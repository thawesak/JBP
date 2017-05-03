package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.InventoryItemOrdersM;

@Repository("inventoryItemOrdersRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface InventoryItemOrdersRepository extends JpaRepository<InventoryItemOrdersM, Long>{
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE upper(b.lotNumber) like upper(:lotNumber) and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByLotNumber(@Param("lotNumber") String lotNumber, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE upper(b.itemName) like upper(:itemName) and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByItemName(@Param("itemName") String itemName, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByItemType(@Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE upper(b.lotNumber) like upper(:lotNumber) and upper(b.itemName) like upper(:itemName) and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByLotNumberAndItemName(@Param("lotNumber") String lotNumber, @Param("itemName") String itemName, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE upper(b.lotNumber) like upper(:lotNumber) and b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByLotNumberAndItemType(@Param("lotNumber") String lotNumber, @Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE upper(b.itemName) like upper(:itemName) and b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByItemNameAndItemType(@Param("itemName") String itemName, @Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE upper(b.lotNumber) like upper(:lotNumber) and upper(b.itemName) like upper(:itemName) and b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemName")
	Page<InventoryItemOrdersM> findByLotNumberAndItemNameAndItemType(@Param("lotNumber") String lotNumber, @Param("itemName") String itemName, @Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemOrdersM b WHERE b.lotNumber = :lotNumber and b.status.name != 'DELETED'")
	InventoryItemOrdersM findOneByLotNumber(@Param("lotNumber") String lotNumber);
	
}