package nuc.gml.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.gml.mall.dataobject.AddressDO;
import nuc.gml.mall.dataobject.ShopDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ShopDOMapper extends BaseMapper<ShopDO> {
}