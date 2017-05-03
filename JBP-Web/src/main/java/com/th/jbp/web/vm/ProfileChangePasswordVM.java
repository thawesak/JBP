package com.th.jbp.web.vm;

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

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.IUserService;
import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.web.utils.SecurityUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProfileChangePasswordVM extends BaseVM {

	private static final long serialVersionUID = -213662630713797499L;
	private static final Logger LOGGER = Logger.getLogger(ProfileChangePasswordVM.class);

	@WireVariable
	private IUserService userService;
	
	private	UserM user;
	
	private	String oldPassword;
	
	private	String newPassword;
	
	private	String confirmNewPassword;
	
	private	String errorOldPassword;
	
	private	String errorNewPassword;
	
	private	String errorConfirmNewPassword;
	
	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init ProfileVM");
		setMode(BaseVM.MODE.INFO.name());
		LOGGER.debug(BaseVM.MODE.INFO.name());
		loadUser();
	}

	private void loadUser() {
		this.oldPassword = "";
		this.newPassword = "";
		this.confirmNewPassword = "";
		this.errorOldPassword = "";
		this.errorNewPassword = "";
		this.errorConfirmNewPassword = "";
		
		ObjectResult<UserM> userObjectResult = userService.findByUsername(getLoginName());
		if (null != userObjectResult && userObjectResult.getCode() == ObjectResult.SUCCESS) {
			this.user = userObjectResult.getObject();
		} else {
			LOGGER.error("Not found Login Name : ".concat(getLoginName()));
			throw new NullPointerException();
		}
	}

	@GlobalCommand
	@NotifyChange({ "mode", "user", "oldPassword", "newPassword", "confirmNewPassword", "errorOldPassword", "errorNewPassword", "errorConfirmNewPassword" })
	public void refreshView() {
		LOGGER.debug("excute method refreshView");
	}
	
	@Command
	@NotifyChange({ "mode", "user", "oldPassword", "newPassword", "confirmNewPassword", "errorOldPassword", "errorNewPassword", "errorConfirmNewPassword" })
	public void save() {
		LOGGER.debug("exute save");
		validateOldPassword();
		validateNewPassword();
		validateConfirmNewPassword();
		if (StringUtils.isBlank(errorOldPassword) && StringUtils.isBlank(errorNewPassword)
				&& StringUtils.isBlank(errorConfirmNewPassword)) {
			execute(new MethodOperation() {
				@Override
				public void execute() {
					Clients.showBusy("Waiting for server...");
					try {
						user.setPassword(SecurityUtils.generatePassword(newPassword));
						ObjectResult<UserM> userObjectResult = userService.update(user);
						if (null != userObjectResult && userObjectResult.getCode() == ObjectResult.SUCCESS) {
							//this.user = userObjectResult.getObject();
							setMode(MODE.INFO.name());
							loadUser();
							showInformation("แก้ไขรหัสผ่านสำเร็จ");
						} else {
							showError("Error", userObjectResult.getDetail());
						}
					} catch (Exception e) {
						LOGGER.error(e.toString(), e);
						showError("Error", getMessage("message.error.operation.fail", "save", e.getMessage()));
					} finally {
						Clients.clearBusy();
					}
					BindUtils.postGlobalCommand(null, null, "refreshView", null);
				}
			}, LOGGER, "คุณต้องการแก้ไขรหัสผ่าน?");
		} else {
			
		}
	}
	
	@Command
	@NotifyChange({ "mode", "user", "oldPassword", "newPassword", "confirmNewPassword", "errorOldPassword", "errorNewPassword", "errorConfirmNewPassword" })
	public void cancel() {
		loadUser();
	}
	
	@Command
	@NotifyChange({ "errorOldPassword" })
	public void validateOldPassword() {
		LOGGER.debug("exute validateOldPassword");
		if (null == this.oldPassword || this.oldPassword.length() <= 0) {
			this.errorOldPassword = "กรุณากรอก Old Password";
		} else if (!this.user.getPassword().equals(SecurityUtils.generatePassword(this.oldPassword, true))) {
			this.errorOldPassword = "รหัสผ่านเดิมไม่ถูกต้อง";
		} else {
			this.errorOldPassword = "";
		}
	}
	
	@Command
	@NotifyChange({ "errorNewPassword" })
	public void validateNewPassword() {
		LOGGER.debug("exute validateNewPassword");
		if (null == this.newPassword || this.newPassword.length() <= 0) {
			this.errorNewPassword = "กรุณากรอก New Password";
		} else if (this.user.getPassword().equals(SecurityUtils.generatePassword(this.newPassword, true))) {
			this.errorNewPassword = "รหัสผ่านใหม่ต้องไม่ซ้ำกับรหัสผ่านเดิม";
		} else if (this.newPassword.length() < 8) {
			this.errorNewPassword = "รหัสผ่านใหม่ต้องมีอย่างน้อย 8 ตัวขึ้นไป";
		} else if (!this.newPassword.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\+\\=])[a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\*\\+\\=]+$")) {
			this.errorNewPassword = "รหัสผ่านใหม่ต้องประกอบด้วยตัวอักษร หรืออักขระอย่างน้อย 1 ตัว";
		} else {
			this.errorNewPassword = "";
		}
	}
	
	@Command
	@NotifyChange({ "errorConfirmNewPassword" })
	public void validateConfirmNewPassword() {
		LOGGER.debug("exute validateOldPassword");
		if (null == this.confirmNewPassword || this.confirmNewPassword.length() <= 0) {
			this.errorConfirmNewPassword = "กรุณากรอก Confirm New Password";
		} else if (!this.confirmNewPassword.equals(this.newPassword)) {
			this.errorConfirmNewPassword = "รหัสผ่านใหม่ไม่สัมพันธ์กัน";
		} else {
			this.errorConfirmNewPassword = "";
		}
	}

	public UserM getUser() {
		if (user == null)
			user = new UserM();
		return user;
	}

	public void setUser(UserM user) {
		this.user = user;
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

	public String getErrorOldPassword() {
		return errorOldPassword;
	}

	public void setErrorOldPassword(String errorOldPassword) {
		this.errorOldPassword = errorOldPassword;
	}

	public String getErrorNewPassword() {
		return errorNewPassword;
	}

	public void setErrorNewPassword(String errorNewPassword) {
		this.errorNewPassword = errorNewPassword;
	}

	public String getErrorConfirmNewPassword() {
		return errorConfirmNewPassword;
	}

	public void setErrorConfirmNewPassword(String errorConfirmNewPassword) {
		this.errorConfirmNewPassword = errorConfirmNewPassword;
	}

}
