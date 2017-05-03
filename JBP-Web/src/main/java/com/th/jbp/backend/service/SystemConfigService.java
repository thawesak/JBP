package com.th.jbp.backend.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;
import com.th.jbp.jpa.entity.SystemConfigM;
import com.th.jbp.jpa.repositories.SystemConfigRepository;


@Service("systemConfigService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SystemConfigService implements ISystemConfigService {

	@Autowired
	SystemConfigRepository repository;

	@Autowired
	EntityManager em;

	// private static final Logger LOGGER =
	// Logger.getLogger(SystemConfigService.class);

	@Override
	public ObjectResult<SystemConfigM> save(SystemConfigM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<SystemConfigM> delete(SystemConfigM entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<List<SystemConfigM>> lists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectResult<SystemConfigM> update(SystemConfigM entity) {
		ObjectResult<SystemConfigM> result = new ObjectResult<>();
		if (entity == null) {
			result = new ObjectResult<>(ObjectResult.FAIL);
			result.setDetail("entity is null");
		} else {
			repository.saveAndFlush(entity);
			result = new ObjectResult<>(ObjectResult.SUCCESS, entity);
		}
		return result;
	}

	@Override
	public ObjectResult<SystemConfigM> getByKey(SystemConfigM entity) {
		ObjectResult<SystemConfigM> result = new ObjectResult<>();
		SystemConfigM systemConfig = repository.findOne(entity.getSystemKey());
		if (systemConfig == null) {
			result = new ObjectResult<>(ObjectResult.DATA_NOT_FOUND);
		} else {
			result = new ObjectResult<>(ObjectResult.SUCCESS, systemConfig);
		}
		return result;
	}

}
