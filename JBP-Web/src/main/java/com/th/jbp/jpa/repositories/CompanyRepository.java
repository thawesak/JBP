package com.th.jbp.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.CompanyM;

@Repository("companyRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface CompanyRepository extends JpaRepository<CompanyM, Long> {
	
	@Query("select o from CompanyM o where o.symbol = :symbol and o.status.name != 'DELETED'")
	CompanyM findBySymbol(String symbol);
	
	@Query("select o from CompanyM o where o.status.name = 'ACTIVE'")
	List<CompanyM> findAllActive();
}
