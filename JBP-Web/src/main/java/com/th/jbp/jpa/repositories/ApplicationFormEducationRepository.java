package com.th.jbp.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.ApplicationFormEducationM;

@Repository("applicationFormEducationRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ApplicationFormEducationRepository extends JpaRepository<ApplicationFormEducationM, Long>{

	@Query("SELECT b FROM ApplicationFormEducationM b WHERE b.applicationForm.applicationFormId  = :applicationFormId order by b.applicationForm.applicationFormId")
	List<ApplicationFormEducationM> findByApplicationFormId(@Param("applicationFormId") Long applicationFormId);
	
}
