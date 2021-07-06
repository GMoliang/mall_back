package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.ShopDO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.service.model.ShopModel;

public interface ShopService extends IService<ShopDO> {

   void registerShop(ShopDO shopDO)  throws BusinessException;

}
