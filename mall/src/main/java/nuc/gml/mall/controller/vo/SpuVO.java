package nuc.gml.mall.controller.vo;

import lombok.Data;

//商品列表页不需要那么多字段
@Data
public class SpuVO {
	private Integer id; //spuId

	private Integer shopId;

	private String title;

	private Integer cid1;

	private Integer cid2;

	private Integer cid3;

	private String mainImage;

	//这里希望能存入所有规格的最低价，单位
	private String specTemplate;

	private String province;

	private String city;


}
