package com.cy.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.common.exception.ServiceException;
import com.cy.common.vo.Node;
import com.cy.sys.dao.SysMenuDao;
import com.cy.sys.dao.SysRoleMenuDao;
import com.cy.sys.entity.SysMenu;
import com.cy.sys.service.SysMenuService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService{

	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Override
	public List<SysMenu> findObjects() {
		List<SysMenu> list = sysMenuDao.findObjects();
		if(list == null || list.size() == 0) {
			log.error("菜单查询出错");
			throw new ServiceException("没有找到记录");
		}
		return list;
	}
	@Override
	public int deleteObject(Integer id) {
		//1.判断参数是否有效
		if(id == null || id < 0) {
			throw new IllegalArgumentException("参数错误：id="+id);
		}
		//2.删除子节点
		int childRows = sysMenuDao.getChildCount(id);
		//3.判断是否有子节点
		if(childRows > 0) {
			throw new ServiceException("请先删除子节点");
		}
		//4.执行删除操作
		int rows = sysMenuDao.deleteObjectById(id);
		//5.判断删除是否成功
		if(rows == 0) {
			throw new ServiceException("删除失败，请稍后重试");
		}
		//6.删除角色表相关数据
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		return rows;
	}
	@Override
	public int saveObject(SysMenu entity) {
		//1.判断用户信息是否完整
		if(entity == null) {
			throw new IllegalArgumentException("用户信息不能为空");
		}
		if(StringUtils.isEmpty(entity.getName())) {
			throw new IllegalArgumentException("菜单名称不能为空");
		}
		
		//2.设置创建者
		entity.setCreatedUser("admin");
		entity.setModifiedUser("admin");
		
		//3.插入数据
		int rows = sysMenuDao.insertObject(entity);
		//4.判断是否创建成功
		if(rows == 0) {
			throw new ServiceException("创建失败，请稍后重试");
		}
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}

}
