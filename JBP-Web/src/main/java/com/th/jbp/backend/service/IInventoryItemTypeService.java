package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.jpa.entity.InventoryItemTypeM;

public interface IInventoryItemTypeService extends IService<InventoryItemTypeM>{
	
	public Page<InventoryItemTypeM> lists(String name, String prefix, PageRequest pageRequest);
	
}
