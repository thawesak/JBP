package com.th.jbp.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.TrailerTailM;

@Repository("trailerTailRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface TrailerTailRepository extends JpaRepository<TrailerTailM, Long>{
	
	@Query("SELECT b FROM TrailerTailM b WHERE b.status.name != 'DELETED' order by b.licensePlate")
	Page<TrailerTailM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM TrailerTailM b WHERE upper(b.licensePlate) like upper(:licensePlate) and b.status.name != 'DELETED' order by b.licensePlate")
	Page<TrailerTailM> findByLicensePlate(@Param("licensePlate") String licensePlate, Pageable pageable);

	@Query("select o from TrailerTailM o where o.status.name = 'ACTIVE'")
	List<TrailerTailM> findAllActive();
}
