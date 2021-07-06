package nuc.gml.mall.controller;

import nuc.gml.mall.controller.vo.ItemVO;
import nuc.gml.mall.controller.vo.SpuVO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller("item")
@RequestMapping("/item")
public class ItemController extends BaseController {
	@Autowired
	private ItemService itemService;

	//查询商品详情页
	@RequestMapping(value = "/getItem", method = {RequestMethod.GET})
	@ResponseBody
	@Transactional
	public CommonReturnType getItem(Integer spuId) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println(spuId);
		ItemVO itemVO=itemService.getItem(spuId);

		return CommonReturnType.create(itemVO);
	}

	//查询商品列表页
	@RequestMapping(value = "/getItemList", method = {RequestMethod.GET})
	@ResponseBody
	@Transactional
	public CommonReturnType getItemList(Integer cid3) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
		System.out.println("查询商品列表"+cid3);
		List<ItemVO> itemVOList=itemService.getItemList(cid3);

		return CommonReturnType.create(itemVOList);
	}



//
//	//查询商品列表
//	//todo  有一个问题 返回去没有价格字段 希望可以在前端存商品的时候就放在spec_Tmlp.json里
//	@RequestMapping(value = "/getItemList", method = {RequestMethod.GET})
//	@ResponseBody
//	public CommonReturnType getItemList(@RequestParam("cid3") Integer cid3,
//	                                    @RequestParam("pageNum") Integer pageNum,
//	                                    @RequestParam("pageSize") Integer pageSize) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
//
//		//todo  发布商品后spu.status因该==2  只有当管理员审核后才==1
//		//并且查询商品列表的sql中也限定了status=1
//		PageInfo<SpuDO> pageInfo = itemService.getItemList(cid3, pageNum, pageSize);
//
//		ItemListVO itemListVO = new ItemListVO();
//		itemListVO.setPageNum(pageInfo.getPageNum());
//		itemListVO.setPages(pageInfo.getPages());
//		itemListVO.setTotal(pageInfo.getTotal());
//		itemListVO.setPageSize(pageInfo.getPageSize());
//		itemListVO.setNavigatepageNums(pageInfo.getNavigatepageNums());
//
//		List<SpuVO> spuVOList = pageInfo.getList().stream().map(spuDO -> {
//			SpuVO spuVO = this.convertModelFromVO(spuDO);
//			return spuVO;
//		}).collect(Collectors.toList());
//
//		itemListVO.setItemVOList(spuVOList);
//
//		return CommonReturnType.create(itemListVO);
//	}
//

//
//	//商户查看已发布的商品
//	@RequestMapping(value = "/getItemListByShop", method = {RequestMethod.GET})
//	@ResponseBody
//	public CommonReturnType getItemListByShop(@RequestParam("shop_id") Integer shopId,
//	                                          @RequestParam("pageNum") Integer pageNum,
//	                                          @RequestParam("pageSize") Integer pageSize) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
//
//		PageItemVO pageItemVO = itemService.getItemListByShop(shopId, pageNum, pageSize);
//		return CommonReturnType.create(pageItemVO);
//	}


	private SpuVO convertModelFromVO(SpuDO spuDO) {
		SpuVO spuVO = new SpuVO();
		BeanUtils.copyProperties(spuDO, spuVO);
		return spuVO;
	}
}