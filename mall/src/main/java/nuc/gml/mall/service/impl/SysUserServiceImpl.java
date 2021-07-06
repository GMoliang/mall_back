/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package nuc.gml.mall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.consts.UserConst;
import nuc.gml.mall.dao.SpuDOMapper;
import nuc.gml.mall.dao.SysUserDOMapper;
import nuc.gml.mall.dao.SysUserRoleDOMapper;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.dataobject.SysUserDO;

import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.error.EmBusinessError;
import nuc.gml.mall.service.SysUserRoleService;
import nuc.gml.mall.service.SysUserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;



/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDOMapper, SysUserDO> implements SysUserService {

	@Autowired
	private SysUserDOMapper sysUserDOMapper;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Override
	@Transactional
	public void saveUser(SysUserDO user) throws BusinessException{
		System.out.println(user);

		//誰創建的 默认都是admin创建的
		user.setCreateUserId(1L);
		user.setStatus((byte) 1);
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		try {
			sysUserDOMapper.insert(user);
		} catch (DuplicateKeyException ex) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已重复注册");
		}

		System.out.println(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), UserConst.USER);
	}

	@Override
	public SysUserDO loginUser(String mobile, String password) throws  BusinessException{
		SysUserDO sysUserDO=sysUserDOMapper.selectOne(
				new QueryWrapper<SysUserDO>()
						.eq("mobile",mobile)
		);
		if (null==sysUserDO||!sysUserDO.getPassword().equals(new Sha256Hash(password, sysUserDO.getSalt()).toHex())) {
			throw  new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
		}
		if (sysUserDO.getStatus()!=1) {
			throw new BusinessException(EmBusinessError.USER_FORBID);
		}
        return sysUserDO;
	}



	@Override
	public void updatePassword(Long userId, String newPassword,String salt) {
		sysUserDOMapper.update(null,
				new UpdateWrapper<SysUserDO>()
				.eq("user_id",userId)
				.set("password",new Sha256Hash(newPassword, salt).toHex())
		);
		return ;
	}

}