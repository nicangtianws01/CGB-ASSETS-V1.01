package com.cy.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.common.vo.JsonResult;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysRole;
import com.cy.sys.service.SysRoleService;
import com.cy.sys.vo.SysRoleMenuVo;

@RequestMapping("/role/")
@RestController
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		PageObject<SysRole> findPageObjects = sysRoleService.findPageObjects(name, pageCurrent);
		return new JsonResult(findPageObjects);
	}
	
	@RequestMapping("doDeleteRole")
	public JsonResult doDeleteRole(Integer id) {
		sysRoleService.deleteRoleById(id);
		return new JsonResult("delete OK");
	}
	
	/**修改前查询*/
	@RequestMapping("doFindObjectById")
	 public JsonResult doFindObjectById(Integer id){
		SysRoleMenuVo findObjectById = sysRoleService.findObjectById(id);
	    return new JsonResult(findObjectById);
	 }
	
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRole entity,Integer[] menuIds){
		sysRoleService.updateObject(entity,menuIds);
		return new JsonResult("update OK");
	}
	
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole entity,Integer[] menuIds){
		sysRoleService.saveObject(entity,menuIds);
		return new JsonResult("save OK");    
	}
	
}
