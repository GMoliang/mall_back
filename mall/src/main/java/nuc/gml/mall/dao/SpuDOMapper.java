package nuc.gml.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.gml.mall.dataobject.SpuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpuDOMapper extends BaseMapper<SpuDO> {
    int deleteByPrimaryKey(Integer id);

    int insert(SpuDO record);

    int insertSelective(SpuDO record);

    SpuDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpuDO record);

    int updateByPrimaryKey(SpuDO record);

    List<SpuDO> selectSpuListByCid3(Integer cid);

    List<SpuDO> selectSpuListByShopId(Integer shopId);
}