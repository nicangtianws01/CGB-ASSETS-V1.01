package com.cy.sys.service;

import java.util.List;

import com.cy.common.vo.Node;
import com.cy.sys.entity.SysMenu;

public interface SysMenuService {
	List<SysMenu> findObjects();
	int deleteObject(Integer id);
	int saveObject(SysMenu entity);
	List<Node> findZtreeMenuNodes();
}
