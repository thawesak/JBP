package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.InsuranceTrailerM;

@Repository("insuranceTrailerRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface InsuranceTrailerRepository extends JpaRepository<InsuranceTrailerM, Long>{

	@Query("SELECT b FROM InsuranceTrailerM b WHERE b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE upper(b.companyName) like upper(:companyName) and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByCompanyName(@Param("companyName") String companyName, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE upper(b.referenceCode) like upper(:referenceCode) and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByReferenceCode(@Param("referenceCode") String referenceCode, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE insuranceType.classifierValueId = :insuranceType and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByInsuranceType(@Param("insuranceType") Long insuranceType, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE upper(b.companyName) like upper(:companyName) and upper(b.referenceCode) like upper(:referenceCode) and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByCompanyNameAndReferenceCode(@Param("companyName") String companyName, @Param("referenceCode") String referenceCode, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE upper(b.companyName) like upper(:companyName) and insuranceType.classifierValueId = :insuranceType and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByCompanyNameAndInsuranceType(@Param("companyName") String companyName, @Param("insuranceType") Long insuranceType, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE upper(b.referenceCode) like upper(:referenceCode) and insuranceType.classifierValueId = :insuranceType and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByReferenceCodeAndInsuranceType(@Param("referenceCode") String referenceCode, @Param("insuranceType") Long insuranceType, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE upper(b.companyName) like upper(:companyName) and upper(b.referenceCode) like upper(:referenceCode) and insuranceType.classifierValueId = :insuranceType and b.status.name != 'DELETED' order by b.companyName, b.referenceCode")
	Page<InsuranceTrailerM> findByCompanyNameAndReferenceCodeAndInsuranceType(@Param("companyName") String companyName, @Param("referenceCode") String referenceCode, @Param("insuranceType") Long insuranceType, Pageable pageable);
	
	@Query("SELECT b FROM InsuranceTrailerM b WHERE b.referenceCode = :referenceCode and b.status.name != 'DELETED'")
	InsuranceTrailerM findOneByReferenceCode(@Param("referenceCode") String referenceCode);
}
