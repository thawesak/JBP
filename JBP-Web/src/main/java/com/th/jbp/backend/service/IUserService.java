package com.th.jbp.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.entity.CompanyM;
import com.th.jbp.jpa.entity.RoleM;
import com.th.jbp.jpa.entity.UserM;

public interface IUserService extends IService<UserM> {

	public ObjectResult<UserM> findByUsername(String username);

	public ObjectResult<RoleM> findRoleByUsername(String username);
	
	public ObjectResult<CompanyM> findCompanyByUsername(String username);
	
	public ObjectResult<ClassifierValueM> findBranchByUsername(String username);

	public ObjectResult<Boolean> updatePassword(String username, String password);
	
	public Page<UserM> lists(String firstName, String lastName, PageRequest pageRequest);

}
