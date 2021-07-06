package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.dao.CartDOMapper;
import nuc.gml.mall.dao.SkuDOMapper;
import nuc.gml.mall.dao.SysUserDOMapper;
import nuc.gml.mall.dataobject.*;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.error.EmBusinessError;
import nuc.gml.mall.service.CartService;
import nuc.gml.mall.service.SkuService;
import nuc.gml.mall.service.SpuService;
import nuc.gml.mall.service.SysUserService;
import nuc.gml.mall.service.model.CartModel;
import nuc.gml.mall.service.model.SpuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl extends ServiceImpl<CartDOMapper, CartDO> implements CartService {

	@Autowired
	private CartDOMapper cartDOMapper;

	@Autowired
	private SpuService spuService;

	@Autowired
	private SkuService skuService;

	@Override
	public void addCart(CartDO cartDO) throws BusinessException {
		cartDO.setCreateTime(new Date());
		try {
			cartDOMapper.insert(cartDO);
		} catch (DuplicateKeyException ex) {
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "该商品重复存入购物车");
		}
		return ;
	}

	@Override
	public List<CartModel> getCardList(Long userId) {
		List<CartDO> cartDOList = cartDOMapper.selectList(
				new QueryWrapper<CartDO>()
						.eq("user_id", userId));

		List<CartModel>  cartModels = new ArrayList<>();
		cartDOList.forEach((cartDO -> {
			CartModel cartModel = convertFromModel(cartDO);
			if (cartModel!=null) {
				cartModels.add(cartModel);
			}
		}));
		return cartModels;
	}

	//	@Override
	public void deleteCartItem(Integer cartId) {
		cartDOMapper.deleteById(cartId);
		return;
	}

	@Override
	public void addItemCount(CartDO cartDO)  {
		cartDOMapper.updateById(cartDO);
		return;
	}


	/**
	 * 根据id的集合查询购物车商品的集合
	 *
	 * @param cartId
	 * @return java.util.List<nuc.rwenjie.modules.sys.service.model.CartModel>
	 * @Param: cartId
	 */
	@Override
	public CartModel getCartById(Integer cartId) {
		CartDO cartDO = cartDOMapper.selectById(cartId);
		return convertFromModel(cartDO);
	}

	//根据id的集合查询购物车商品的集合
	@Override
	public List<CartModel> getCartListByIds(Integer[] cartId) {
		List<CartModel> list = new ArrayList<>();
		for (Integer aLong : cartId) {
			CartModel cartModel = getCartById(aLong);
			list.add(cartModel);
		}
		return list;
	}

	private CartModel convertFromModel(CartDO cartDO){
		if (cartDO == null){
			return null;
		}
		CartModel cartModel = new CartModel();
		BeanUtils.copyProperties(cartDO, cartModel);
		SpuDO spuDO=spuService.getById(cartDO.getItemId());
		SkuDO skuDO=skuService.getById(cartDO.getSkuId());
	    cartModel.setSpu(spuDO);
		cartModel.setSku(skuDO);
		return cartModel;
	}

//	@Autowired
//	private CartDOMapper cartDOMapper;
//
//	@Autowired
//	private SkuDOMapper skuDOMapper;
//
//	@Override
//	public void addCart(CartModel cartModel) throws BusinessException {
//		if (cartModel == null) {
//			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
//		}
//		CartDO cartDO = new CartDO();
//		BeanUtils.copyProperties(cartModel, cartDO);
//		cartDO.setCreateTime(new Date());
//		try {
//			cartDOMapper.insert(cartDO);
//		} catch (DuplicateKeyException ex) {
//			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "该商品重复存入购物车");
//		}
//
//		return;
//	}
//
//	@Override
//	public List<CartModel> getCardList(Integer userId) {
//		List<CartDO> cartDOList = cartDOMapper.selectByUserId(userId);
//
//		List<CartModel> cartModelList = cartDOList.stream().map(cartDO -> {
//			CartModel cartModel = convertModelFromDO(cartDO);
//			return cartModel;
//		}).collect(Collectors.toList());
//
//
//		return cartModelList;
//	}
//




	private CartModel convertModelFromDO(CartDO cartDO) {
		if (cartDO == null) {
			return null;
		}
		CartModel cartModel = new CartModel();
		BeanUtils.copyProperties(cartDO, cartModel);
		return cartModel;
	}


}
