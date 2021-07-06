package nuc.gml.mall.service;


import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.CategoryModel;
import nuc.gml.mall.dataobject.MallCategoryEntity;


import java.util.List;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-15 19:22:37
 */
public interface MallCategoryService extends IService<MallCategoryEntity> {

    public List<CategoryModel> selectAll();

}

