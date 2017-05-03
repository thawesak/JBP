package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.ApplicationFormM;

@Repository("applicationFormRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ApplicationFormRepository extends JpaRepository<ApplicationFormM, Long>{

	@Query("SELECT b FROM ApplicationFormM b WHERE b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<ApplicationFormM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM ApplicationFormM b WHERE upper(b.firstName) like upper(:firstName) and b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<ApplicationFormM> findByFirstName(@Param("firstName") String firstName, Pageable pageable);
	
	@Query("SELECT b FROM ApplicationFormM b WHERE upper(b.lastName) like upper(:lastName) and b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<ApplicationFormM> findByLastName(@Param("lastName") String lastName, Pageable pageable);
	
	@Query("SELECT b FROM ApplicationFormM b WHERE upper(b.firstName) like upper(:firstName) and upper(b.lastName) like upper(:lastName) and b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<ApplicationFormM> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);
	
}
