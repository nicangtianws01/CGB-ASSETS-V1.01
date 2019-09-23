package com.cy.sys.service;

import java.util.List;

import com.cy.common.vo.PageObject;
import com.cy.common.vo.CheckBox;
import com.cy.sys.entity.SysRole;
import com.cy.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	/**
	 * 查询所有角色信息
	 * @return
	 */
	List<CheckBox> findRoles();
	/**
	 * 基于角色id查询角色名称，备注以及角色对应的菜单id
	 * @param id
	 * @return
	 */
	 SysRoleMenuVo findObjectById(Integer id);
	PageObject<SysRole> findPageObjects(
			 String name,
			 Integer pageCurrent);
	
	int deleteRoleById(Integer id);
//	
//	/**修改前查询*/
//	SysRoleMenuVo findObjectById(Integer id) ;
	
	int updateObject(SysRole entity,Integer[] menuIds);
	
	int saveObject(SysRole entity,Integer[] menuIds);

}
