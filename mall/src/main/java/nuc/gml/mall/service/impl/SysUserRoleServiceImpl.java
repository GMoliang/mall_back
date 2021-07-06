/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package nuc.gml.mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.dao.SysUserDOMapper;
import nuc.gml.mall.dao.SysUserRoleDOMapper;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.dataobject.SysUserRoleDO;
import nuc.gml.mall.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("sysUserRoleService")
public class SysUserRoleServiceImpl  extends ServiceImpl<SysUserRoleDOMapper, SysUserRoleDO> implements SysUserRoleService {

	@Autowired
	private SysUserRoleDOMapper sysUserRoleDOMapper;

	@Override
	public void saveOrUpdate(Long userId,  Long roleId) {
		SysUserRoleDO sysUserRoleDO=new SysUserRoleDO();
		sysUserRoleDO.setUserId(userId);
		sysUserRoleDO.setRoleId(roleId);
		//保存用户与角色关系
		sysUserRoleDOMapper.insert(sysUserRoleDO);
		return ;
	}

}
