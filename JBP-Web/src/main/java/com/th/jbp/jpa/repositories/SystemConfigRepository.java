package com.th.jbp.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.SystemConfigM;

@Repository("systemConfigRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface SystemConfigRepository extends JpaRepository<SystemConfigM, String> {

	@Query("select o from SystemConfigM o order by systemKey")
	List<SystemConfigM> findAll();
	
	@Query("select o from SystemConfigM o where o.systemKey = :systemKey")
	SystemConfigM findByKey(@Param("systemKey") String systemKey);

}
