package nuc.gml.mall.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ItemModel {
	private Integer id; //spuId

	@JsonProperty(value = "shop_id")
	private Integer shopId;

	private String title;

	@JsonProperty(value = "sub_title")
	private String subTitle;

	private Integer cid1;

	private Integer cid2;

	private Integer cid3;

	@JsonProperty(value = "main_image")
	private String mainImage;

	@JsonProperty(value = "sub_images")
	private String subImages;

	private String description;

	private String specifications;

	private String specTemplate;

	private String province;

	private String city;

	private String area;

	private String address;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	private List<SkuModel> skus;

	//当前页
	private int pageNum;
	//每页的数量
	private int pageSize;
	//当前页的数量
	private int size;
	//总记录数
	private long total;
	//总页数
	private int pages;
	//第一页
	private int firstPage;
	//前一页
	private int prePage;
	//是否为第一页
	private boolean isFirstPage = false;
	//是否为最后一页
	private boolean isLastPage = false;
	//是否有前一页
	private boolean hasPreviousPage = false;
	//是否有下一页
	private boolean hasNextPage = false;
	//导航页码数
	private int navigatePages;
	//所有导航页号
	private int[] navigatepageNums;
}
