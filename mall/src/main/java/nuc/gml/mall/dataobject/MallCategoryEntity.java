package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-05-15 19:22:37
 */
@Data
@TableName("mall_category")
public class MallCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 类目id
	 */
	@TableId
	private Integer id;
	/**
	 * 类目名称
	 */
	private String name;
	/**
	 * 父类目id,顶级类目填0
	 */
	private Integer parentId;

	/**
	 * 排序指数，越小越靠前
	 */
	private Integer sortOrder;
	/**
	 *
	 */
	private Date createTime;
	/**
	 *
	 */
	private Date updateTime;
	/**
	 *
	 */
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
