

package nuc.gml.mall.controller;

import com.baomidou.mybatisplus.extension.api.R;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.error.EmBusinessError;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.SysUserRoleService;
import nuc.gml.mall.service.SysUserService;
import nuc.gml.mall.service.model.PasswordModel;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	
	/**
	 * 注册用户
	 */
	@PostMapping("/register")
	public CommonReturnType save(@RequestBody SysUserDO user)throws BusinessException {
		//todo 保存用户
		sysUserService.saveUser(user);
		return CommonReturnType.create(null);
	}

	/**
	 * 用户登录
	 */
	@PostMapping("/login")
	public CommonReturnType mobileLogin(@RequestBody SysUserDO sysUserDO) throws BusinessException {
		SysUserDO sysUserDO2=sysUserService.loginUser(sysUserDO.getMobile(), sysUserDO.getPassword());

		//将登陆凭证加入到用户登陆成功的session内
		this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
		this.httpServletRequest.getSession().setAttribute("LOGIN_USER", sysUserDO2);

		return CommonReturnType.create(sysUserDO2);
	}
	
	/**
	 * 修改用户密码
	 */
	@PostMapping("/updatePassword")
	public CommonReturnType updatePassword(@RequestBody PasswordModel passwordModel)throws BusinessException{
		//todo 修改密码
		System.out.println(passwordModel);

		SysUserDO sysUserDO = (SysUserDO) httpServletRequest.getSession().getAttribute("LOGIN_USER");
		if (null==sysUserDO||!sysUserDO.getPassword().equals(new Sha256Hash(passwordModel.getOldPwd(), sysUserDO.getSalt()).toHex())) {
			throw  new BusinessException(EmBusinessError.USER_LOGIN_FAIL);
		}

		sysUserService.updatePassword(sysUserDO.getUserId(),passwordModel.getNewPwd(),sysUserDO.getSalt());

		return CommonReturnType.create(null);
	}

}
