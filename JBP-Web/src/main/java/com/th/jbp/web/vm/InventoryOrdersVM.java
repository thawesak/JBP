package com.th.jbp.web.vm;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Converter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.backend.service.InventoryItemOrdersService;
import com.th.jbp.backend.service.UserService;
import com.th.jbp.jpa.entity.InventoryItemOrdersM;
import com.th.jbp.jpa.entity.InventoryItemTypeM;
import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.InventoryItemOrdersRepository;
import com.th.jbp.jpa.repositories.InventoryItemTypeRepository;
import com.th.jbp.web.utils.SecurityUtils;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class InventoryOrdersVM extends BaseVM{
	private static final long serialVersionUID = 6336535098948760696L;
	private static final Logger LOGGER = Logger.getLogger(InventoryOrdersVM.class);
	
	@WireVariable
	ClassifierValueRepository classifierValueRepository;
	
	@WireVariable
	InventoryItemOrdersRepository inventoryItemOrdersRepository;
	
	@WireVariable
	InventoryItemTypeRepository inventoryItemTypeRepository;
	
	@WireVariable
	InventoryItemOrdersService inventoryItemOrdersService;
	
	@WireVariable
	UserService userService;
	
	private String lotNumber = null;
	private String itemName = null;
	private String itemType = null;
	private InventoryItemTypeM type;
	
	private ListModelList<InventoryItemOrdersM> records = null;
	private ListModelList<InventoryItemTypeM> itemTypes = null;
	private InventoryItemOrdersM inventoryItemOrdersM;
	private InventoryItemTypeM type2;
	private int pageSize = 10;
	private int activePage = 0;
	private int totalSize = 0;

	@Init
	public void init() {
		this.records = new ListModelList<>();
		this.itemTypes = new ListModelList<>(inventoryItemTypeRepository.findAllActive());
		this.itemTypes.add(0, new InventoryItemTypeM());
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	private void clearValues(){
		this.activePage = 0;
		this.totalSize = 0;
		this.records = new ListModelList<>();
	}
	
	private void loadItems() {
		LOGGER.info("loadItems--> activePage : " + this.activePage);
		PageRequest pageRequest = new PageRequest(activePage, pageSize);
		itemType = type != null && type.getName() != null ? type.getName() : null;
		Page<InventoryItemOrdersM> page = inventoryItemOrdersService.lists(lotNumber, itemName, itemType, pageRequest);
		this.totalSize = Integer.parseInt(String.valueOf(page.getTotalElements()));
		this.records.clear();
		this.records.addAll(page.getContent());
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "lotNumber", "itemName", "itemType" })
	public void search() {
		LOGGER.info("excute method search");
		setMode(MODE.SEARCH.name());
		clearValues();
		loadItems();
	}
	
	@Command
	@NotifyChange({ "records", "totalSize", "activePage", "pageSize", "mode", "lotNumber", "itemName", "itemType", "type", "inventoryItemOrdersM" })
	public void clearSearch() {
		LOGGER.info("excute method clearSearch");
		setMode(MODE.SEARCH.name());
		setInventoryItemOrdersM(new InventoryItemOrdersM());
		setType(new InventoryItemTypeM());
		lotNumber = null;
		itemName = null;
		itemType = null;
		clearValues();
	}
	
	@Command
	@NotifyChange({ "inventoryItemOrdersM" })
	public void getUploadedImage1(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	inventoryItemOrdersM.setImage(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "inventoryItemOrdersM" })
	public void viewImage1() {
		LOGGER.debug("excute method viewImage");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", inventoryItemOrdersM.getImage());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "inventoryItemOrdersM" })
	public void getUploadedImage2(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	inventoryItemOrdersM.setImage2(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "inventoryItemOrdersM" })
	public void viewImage2() {
		LOGGER.debug("excute method viewImage");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", inventoryItemOrdersM.getImage2());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	@Command
	@NotifyChange({ "inventoryItemOrdersM" })
	public void getUploadedImage3(@BindingParam("media") Media media){
		if(media == null)
			return;
			
        if (media instanceof org.zkoss.image.Image) {
        	inventoryItemOrdersM.setImage3(media.getByteData());
        } else {
            Messagebox.show(getMessage("upload.type.invalid"), "Error", Messagebox.OK, Messagebox.ERROR);
        }
		
	}
	
	@Command
	@NotifyChange({ "inventoryItemOrdersM" })
	public void viewImage3() {
		LOGGER.debug("excute method viewImage");
		Map<String, Object> arg = new HashMap<String, Object>();
		arg.put("media", inventoryItemOrdersM.getImage3());
		Window window = (Window) Executions.createComponents(
				"/zul/view_image.zul", null, arg);
		window.doModal();
	}
	
	private static Map<Long, UserM> CACHE_USERS = new HashMap<>();
	
	@SuppressWarnings("rawtypes")
	public Converter getMemberConverter() {
		return new Converter() {
			public Object coerceToUi(Object user, Component component, BindContext ctx) {
				if (user == null) {
					return "N/A";
				}
				Long userId = ((UserM) user).getUserId();
				UserM userM = CACHE_USERS.get(userId);
				if (userM == null) {
					userM = new UserM();
					userM.setUserId(userId);
					ObjectResult<UserM> rs = userService.getByKey(userM);
					if (rs.getCode() == ObjectResult.SUCCESS) {
						userM = rs.getObject();
						if (userM == null) {
							return "N/A";
						}
						CACHE_USERS.put(userId, userM);
					}
				}
				return String.format("%s %s", userM.getFirstName(), userM.getLastName());
			}

			public Object coerceToBean(Object val, Component component, BindContext ctx) {
				return null;
			}
		};
	}
	
	@Command
	@NotifyChange({ "records", "inventoryItemOrdersM", "mode", "activePage", "type2" })
	public void cancel() {
		LOGGER.info("excute method cancel");
		setMode(MODE.SEARCH.name());
		this.inventoryItemOrdersM = null;
		this.activePage = 0;
		this.type2 = null;
	}
	
	@Command
	@NotifyChange({ "records", "inventoryItemOrdersM", "mode", "type2" })
	public void getByItem(@BindingParam("item") InventoryItemOrdersM inventoryItemOrdersM) {
		LOGGER.info("excute method getByItem");
		setMode(MODE.EDIT.name());
		this.inventoryItemOrdersM = inventoryItemOrdersService.getByKey(inventoryItemOrdersM).getObject();
		InventoryItemTypeM resultType = inventoryItemTypeRepository.findOne(inventoryItemOrdersM.getItemType().getInventoryItemTypeId());
		type2 = resultType;
		LOGGER.info("type2 : " + this.type2);
	}
	
	@Command
	@NotifyChange({ "records", "inventoryItemOrdersM", "type2" })
	public void delete(@BindingParam("item") final InventoryItemOrdersM inventoryItemOrdersM) {
		LOGGER.info("excute method delete");
		String message = getMessage(MESSAGE_CONFIRM_DELETE);
		execute(new MethodOperation() {
			@Override
			public void execute() {
				Clients.showBusy("Waiting for server...");
				inventoryItemOrdersM.setUpdateBy(SecurityUtils.getWebUserDetails());
				try{
					ObjectResult<InventoryItemOrdersM> objectResult = inventoryItemOrdersService.delete(inventoryItemOrdersM);
					if (objectResult.getCode() == ObjectResult.SUCCESS) {
						LOGGER.info("delete sussess load items again.");
						loadItems();
					}
				}catch(Exception e){
					LOGGER.error(e.toString(), e);
					showError("Error", getMessage("message.error.operation.fail", "Delete", e.getMessage()));
				}finally{
					Clients.clearBusy();
				}
				BindUtils.postGlobalCommand(null, null, "refreshView", null);
			}
		}, LOGGER, message);
	}
	
	@GlobalCommand
	@NotifyChange({ "records", "inventoryItemOrdersM", "activePage", "totalSize", "pageSize", "mode", "type2" })
	public void refreshView() {
		LOGGER.info("excute method refreshView");
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public InventoryItemTypeM getType() {
		return type;
	}

	public void setType(InventoryItemTypeM type) {
		this.type = type;
	}

	public ListModelList<InventoryItemOrdersM> getRecords() {
		return records;
	}

	public void setRecords(ListModelList<InventoryItemOrdersM> records) {
		this.records = records;
	}

	public ListModelList<InventoryItemTypeM> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(ListModelList<InventoryItemTypeM> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public InventoryItemOrdersM getInventoryItemOrdersM() {
		return inventoryItemOrdersM;
	}

	public void setInventoryItemOrdersM(InventoryItemOrdersM inventoryItemOrdersM) {
		this.inventoryItemOrdersM = inventoryItemOrdersM;
	}

	public InventoryItemTypeM getType2() {
		return type2;
	}

	public void setType2(InventoryItemTypeM type2) {
		this.type2 = type2;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getActivePage() {
		return activePage;
	}

	@NotifyChange("records")
	public void setActivePage(int activePage) {
		this.activePage = activePage;
		this.totalSize = 0;
		this.records = new ListModelList<>();
		loadItems();
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

}
