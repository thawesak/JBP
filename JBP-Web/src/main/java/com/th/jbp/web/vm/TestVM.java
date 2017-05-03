package com.th.jbp.web.vm;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TestVM extends BaseVM {

	private static final long serialVersionUID = -213662630713797499L;
	private static final Logger LOGGER = Logger.getLogger(TestVM.class);

	private String currentPage;
	private String mode;
	private String message;

	@Init
	@NotifyChange({ "mode", "message" })
	public void init(@QueryParam("error") String error) {
		LOGGER.debug("init HomeVM");
		this.mode = MODE.INFO.name();
		this.message = "";
		if (StringUtils.isNotBlank(error)) {
			this.mode = MODE.ERROR.name();
			if (error.equalsIgnoreCase("403")) {
				this.message = "Access denied - You are not authorized to access this page.";
			} else {
				this.message = error;
			}
		}
	}

	@GlobalCommand
	@NotifyChange({ "currentPage" })
	public void gotoPage(@BindingParam("page") String page) {
		LOGGER.debug("excute method search");
		this.currentPage = page;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getMode() {
		return mode;
	}

	public String getMessage() {
		return message;
	}

}
