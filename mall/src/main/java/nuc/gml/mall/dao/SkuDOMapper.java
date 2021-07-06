package nuc.gml.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.gml.mall.dataobject.SkuDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SkuDOMapper extends BaseMapper<SkuDO> {

}