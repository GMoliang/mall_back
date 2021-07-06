package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import nuc.gml.mall.controller.vo.ItemVO;
import nuc.gml.mall.controller.vo.PageItemVO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    ItemVO getItem(Integer spuId) throws  BusinessException;

    List<ItemVO> getItemList(Integer cid3);

//	PageInfo<SpuDO> getItemList(Integer cid3, Integer pageNum, Integer pageSize) throws BusinessException;

//	PageItemVO getItemListByShop(Integer shopId, Integer pageNum, Integer pageSize) throws BusinessException;
}
