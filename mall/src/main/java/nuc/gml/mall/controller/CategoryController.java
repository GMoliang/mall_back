package nuc.gml.mall.controller;


import nuc.gml.mall.dataobject.CategoryModel;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.MallCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-15 19:22:37
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private MallCategoryService mallCategoryService;


    @GetMapping("/listing")
    public CommonReturnType selectAll() {
        List<CategoryModel> categoryModelList = mallCategoryService.selectAll();
        return CommonReturnType.create(categoryModelList);

    }


}
