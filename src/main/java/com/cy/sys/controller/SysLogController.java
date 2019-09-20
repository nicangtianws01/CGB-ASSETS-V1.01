package com.cy.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cy.common.vo.JsonResult;
import com.cy.common.vo.PageObject;
import com.cy.sys.entity.SysLog;
import com.cy.sys.service.SysLogService;


//@Controller
//@ResponseBody
@RestController
@RequestMapping("/log/")
public class SysLogController {
	
	@Autowired
	private SysLogService sysLogService;
	
	@GetMapping("doFindPageObjects")
//	@ResponseBody
	public JsonResult doFindPageObjects(String username,@RequestParam Integer pageCurrent) {
		PageObject<SysLog> findPageObjects = sysLogService.findPageObjects(username, pageCurrent);
		return new JsonResult(findPageObjects);
	}
	
	@RequestMapping("doDeleteObjects")
//	@ResponseBody
	public JsonResult doDeleteObjects(Integer...ids) {
		sysLogService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
}
