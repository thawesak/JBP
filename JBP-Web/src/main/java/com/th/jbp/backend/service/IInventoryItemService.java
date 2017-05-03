package com.th.jbp.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.InventoryItemM;
import com.th.jbp.web.view.InventoryItemView;

public interface IInventoryItemService extends IService<InventoryItemM>{
	
	public Page<InventoryItemM> lists(String itemNo, String itemName, String itemType, PageRequest pageRequest);

	public ObjectResult<List<InventoryItemM>> saveItem(InventoryItemM item, int quantity);
	
	public ObjectResult<List<InventoryItemView>> remainItems();
}
