package com.cy.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cy.common.vo.Node;
import com.cy.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
	List<SysMenu> findObjects();
	
	@Delete("delete from sys_menus where id=#{id}")
	int deleteObjectById(Integer id);
	
	int insertObject(SysMenu entity);
	
	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();

	@Select("select count(*) from sys_menus where parentId=#{parentId}")
	int getChildCount(Integer id);
}
