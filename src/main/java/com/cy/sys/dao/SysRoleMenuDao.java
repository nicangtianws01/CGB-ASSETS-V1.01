package com.cy.sys.dao;

import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
	
	int deleteObjectsByRoleId(Integer id);
	
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	int deleteObjectsByMenuId(Integer menuId);
=======
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {

        int deleteObjectsByRoleId(Integer id);

        int insertObjects(
                        @Param("roleId")Integer roleId,
                        @Param("menuIds")Integer[] menuIds);
        int deleteObjectsByMenuId(Integer menuId);
>>>>>>> tmp/master
}
