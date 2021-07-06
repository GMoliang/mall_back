package nuc.gml.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.gml.mall.dataobject.AddressDO;
import nuc.gml.mall.dataobject.OrderDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDOMapper extends BaseMapper<OrderDO> {
}
