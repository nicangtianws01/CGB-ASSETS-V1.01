package com.cy.sys.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserRoleDao {
	
	int deleteObjectsByRoleId(Integer id);
	
}
