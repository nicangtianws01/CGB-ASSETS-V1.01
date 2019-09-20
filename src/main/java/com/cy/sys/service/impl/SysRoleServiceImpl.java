package com.cy.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.PageObject;
import com.cy.sys.dao.SysRoleDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.dao.SysUserRoleDao;
import com.cy.sys.entity.SysRole;
import com.cy.sys.service.SysRoleService;
import com.cy.sys.vo.SysRoleMenuVo;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;

	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent<1) {
			throw new IllegalArgumentException("当前页码有误");
		}
		int rowCount = sysRoleDao.getRowCount(name);
		if (rowCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		int pageSize=2;
		int startIndex = (pageCurrent-1)*pageSize;
		List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);
		PageObject<SysRole> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}

	@Override
	public int deleteRoleById(Integer id) {
		if (id == null || id<1) {
			throw new ServiceException("id值错误");
		}
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		int rows = sysRoleDao.deleteRoleById(id);
		if(rows == 0) {
			throw new ServiceException("数据已经不存在");
		}
		return rows;
	}

	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		if (id == null || id <1) {
			throw new ServiceException("id值不合法");
		}
		SysRoleMenuVo findObjectById = sysRoleDao.findObjectById(id);
		if (findObjectById == null) {
			throw new ServiceException("此记录已经不存在");
		}
		return findObjectById;
	}

	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
    	if(entity==null)
    throw new ServiceException("更新的对象不能为空");
    	if(entity.getId()==null)
    	throw new ServiceException("id的值不能为空");
    	
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色指定一个权限");
    	
    	//2.更新数据
    	int rows=sysRoleDao.updateObject(entity);
    	if(rows==0)
    		throw new ServiceException("对象可能已经不存在");
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
 
    	//3.返回结果
    	return rows;
	}

	@Override
	public int saveObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
    	if(entity==null)
    		throw new ServiceException("保存数据不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    		throw new ServiceException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    		throw new ServiceException("必须为角色赋予权限");
    	//2.保存数据
    	int rows=sysRoleDao.insertObject(entity);
    	sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
    	//3.返回结果
    	return rows;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
