package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.CartDO;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.service.model.CartModel;

import java.util.List;

public interface CartService extends IService<CartDO> {

	void addCart(CartDO cartDO) throws  BusinessException;

	List<CartModel> getCardList(Long userId);

	void addItemCount(CartDO cartDO);

	void deleteCartItem(Integer cartId);

	/**
	 * 根据id的集合查询购物车商品的集合
	 * @Param: cartId
	 * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
	 **/
	CartModel getCartById(Integer cartId);

	/**
	 * 根据id的集合查询购物车商品的集合
	 * @Param: cartId
	 * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
	 **/
	List<CartModel> getCartListByIds(Integer[] cartId);
}
