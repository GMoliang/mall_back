package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.SpuDO;

import java.util.List;

public interface SpuService extends IService<SpuDO> {

    List<SpuDO> getSpuListByCid3(Integer cid3);
}
