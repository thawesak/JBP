package com.th.jbp.web.vm;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import com.th.jbp.backend.service.UserService;
import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.UserRepository;
import com.th.jbp.web.utils.SecurityUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ResetPasswordVM extends BaseVM{
	private static final long serialVersionUID = 6163499077898784904L;
	private static final Logger LOGGER = Logger.getLogger(ResetPasswordVM.class);
	
	@WireVariable
	UserRepository userRepository;
	
	@WireVariable
	ClassifierValueRepository classifierValueRepository;
	
	@WireVariable
	UserService userService;
	
	private String username;
	private String password;
	private String confirmPassword;
	private UserM userM;
	
	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init ResetPasswordVM");
	}

	@Command
	@NotifyChange({ "username", "password", "confirmPassword" })
	public void resetPassword() {
		LOGGER.info("excute method resetPassword");
		String sb = StringUtils.trimToEmpty(validate());
		if (StringUtils.isNotEmpty(sb)) {
			Clients.clearBusy();
			showValidate(sb);
			return;
		}
		
		String message = getMessage(MESSAGE_CONFIRM_UPDATE);
		final String operation = getMessage("update");
		execute(new MethodOperation() {
			@Override
			public void execute() {

				Clients.showBusy("Waiting for server...");
				
				try {
					userM.setPassword(SecurityUtils.generatePassword(password, true));
					userM.setUpdateDate(new Timestamp(System.currentTimeMillis()));
					userM.setUpdateBy(SecurityUtils.getWebUserDetails());
					userService.update(userM);
					showInformation(getMessage("message.operation.success", operation));
					clearInputValue();
				} catch (Exception e) {
					LOGGER.error(e.toString(), e);
					showError("Error", getMessage("message.error.operation.fail", operation, e.getMessage()));
				} finally {
					Clients.clearBusy();
				}
				BindUtils.postGlobalCommand(null, null, "refreshView", null);
			}
		}, LOGGER, message);
	}
	
	@GlobalCommand
	@NotifyChange({ "username", "password", "confirmPassword" })
	public void refreshView() {
		LOGGER.info("excute method refreshView");
	}
	
	private String validate() {
		StringBuilder result = new StringBuilder();
		int i = 1;
		if (StringUtils.isEmpty(this.username)) {
			result.append(i++ + "." + getMessage("username.require"));
			result.append(NEW_LINE);
		}else{
			userM = userRepository.findByUsername(username);
			if(userM == null){
				result.append(i++ + "." + getMessage("username.not.found"));
				result.append(NEW_LINE);
			}
		}
		if (StringUtils.isEmpty(this.password)) {
			result.append(i++ + "." + getMessage("password.require"));
			result.append(NEW_LINE);
		}else if(this.password.length() < 6){
			result.append(i++ + "." + getMessage("password.size.invalid"));
			result.append(NEW_LINE);
		}
		if (StringUtils.isEmpty(this.confirmPassword)) {
			result.append(i++ + "." + getMessage("confirmpassword.require"));
			result.append(NEW_LINE);
		}
		if (StringUtils.isNotEmpty(this.password) && StringUtils.isNotEmpty(this.confirmPassword)) {
			if(!this.password.equals(this.confirmPassword)){
				result.append(i++ + "." + getMessage("password.mismatch"));
				result.append(NEW_LINE);
			}
		}
		
		return result.toString();
	}
	
	private void clearInputValue() {
		setUsername(null);
		setPassword(null);
		setConfirmPassword(null);
		setUserM(null);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public UserM getUserM() {
		return userM;
	}

	public void setUserM(UserM userM) {
		this.userM = userM;
	}
	
}
