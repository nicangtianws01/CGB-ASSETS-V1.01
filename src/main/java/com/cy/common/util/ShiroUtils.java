package com.cy.common.util;
import org.apache.shiro.SecurityUtils;

import com.cy.sys.entity.SysUser;
public class ShiroUtils {
	 /**获取登录用户*/
	 public static String getUsername() {
    	 return getLoginUser().getUsername();
	 }
	 public static SysUser getLoginUser() {
		 SysUser user=(SysUser)
				 SecurityUtils.getSubject().getPrincipal();
		 return user;
	 }
}





