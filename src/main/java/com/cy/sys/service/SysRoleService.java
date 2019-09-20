package com.cy.sys.service;

import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysRole;
import com.cy.sys.vo.SysRoleMenuVo;

public interface SysRoleService {
	PageObject<SysRole> findPageObjects(
			 String name,
			 Integer pageCurrent);
	
	int deleteRoleById(Integer id);
	
	/**修改前查询*/
	SysRoleMenuVo findObjectById(Integer id) ;
	
	int updateObject(SysRole entity,Integer[] menuIds);
	
	int saveObject(SysRole entity,Integer[] menuIds);
}
