package com.tarena.tedu.cn.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao<T> {
	T findByName(Serializable name);
	
	T findById(Serializable id);
	
	List<T> findByPage (Map<String,Object> page);
	
	void update(T e);
	
	void update(Serializable id);
	
	//删除数据
	void delete(T e);
	
	void delete(Serializable id);
	//增加数据
	void insert(T e);
	
	
}
