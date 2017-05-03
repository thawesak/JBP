package com.th.jbp.web.vm;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

import com.th.jbp.backend.service.SystemConfigService;
import com.th.jbp.jpa.entity.SystemConfigM;
import com.th.jbp.jpa.repositories.SystemConfigRepository;
import com.th.jbp.security.WebUserDetails;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SystemSettingVM extends BaseVM{
	private static final long serialVersionUID = 6913473656372440792L;
	private static final Logger LOGGER = Logger.getLogger(SystemSettingVM.class);
	
	@WireVariable
	SystemConfigRepository systemConfigRepository;
	
	@WireVariable
	SystemConfigService systemConfigService;
	
	private ListModelList<SystemConfigM> systemConfigs = new ListModelList<SystemConfigM>();
	private boolean displayEdit = true;
	
	@Init
	@NotifyChange({ "" })
	public void init() {
		LOGGER.debug("init SystemSettingVM");
		List<SystemConfigM> configs = systemConfigRepository.findAll();
		for(SystemConfigM systemConfig : configs){
			systemConfigs.add(systemConfig);
		}
	}
	
	@Command
	public void changeEditableStatus(@BindingParam("systemConfig") SystemConfigM systemConfig) {
		systemConfig.setEditingStatus(!systemConfig.isEditingStatus());
        refreshRowTemplate(systemConfig);
    }
	
	@Command
    public void confirm(@BindingParam("systemConfig") SystemConfigM systemConfig) {
		WebUserDetails webUserDetails = (WebUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		systemConfig.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		systemConfig.setUpdateBy(webUserDetails.getUser());
		systemConfigService.update(systemConfig);
        changeEditableStatus(systemConfig);
        refreshRowTemplate(systemConfig);
    }
	
	public void refreshRowTemplate(SystemConfigM systemConfig) {
        //replace the element in the collection by itself to trigger a model update
		systemConfigs.set(systemConfigs.indexOf(systemConfig), systemConfig);       
    }

	public ListModelList<SystemConfigM> getSystemConfigs() {
		return systemConfigs;
	}

	public void setSystemConfigs(ListModelList<SystemConfigM> systemConfigs) {
		this.systemConfigs = systemConfigs;
	}

	public boolean isDisplayEdit() {
		return displayEdit;
	}

	@NotifyChange({"systemConfigs", "displayEdit"})
	public void setDisplayEdit(boolean displayEdit) {
		this.displayEdit = displayEdit;
	}

}
