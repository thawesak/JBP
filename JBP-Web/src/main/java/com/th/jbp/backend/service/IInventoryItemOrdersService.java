package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.jpa.entity.InventoryItemOrdersM;

public interface IInventoryItemOrdersService extends IService<InventoryItemOrdersM>{
	
	public Page<InventoryItemOrdersM> lists(String lotNumber, String itemName, String itemType, PageRequest pageRequest);

}
