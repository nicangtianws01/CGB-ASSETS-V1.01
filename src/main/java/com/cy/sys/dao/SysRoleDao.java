package com.cy.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.sys.entity.SysRole;
import com.cy.sys.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {
	
	List<SysRole> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("name")String name);
	
	int deleteRoleById(Integer id);
	
	/**修改时先查询要修改的职位并且显示出来*/
	SysRoleMenuVo findObjectById(Integer id);
	
	int updateObject(SysRole entity);
	
	int insertObject(SysRole entity);
	
}
