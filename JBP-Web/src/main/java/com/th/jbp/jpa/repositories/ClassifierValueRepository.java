package com.th.jbp.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.ClassifierValueM;

@Repository("classifierValueRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ClassifierValueRepository extends JpaRepository<ClassifierValueM, Long>{
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 1")
	List<ClassifierValueM> findAllRecordStatus();
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 2")
	List<ClassifierValueM> findAllCompanyType();
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 3")
	List<ClassifierValueM> findAllBranch();
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 4")
	List<ClassifierValueM> findAllItemType();
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 5")
	List<ClassifierValueM> findAllItemStatus();
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 6")
	List<ClassifierValueM> findAllInsuranceType();
	
	@Query("select o from ClassifierValueM o where o.classifierGroup.classifierGroupId = 4 and o.name = :name")
	List<ClassifierValueM> findItemTypeByName(@Param("name") String name);
}
