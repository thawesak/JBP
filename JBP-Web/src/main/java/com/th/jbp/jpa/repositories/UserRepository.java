package com.th.jbp.jpa.repositories;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.th.jbp.jpa.entity.UserM;

@Repository("userRepository")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface UserRepository extends JpaRepository<UserM, Long> {
	
	@Query("SELECT b FROM UserM b WHERE b.username = :username and b.status.name != 'DELETED'")
	UserM findByUsername(@Param("username") String username);
	
	@Query("SELECT b FROM UserM b WHERE b.username = :username and b.password = :password and b.status.name != 'DELETED'")
	UserM findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

	@Query("SELECT MAX(b.userId) + 1 FROM UserM b")
	Integer findRunning();
	
	@Query("update UserM b set b.password = :password where b.username = :username and b.status.name != 'DELETED'")
	Integer updatePassword(String username, String password);
	
	@Query("SELECT b FROM UserM b WHERE b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<UserM> findAll(Pageable pageable);
	
	@Query("SELECT b FROM UserM b WHERE upper(b.firstName) like upper(:firstName) and b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<UserM> findByFirstName(@Param("firstName") String firstName, Pageable pageable);
	
	@Query("SELECT b FROM UserM b WHERE upper(b.lastName) like upper(:lastName) and b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<UserM> findByLastName(@Param("lastName") String lastName, Pageable pageable);
	
	@Query("SELECT b FROM UserM b WHERE upper(b.firstName) like upper(:firstName) and upper(b.lastName) like upper(:lastName) and b.status.name != 'DELETED' order by b.firstName, b.lastName")
	Page<UserM> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);
	
}
