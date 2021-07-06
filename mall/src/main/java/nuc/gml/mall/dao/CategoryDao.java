package nuc.gml.mall.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.gml.mall.dataobject.MallCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-15 19:22:37
 */
@Mapper
public interface CategoryDao extends BaseMapper<MallCategoryEntity> {
	
}
