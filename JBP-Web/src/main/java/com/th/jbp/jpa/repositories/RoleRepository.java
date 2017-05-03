package com.th.jbp.jpa.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.RoleM;

@Repository("roleRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface RoleRepository extends JpaRepository<RoleM, Long> {
	
	@Query("select o from RoleM o where o.roleId = :roleId")
	RoleM findByRoleId(long roleId);
	
	@Query("select o from RoleM o where o.status.name = 'ACTIVE'")
	List<RoleM> findAllActive();
}
