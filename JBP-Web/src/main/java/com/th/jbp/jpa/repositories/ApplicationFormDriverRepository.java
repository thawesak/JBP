package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.ApplicationFormDriverM;

@Repository("applicationFormDriverRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ApplicationFormDriverRepository extends JpaRepository<ApplicationFormDriverM, Long>{
	
	@Query("SELECT b FROM ApplicationFormDriverM b WHERE b.applicationFormId  = :applicationFormId")
	ApplicationFormDriverM findByApplicationFormId(@Param("applicationFormId") Long applicationFormId);

}
