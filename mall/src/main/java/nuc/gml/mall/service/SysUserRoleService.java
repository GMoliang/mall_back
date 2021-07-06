/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package nuc.gml.mall.service;



public interface SysUserRoleService {

	//保存用户角色的关系
	void saveOrUpdate(Long userId, Long roleId);

}
