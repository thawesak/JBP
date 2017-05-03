package com.th.jbp.backend.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.enums.Status;
import com.th.jbp.jpa.entity.ClassifierValueM;
import com.th.jbp.jpa.entity.CompanyM;
import com.th.jbp.jpa.entity.RoleM;
import com.th.jbp.jpa.entity.UserM;
import com.th.jbp.jpa.repositories.ClassifierValueRepository;
import com.th.jbp.jpa.repositories.RoleRepository;
import com.th.jbp.jpa.repositories.SystemConfigRepository;
import com.th.jbp.jpa.repositories.UserRepository;

@Service("userService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class UserService implements IUserService {

	@Autowired
	UserRepository repository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ClassifierValueRepository classifierValueRepository;

	@Autowired
	SystemConfigRepository systemConfigRepository;

	@Autowired
	EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(UserService.class);

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<UserM> getByKey(UserM entity) {
		ObjectResult<UserM> result = new ObjectResult<>();
		UserM userM = repository.findOne(entity.getUserId());
		if (userM == null || entity == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, userM);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<List<UserM>> lists() {
		ObjectResult<List<UserM>> result = new ObjectResult<>();
		List<UserM> items = repository.findAll();
		if (items == null) {
			result = new ObjectResult<>(ObjectResult.SUCCESS, new ArrayList<>(0));
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, items);
		}
		return result;
	}

	@Override
	public ObjectResult<UserM> save(UserM entity) {
		ObjectResult<UserM> result = new ObjectResult<>();
		if (entity == null || entity.getUserId() != null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null.");
		} else {
			Integer running = repository.findRunning();
			entity.setUserId(Long.valueOf(running));
			entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
			repository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<UserM> update(UserM entity) {
		ObjectResult<UserM> result = new ObjectResult<>();
		if (entity == null || entity.getUserId() == null || entity.getUserId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			repository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<UserM> delete(UserM entity) {
		ObjectResult<UserM> result = new ObjectResult<>();
		if (entity == null || entity.getUserId() == null || entity.getUserId().equals(0L)) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity or id is null.");
		} else {
			ClassifierValueM status = classifierValueRepository.findOne(Status.DELETED.getId());
			entity.setStatus(status);
			entity.setUpdateDate(new Timestamp(System.currentTimeMillis()));
			repository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public ObjectResult<UserM> findByUsername(String username) {
		ObjectResult<UserM> result = new ObjectResult<>();
		UserM userM = repository.findByUsername(username);
		if (userM == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, userM);
		}
		return result;
	}

	@Override
	public ObjectResult<RoleM> findRoleByUsername(String username) {
		ObjectResult<RoleM> result = new ObjectResult<>();
		UserM user = repository.findByUsername(username);
		if (user == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			RoleM role = user.getRoles().iterator().next();
			result = new ObjectResult<>(ObjectResult.SUCCESS, role);
		}
		return result;
	}

	@Override
	public ObjectResult<Boolean> updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserM> lists(String firstName, String lastName, PageRequest pageRequest) {
		LOGGER.debug("firstName = "+firstName+" , lastName = "+lastName+" , pageRequest = "+pageRequest);
		Page<UserM> results = null;
		if(StringUtils.isNotEmpty(firstName) && StringUtils.isNotEmpty(lastName)){
			results = repository.findByFirstNameAndLastName("%"+firstName+"%", "%"+lastName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(firstName)){
			results = repository.findByFirstName("%"+firstName+"%", pageRequest);
		}else if(StringUtils.isNotEmpty(lastName)){
			results = repository.findByLastName("%"+lastName+"%", pageRequest);
		}else{
			results = repository.findAll(pageRequest);
		}
		
		return results;
	}

	@Override
	public ObjectResult<CompanyM> findCompanyByUsername(String username) {
		ObjectResult<CompanyM> result = new ObjectResult<>();
		UserM user = repository.findByUsername(username);
		if (user == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			CompanyM company = user.getCompany();
			result = new ObjectResult<>(ObjectResult.SUCCESS, company);
		}
		return result;
	}

	@Override
	public ObjectResult<ClassifierValueM> findBranchByUsername(String username) {
		ObjectResult<ClassifierValueM> result = new ObjectResult<>();
		UserM user = repository.findByUsername(username);
		if (user == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			ClassifierValueM branch = user.getBranch();
			result = new ObjectResult<>(ObjectResult.SUCCESS, branch);
		}
		return result;
	}

}
