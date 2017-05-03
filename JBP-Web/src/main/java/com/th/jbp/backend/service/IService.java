package com.th.jbp.backend.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.backend.entity.ObjectResult;

public interface IService<T> {
	@Transactional(readOnly=true)
	public ObjectResult<T> getByKey(T entity);
	
	@Transactional(readOnly=true)
	public ObjectResult<List<T>> lists();

	public ObjectResult<T> save(T entity);
	
	public ObjectResult<T> update(T entity);

	public ObjectResult<T> delete(T entity);

}
