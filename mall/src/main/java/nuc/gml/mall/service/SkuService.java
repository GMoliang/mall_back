package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.SkuDO;

import java.util.List;


public interface SkuService extends IService<SkuDO> {

    List<SkuDO > getSkus(Integer spuId);
}
