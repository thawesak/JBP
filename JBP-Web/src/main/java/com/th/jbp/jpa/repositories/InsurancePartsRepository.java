package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.InsurancePartsM;

@Repository("insurancePartsRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface InsurancePartsRepository extends JpaRepository<InsurancePartsM, Long>{

	@Query("SELECT b FROM InsurancePartsM b WHERE b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsurancePartsM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM InsurancePartsM b WHERE upper(b.companyName) like upper(:companyName) and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsurancePartsM> findByCompanyName(@Param("companyName") String companyName, Pageable pageable);
	
	@Query("SELECT b FROM InsurancePartsM b WHERE upper(b.referenceCode) like upper(:referenceCode) and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsurancePartsM> findByReferenceCode(@Param("referenceCode") String referenceCode, Pageable pageable);
	
	@Query("SELECT b FROM InsurancePartsM b WHERE upper(b.companyName) like upper(:companyName) and upper(b.referenceCode) like upper(:referenceCode) and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsurancePartsM> findByCompanyNameAndReferenceCode(@Param("companyName") String companyName, @Param("referenceCode") String referenceCode, Pageable pageable);
	
	@Query("SELECT b FROM InsurancePartsM b WHERE b.referenceCode = :referenceCode and b.status.name != 'DELETED'")
	InsurancePartsM findOneByReferenceCode(@Param("referenceCode") String referenceCode);
}
