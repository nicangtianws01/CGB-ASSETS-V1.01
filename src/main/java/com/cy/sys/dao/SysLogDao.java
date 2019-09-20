package com.cy.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cy.sys.entity.SysLog;


@Mapper
public interface SysLogDao {
	/**
	 * 基于条件分页查询日志信息
	 * @param username  查询条件(例如查询哪个用户的日志信息)
	 * @param startIndex 当前页的起始位置
	 * @param pageSize 当前页的页面大小
	 * @return 当前页的日志记录信息
	 * 数据库中每条日志信息封装到一个SysLog对象中
	 */
	public List<SysLog> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	public int getRowCount(@Param("username")String username);

	public int deleteObjects(@Param("ids")Integer...ids);
	
	

}
