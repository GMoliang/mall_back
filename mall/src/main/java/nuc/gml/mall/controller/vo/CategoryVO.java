package nuc.gml.mall.controller.vo;

import java.util.List;

public class CategoryVO {
    private Integer id;

    private Integer parentId;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<CategoryVO> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryVO> subCategories) {
        this.subCategories = subCategories;
    }

    private Integer sortOrder;

    private List<CategoryVO> subCategories;
}
