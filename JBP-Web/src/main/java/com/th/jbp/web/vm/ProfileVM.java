package com.th.jbp.web.vm;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.IUserService;
import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.security.WebUserDetails;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProfileVM extends BaseVM {

	private static final long serialVersionUID = -213662630713797499L;
	private static final Logger LOGGER = Logger.getLogger(ProfileVM.class);

	@WireVariable
	private IUserService userService;

	private UserM user;

	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init ProfileVM");
		setMode(BaseVM.MODE.INFO.name());
		LOGGER.debug(BaseVM.MODE.INFO.name());
		loadUser();
	}

	private void loadUser() {
		ObjectResult<UserM> userObjectResult = userService.findByUsername(getLoginName());
		if (null != userObjectResult && userObjectResult.getCode() == ObjectResult.SUCCESS) {
			this.user = userObjectResult.getObject();
		} else {
			LOGGER.error("Not found Login Name : ".concat(getLoginName()));
			throw new NullPointerException();
		}
	}

	@Command
	@NotifyChange({ "mode" })
	public void editClick() {
		setMode(MODE.EDIT.name());
	}

	@Command
	@NotifyChange({ "mode", "user" })
	public void cancelClick() {
		setMode(MODE.INFO.name());
		loadUser();
	}

	@Command
	@NotifyChange({ "mode", "user" })
	public void save() {
		LOGGER.debug("execute save");
		
		WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		this.user.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		this.user.setUpdateBy( webUserDetails.getUser() );
		
		ObjectResult<UserM> userObjectResult = userService.update(this.user);
		if (null != userObjectResult && userObjectResult.getCode() == ObjectResult.SUCCESS) {
			this.user = userObjectResult.getObject();
			setMode(MODE.INFO.name());
		} else {
			showError("Error", userObjectResult.getDetail());
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

}
