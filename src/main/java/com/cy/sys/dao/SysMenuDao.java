package com.cy.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cy.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	List<SysMenu> findObjects();
	
	int deleteObjectsByRoleId(Integer roleId);
}
