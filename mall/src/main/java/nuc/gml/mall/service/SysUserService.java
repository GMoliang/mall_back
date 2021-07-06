/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.error.BusinessException;


public interface SysUserService  extends IService<SysUserDO> {

	/**
	 * 保存用户
	 */
	void saveUser(SysUserDO userDO) throws BusinessException;

	/**
	 * 用户登录
	 */
	SysUserDO loginUser(String mobile, String password) throws BusinessException;


	/**
	 * 修改密码
	 * @param userId       用户ID
	 * @param newPassword  新密码
	 */
	void updatePassword(Long userId, String newPassword,String salt);
}
