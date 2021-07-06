package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.dao.SkuDOMapper;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuDOMapper, SkuDO> implements SkuService {
    @Autowired
    private SkuDOMapper skuDOMapper;

    @Override
    public List<SkuDO> getSkus(Integer spuId) {

        List<SkuDO> skus=skuDOMapper.selectList(
                new QueryWrapper<SkuDO>()
                .eq("spu_id",spuId)
                .eq("enable",1)
        );
        return skus;
    }

}
