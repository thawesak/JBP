package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.TrailerHeadM;

@Repository("trailerHeadRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface TrailerHeadRepository extends JpaRepository<TrailerHeadM, Long>{
	
	@Query("SELECT b FROM TrailerHeadM b WHERE b.status.name != 'DELETED' order by b.licensePlate")
	Page<TrailerHeadM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM TrailerHeadM b WHERE upper(b.licensePlate) like upper(:licensePlate) and b.status.name != 'DELETED' order by b.licensePlate")
	Page<TrailerHeadM> findByLicensePlate(@Param("licensePlate") String licensePlate, Pageable pageable);

}
