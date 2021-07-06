package nuc.gml.mall.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.dao.CategoryDao;
import nuc.gml.mall.dataobject.CategoryModel;
import nuc.gml.mall.dataobject.MallCategoryEntity;
import nuc.gml.mall.service.MallCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service("mallCategoryService")
public class MallCategoryServiceImpl extends ServiceImpl<CategoryDao, MallCategoryEntity> implements MallCategoryService {


   @Autowired
   private MallCategoryService mallCategoryService;

    public List<CategoryModel> selectAll() {

        List<CategoryModel> categoryModelList = new ArrayList<>();

        List<MallCategoryEntity> categories = mallCategoryService.list();

        //查出parent_id=0
        for (MallCategoryEntity category : categories) {
            if (category.getParentId().equals(0)) {
                CategoryModel categoryModel = new CategoryModel();
                BeanUtils.copyProperties(category, categoryModel);
                categoryModel.setValue(category.getId());
                categoryModel.setLabel(category.getName());
                categoryModelList.add(categoryModel);
            }
        }
        categoryModelList.sort(Comparator.comparing(CategoryModel::getSortOrder).reversed());

        //查询子目录
        findSubCategory(categoryModelList, categories);

        return categoryModelList;
    }


    private void findSubCategory(List<CategoryModel> categoryModelList, List<MallCategoryEntity> categories) {
        for (CategoryModel categoryModel : categoryModelList) {
            List<CategoryModel> subCategoryModelList = new ArrayList<>();

            for (MallCategoryEntity category : categories) {
                //如果查到内容，设置subCategory, 继续往下查
                if (categoryModel.getId().equals(category.getParentId())) {
                    CategoryModel subCategoryModel = convertFromDataObject(category);
                    subCategoryModelList.add(subCategoryModel);
                }

                subCategoryModelList.sort(Comparator.comparing(CategoryModel::getSortOrder).reversed());
                categoryModel.setChildren(subCategoryModelList);

                findSubCategory(subCategoryModelList, categories);
            }
        }
    }

    private CategoryModel convertFromDataObject(MallCategoryEntity category) {
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(category, categoryModel);
        categoryModel.setValue(category.getId());
        categoryModel.setLabel(category.getName());
        return categoryModel;
    }
}