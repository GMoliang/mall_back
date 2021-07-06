package nuc.gml.mall.controller;

import nuc.gml.mall.controller.vo.CartVO;
import nuc.gml.mall.dataobject.CartDO;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.CartService;
import nuc.gml.mall.service.model.CartModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Controller("cart")
@RequestMapping("/cart")
public class CartController extends BaseController {

	@Autowired
	private CartService cartService;

	@Autowired
	private HttpServletRequest httpServletRequest;
	//添加购物车
	@RequestMapping(value = "/addCartItem", method = {RequestMethod.POST})
	@ResponseBody
	public CommonReturnType addCartItem(@RequestBody CartDO cartDO) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
		//获取用户的登陆信息
		SysUserDO sysUserDO = (SysUserDO) httpServletRequest.getSession().getAttribute("LOGIN_USER");

//		System.out.println(sysUserDO);
//		System.out.println(cartDO);

		cartService.addCart(cartDO);
		return CommonReturnType.create(null);
	}

	//获取购物车列表
	//todo 需要分页
	@RequestMapping(value = "/selectCart", method = {RequestMethod.GET})
	@ResponseBody
	public CommonReturnType getCartList( Long  userId) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
		//获取用户的登陆信息
//        userModel userModel = (UserModel) httpServletRequest.getSession().getAttribute("LOGIN_USER");

		List<CartModel> cartModelList = cartService.getCardList(userId);

//		System.out.println(cartModelList);
		return CommonReturnType.create(cartModelList);
	}

	//	//购物车删除一项
	@RequestMapping(value = "deleteCartItem", method = RequestMethod.POST)
	@ResponseBody
	public CommonReturnType deleteCartItem(@RequestBody Integer cartId) throws BusinessException {
//		System.out.println(cartId);
		cartService.deleteCartItem(cartId);
		return CommonReturnType.create(null);
	}

	//购物车增加数量
	@RequestMapping(value = "/addItemCount", method = {RequestMethod.POST})
	@ResponseBody
	public CommonReturnType addItemCount(@RequestBody CartDO cartDO) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

		cartService.addItemCount(cartDO);

		return CommonReturnType.create(null);
	}



	private CartVO convertVOFromModel(CartModel cartModel) {
		if (cartModel == null) {
			return null;
		}
		CartVO cartVO = new CartVO();
		BeanUtils.copyProperties(cartModel, cartVO);
		return cartVO;
	}
}
