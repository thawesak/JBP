package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.InventoryItemM;

@Repository("inventoryItemRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface InventoryItemRepository extends JpaRepository<InventoryItemM, Long>{
	
	@Query("SELECT b FROM InventoryItemM b WHERE b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE upper(b.itemNo) like upper(:itemNo) and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemNo(@Param("itemNo") String itemNo, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE upper(b.itemName) like upper(:itemName) and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemName(@Param("itemName") String itemName, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemType(@Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE upper(b.itemNo) like upper(:itemNo) and upper(b.itemName) like upper(:itemName) and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemNoAndItemName(@Param("itemNo") String itemNo, @Param("itemName") String itemName, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE upper(b.itemNo) like upper(:itemNo) and b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemNoAndItemType(@Param("itemNo") String itemNo, @Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE upper(b.itemName) like upper(:itemName) and b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemNameAndItemType(@Param("itemName") String itemName, @Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE upper(b.itemNo) like upper(:itemNo) and upper(b.itemName) like upper(:itemName) and b.itemType.name = :itemType and b.status.name != 'DELETED' order by b.itemNo, b.itemName")
	Page<InventoryItemM> findByItemNoAndItemNameAndItemType(@Param("itemNo") String itemNo, @Param("itemName") String itemName, @Param("itemType") String itemType, Pageable pageable);
	
	@Query("SELECT b FROM InventoryItemM b WHERE b.itemNo = :itemNo and b.status.name != 'DELETED'")
	InventoryItemM findOneByItemNo(@Param("itemNo") String itemNo);

	@Query("SELECT max(b.itemNo) FROM InventoryItemM b WHERE b.itemNo like (:prefix)")
	String findMaxItemNoByPrefix(@Param("prefix") String prefix);
}
