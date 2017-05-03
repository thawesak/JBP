package com.th.jbp.backend.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.enums.ItemStatus;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.entity.InventoryItemM;
import com.th.jbp.jpa.entity.InventoryItemOrdersM;
import com.th.jbp.jpa.entity.InventoryItemTypeM;
import com.th.jbp.jpa.entity.SystemConfigM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.InventoryItemOrdersRepository;
import com.th.jbp.jpa.repositories.InventoryItemRepository;
import com.th.jbp.jpa.repositories.InventoryItemTypeRepository;
import com.th.jbp.jpa.repositories.SystemConfigRepository;
import com.th.jbp.web.view.InventoryItemView;

@Service("inventoryItemService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class InventoryItemService implements IInventoryItemService{
	private static final Logger LOGGER = Logger.getLogger(InventoryItemService.class);
	
	@Autowired
	InventoryItemRepository inventoryItemRepository;
	
	@Autowired
	ClassifierValueRepository classifierValueRepository;
	
	@Autowired
	SystemConfigRepository systemConfigRepository;
	
	@Autowired
	InventoryItemTypeRepository inventoryItemTypeRepository;
	
	@Autowired
	InventoryItemOrdersRepository inventoryItemOrdersRepository;
	
	@Autowired
	EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<InventoryItemM> getByKey(InventoryItemM entity) {
		ObjectResult<InventoryItemM> result = new ObjectResult<>();
		InventoryItemM inventoryM = inventoryItemRepository.findOne(entity.getInventoryItemId());
		if (inventoryM == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, inventoryM);
		}
		return result;
	}

	@Override
	public ObjectResult<List<InventoryItemM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<InventoryItemM> save(InventoryItemM entity) {
		ObjectResult<InventoryItemM> result = new ObjectResult<>();
		if (entity == null || entity.getInventoryItemId() != null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			inventoryItemRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InventoryItemM> update(InventoryItemM entity) {
		ObjectResult<InventoryItemM> result = new ObjectResult<>();
		if (entity == null || entity.getInventoryItemId() == null || entity.getInventoryItemId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			inventoryItemRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<InventoryItemM> delete(InventoryItemM entity) {
		ObjectResult<InventoryItemM> result = new ObjectResult<>();
		if (entity == null || entity.getInventoryItemId() == null || entity.getInventoryItemId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			ClassifierValueM itemStatus = classifierValueRepository.findOne(ItemStatus.CARCASS.getId());
			entity.setStatus(status);
			entity.setItemStatus(itemStatus);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			inventoryItemRepository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<InventoryItemM> lists(String itemNo, String itemName, String itemType, PageRequest pageRequest) {
		LOGGER.debug("itemNo = "+itemNo+" , itemName = "+itemName+" , itemType = "+itemType+" , pageRequest = "+pageRequest);
		Page<InventoryItemM> results = null;
		if(StringUtils.isNotEmpty(itemNo) && StringUtils.isNotEmpty(itemName) && StringUtils.isNotEmpty(itemType)){
			results = inventoryItemRepository.findByItemNoAndItemNameAndItemType("%"+itemNo+"%", "%"+itemName+"%", itemType, pageRequest);
		}else if(StringUtils.isNotEmpty(itemNo) && StringUtils.isNotEmpty(itemName)){
			results = inventoryItemRepository.findByItemNoAndItemName("%"+itemNo+"%", "%"+itemName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(itemNo) && StringUtils.isNotEmpty(itemType)){
			results = inventoryItemRepository.findByItemNoAndItemType("%"+itemNo+"%", itemType, pageRequest);
		}else if(StringUtils.isNotEmpty(itemName) && StringUtils.isNotEmpty(itemType)){
			results = inventoryItemRepository.findByItemNameAndItemType("%"+itemName+"%", itemType, pageRequest);
		}else if(StringUtils.isNotEmpty(itemNo)){
			results = inventoryItemRepository.findByItemNo("%"+itemNo+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(itemName)){
			results = inventoryItemRepository.findByItemName("%"+itemName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(itemType)){
			results = inventoryItemRepository.findByItemType(itemType, pageRequest);
		}else{
			results = inventoryItemRepository.findAll(pageRequest);
		}
		
		return results;
	}

	@Override
	public ObjectResult<List<InventoryItemM>> saveItem(InventoryItemM item, int quantity) {
		ObjectResult<List<InventoryItemM>> results = new ObjectResult<>();
		if (item == null || item.getInventoryItemId() != null) {
			results = new ObjectResult<>(ObjectResult.FAIL);
			results.setDetail("entity is null.");
		} else {
			List<InventoryItemM> savedList = new ArrayList<>();
			String currentYear = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy"));
			String prefix = null;
			InventoryItemTypeM inventoryItemTypeM = inventoryItemTypeRepository.findOne(item.getItemType().getInventoryItemTypeId());
			if(inventoryItemTypeM != null && inventoryItemTypeM.getPrefix() != null){
				prefix = inventoryItemTypeM.getPrefix();
			}else{
				SystemConfigM systemConfig = systemConfigRepository.findOne("DEFAULT_ITEM_PREFIX");
				prefix = systemConfig.getSystemValue();
			}
			prefix = prefix+currentYear+"-";
			String maxItemNo = inventoryItemRepository.findMaxItemNoByPrefix(prefix+"%");
			if(maxItemNo != null){
				maxItemNo = maxItemNo.substring(maxItemNo.lastIndexOf("-")+1) ;
			}else{
				maxItemNo = "0";
			}
			
			int max = Integer.parseInt(maxItemNo);
			String newItemNo = null;
			
			for(int i=0 ; i<quantity ; i++){
				InventoryItemM newItem = new InventoryItemM(item);
				max = max+1;
				newItemNo = prefix+lpad(max+"", 4, '0');
				newItem.setItemNo(newItemNo);
				savedList.add(save(newItem).getObject());
			}
			
			InventoryItemOrdersM newOrder = new InventoryItemOrdersM(item);
			newOrder.setQuantity(quantity);
			inventoryItemOrdersRepository.saveAndFlush(newOrder);
			
			results = new ObjectResult<>(ObjectResult.SUCCESS, savedList);
		}
		return results;
	}
	
//	private String lpad(String value, int length){
//		String result = value+"";
//		for(int i=0 ; value.length()<length ; i++){
//			result = "0"+result;
//		}
//		
//		return result;
//	}
	
	private String lpad(String originalString, int length, char padCharacter) {
	      StringBuilder sb = new StringBuilder();
	      while (sb.length() + originalString.length() < length) {
	         sb.append(padCharacter);
	      }
	      sb.append(originalString);
	      
	      return sb.toString();
	}

	@Override
	public ObjectResult<List<InventoryItemView>> remainItems() {
		ObjectResult<List<InventoryItemView>> results = new ObjectResult<>();
		List<InventoryItemView> views = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("select item_type.name as item_name, cv.name as item_status, count(*) as quantity, item_type.remark1 as remark1, item_type.remark2 as remark2 ");
		sql.append("from jbp_inventory_item as item, jbp_inventory_item_type as item_type, jbp_classifiervalue as cv ");
		sql.append("where 1 = 1 ");
		sql.append("and item.status = 1 ");
		sql.append("and item.itemType = item_type.inventoryItemTypeId ");
		sql.append("and item.itemStatus = cv.classifiervalueid ");
		sql.append("group by item_type.name, cv.name, item_type.remark1, item_type.remark2 ");
		sql.append("order by item_type.name ");
		Query q = em.createNativeQuery(sql.toString());
		List<Object[]> remainItems = q.getResultList();
		for (Object[] item : remainItems) {
			InventoryItemView view = new InventoryItemView();
			view.setName( (String) item[0] );
			view.setItemStatus( (String) item[1] );
			view.setQuantity( ((BigInteger) item[2]).intValue() );
			view.setRemark1( (String) item[3] );
			view.setRemark1( (String) item[4] );
			views.add(view);
		}
		
		results = new ObjectResult<>(ObjectResult.SUCCESS, views);
		
		return results;
	}

}
