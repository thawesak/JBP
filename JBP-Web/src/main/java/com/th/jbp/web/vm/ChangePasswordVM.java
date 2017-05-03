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
public class ChangePasswordVM extends BaseVM{

	private static final long serialVersionUID = 3945838562091506736L;

	private static final Logger LOGGER = Logger.getLogger(ChangePasswordVM.class);
	
	@WireVariable
	UserRepository userRepository;
	
	@WireVariable
	ClassifierValueRepository classifierValueRepository;
	
	@WireVariable
	UserService userService;
	
	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;
	private UserM userM;
	
	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init ChangePasswordVM");
	}

	@Command
	@NotifyChange({ "oldPassword", "newPassword", "confirmNewPassword" })
	public void changePassword() {
		LOGGER.info("excute method changePassword");
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
					userM.setPassword(SecurityUtils.generatePassword(newPassword, true));
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
	@NotifyChange({ "oldPassword", "newPassword", "confirmNewPassword" })
	public void refreshView() {
		LOGGER.info("excute method refreshView");
	}
	
	private String validate() {
		StringBuilder result = new StringBuilder();
		int i = 1;
		if (StringUtils.isEmpty(this.oldPassword)) {
			result.append(i++ + "." + getMessage("changepassword.old.password.required"));
			result.append(NEW_LINE);
		}else{
			String encryptOldPassword = SecurityUtils.generatePassword(oldPassword, true);
			userM = userRepository.findOne(SecurityUtils.getWebUserDetails().getUserId());
			if(!userM.getPassword().equals(encryptOldPassword)){
				result.append(i++ + "." + getMessage("changepassword.old.password.invalid"));
				result.append(NEW_LINE);
			}
		}
		if (StringUtils.isEmpty(this.newPassword)) {
			result.append(i++ + "." + getMessage("changepassword.new.password.required"));
			result.append(NEW_LINE);
		}else if(this.newPassword.length() < 6){
			result.append(i++ + "." + getMessage("password.size.invalid"));
			result.append(NEW_LINE);
		}
		if (StringUtils.isEmpty(this.confirmNewPassword)) {
			result.append(i++ + "." + getMessage("changepassword.confirm.new.password.required"));
			result.append(NEW_LINE);
		}
		if (StringUtils.isNotEmpty(this.newPassword) && StringUtils.isNotEmpty(this.confirmNewPassword)) {
			if(!this.newPassword.equals(this.confirmNewPassword)){
				result.append(i++ + "." + getMessage("password.mismatch"));
				result.append(NEW_LINE);
			}else if(this.newPassword.equals(this.oldPassword)){
				result.append(i++ + "." + getMessage("newpassword.invalid"));
				result.append(NEW_LINE);
			}
		}
		
		return result.toString();
	}
	
	private void clearInputValue() {
		setOldPassword(null);
		setNewPassword(null);
		setConfirmNewPassword(null);
		setUserM(null);
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public UserM getUserM() {
		return userM;
	}

	public void setUserM(UserM userM) {
		this.userM = userM;
	}

}
