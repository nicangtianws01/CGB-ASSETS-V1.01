package com.cy.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
	int deleteObjectsByMenuId(Integer menuId);
	
	int deleteObjectsByRoleId(Integer id);
	
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
}
