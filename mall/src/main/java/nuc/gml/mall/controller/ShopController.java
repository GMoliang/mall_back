package nuc.gml.mall.controller;

import nuc.gml.mall.controller.vo.ShopVO;
import nuc.gml.mall.dataobject.ShopDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.ShopService;
import nuc.gml.mall.service.model.ShopModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Controller("shop")
@RequestMapping("/shop")
public class ShopController extends BaseController {

	@Autowired
	private ShopService shopService;


	/*
	管理员审核店铺接口
   注册上得由管理员审核  通过后置status=1
	*/
	//用户注册
	@RequestMapping(value = "/registerShop", method = {RequestMethod.POST})
	@ResponseBody
	public CommonReturnType registerShop(@RequestBody ShopDO shopDO) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println(shopDO);
		shopService.registerShop(shopDO);
		return CommonReturnType.create(null);
	}


}
