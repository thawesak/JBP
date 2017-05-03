package com.th.jbp.web.vm;

import org.apache.log4j.Logger;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ViewImageVM extends BaseVM {

	private static final long serialVersionUID = -213662630713797499L;
	private static final Logger LOGGER = Logger.getLogger(ViewImageVM.class);
	private byte[] media;

	@Init
	public void init(@ExecutionArgParam("media") byte[] media) {
		LOGGER.info("init media");
		this.media = media;
	}

	@Command
	public void close(@BindingParam("window") final Window window) {
		LOGGER.debug("excute method close");
		window.detach();
	}

	public byte[] getMedia() {
		return media;
	}

	public void setMedia(byte[] media) {
		this.media = media;
	}

}
