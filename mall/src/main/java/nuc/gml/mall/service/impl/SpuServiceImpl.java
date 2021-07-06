package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.dao.SpuDOMapper;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuDOMapper, SpuDO> implements SpuService {

    @Autowired
    private SpuDOMapper spuDOMapper;

    @Override
    public List<SpuDO> getSpuListByCid3(Integer cid3) {

        List<SpuDO> spuDOList=spuDOMapper.selectList(
                new QueryWrapper<SpuDO>()
                .eq("cid3",cid3)
                .eq("status",1)
        );
        return spuDOList;
    }
}
