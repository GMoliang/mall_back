package nuc.gml.mall.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import nuc.gml.mall.controller.vo.ItemVO;

import nuc.gml.mall.dao.SpuDOMapper;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.error.BusinessException;

import nuc.gml.mall.service.ItemService;
import nuc.gml.mall.service.SkuService;
import nuc.gml.mall.service.SpuService;

import nuc.gml.mall.service.model.SkuModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl  implements ItemService {


	@Autowired
	private SpuService spuService;



	@Autowired
	private SkuService skuService;

	public ItemVO getItem(Integer spuId) throws BusinessException{

		ItemVO itemVO=new ItemVO();
		SpuDO spuDO=spuService.getById(spuId);
		//System.out.println(spuDO);
		List<SkuDO> skuDOList=skuService.getSkus(spuId);
		//System.out.println(skuDOList);
		BeanUtils.copyProperties(spuDO,itemVO);
		itemVO.setSkus(skuDOList);

		return itemVO;
	}

	public List<ItemVO> getItemList(Integer cid3) {

		List<ItemVO> itemVOList=new ArrayList<>();

		List<SpuDO> spuDOList=spuService.getSpuListByCid3(cid3);

		for (SpuDO spuDO : spuDOList){
			ItemVO itemVO=new ItemVO();
			BeanUtils.copyProperties(spuDO,itemVO);
			List<SkuDO> skuDOList=skuService.getSkus(spuDO.getId());
			itemVO.setSkus(skuDOList);
			itemVOList.add(itemVO);
		}

		return itemVOList;
	}

//	@Autowired
//	private SpuDOMapper spuDOMapper;
//
//	@Autowired
//	private SkuDOMapper skuDOMapper;




//	@Override
//	public PageInfo<SpuDO> getItemList(Integer cid3, Integer pageNum, Integer pageSize) throws BusinessException {
//
//		PageHelper.startPage(pageNum, pageSize);
//		//todo 这里的sql查询条件限制了spu.state=1的情况 因该有管理员审核
//		List<SpuDO> spuDOList = spuDOMapper.selectSpuListByCid3(cid3);
//		PageInfo<SpuDO> pageInfo = new PageInfo<SpuDO>(spuDOList);
//
//		return pageInfo;
//	}
//
//	@Override
//	public ItemModel getItem(Integer spuId) {
//		ItemModel itemModel = new ItemModel();
//
//		SpuDO spuDO = spuDOMapper.selectByPrimaryKey(spuId);
//		BeanUtils.copyProperties(spuDO, itemModel);
//
//		List<SkuDO> skuDOList = skuDOMapper.selectSkusBySpuId(spuId);
//
//		System.out.println(skuDOList);
//		List<SkuModel> skuModelList = skuDOList.stream().map(skuDO -> {
//			SkuModel skuModel = this.convertModelFromDataObject(skuDO);
//			return skuModel;
//		}).collect(Collectors.toList());
//
//
//		itemModel.setSkus(skuModelList);
//
//		return itemModel;
//	}
//


	//根据店铺Id得到商品spu,sku信息
//	@Override
//	@Transactional
//	public PageItemVO getItemListByShop(Integer shopId, Integer pageNum, Integer pageSize) throws BusinessException {
//		PageHelper.startPage(pageNum, pageSize);
//		//todo 这里的sql查询条件限制了spu.state=1的情况 因该有管理员审核
//		List<SpuDO> spuDOList = spuDOMapper.selectSpuListByShopId(shopId);
//		PageInfo<SpuDO> pageInfo = new PageInfo<SpuDO>(spuDOList);
//
//		System.out.println(pageInfo);
//
//		PageItemVO pageItemVO = new PageItemVO();
//		pageItemVO.setPageNum(pageInfo.getPageNum());
//		pageItemVO.setPages(pageInfo.getPages());
//		pageItemVO.setPageSize(pageInfo.getPageSize());
//		pageItemVO.setTotal(pageInfo.getTotal());
//		pageItemVO.setNavigatepageNums(pageInfo.getNavigatepageNums());
//		List<ItemVO> itemVOList = new ArrayList<>();
//		for (SpuDO spu : pageInfo.getList()) {
//			List<SkuDO> skuDOList = skuDOMapper.selectSkusBySpuId(spu.getId());
//
//			List<SkuModel> skuModelList = skuDOList.stream().map(skuDO -> {
//				SkuModel skuModel = this.convertModelFromDataObject(skuDO);
//				return skuModel;
//			}).collect(Collectors.toList());
//
//			ItemVO itemVO = new ItemVO();
//			BeanUtils.copyProperties(spu, itemVO);
//
//			itemVO.setSkus(skuModelList);
//			itemVOList.add(itemVO);
//		}
//		pageItemVO.setItemVOList(itemVOList);
//
//		return pageItemVO;
//	}

	private SkuModel convertModelFromDataObject(SkuDO skuDO) {
		SkuModel skuModel = new SkuModel();
		BeanUtils.copyProperties(skuDO, skuModel);
		return skuModel;
	}


	private SkuDO convertFromModel(SkuModel skuModel) {
		if (skuModel == null) {
			return null;
		}
		SkuDO skuDO = new SkuDO();
		BeanUtils.copyProperties(skuModel, skuDO);
		return skuDO;
	}

//	private ItemModel convertModelFromDataObject(SpuDO spuDO) {
//		ItemModel itemModel = new ItemModel();
//		BeanUtils.copyProperties(spuDO, itemModel);
//		return itemModel;
//	}
}
