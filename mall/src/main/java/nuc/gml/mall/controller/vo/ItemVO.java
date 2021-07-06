package nuc.gml.mall.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.service.model.SkuModel;

import java.util.Date;
import java.util.List;

@Data
public class ItemVO {
	private Integer id; //spuId

	private Integer shopId;

	private String title;

	private String subTitle;

	private Integer cid1;

	private Integer cid2;

	private Integer cid3;

	private String cname;

	private String mainImage;

	private String subImages;

	private String description;

	private String specifications;

	private String specTemplate;

	private String province;

	private String city;

	private String area;

	private String address;

	private Integer status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//后台输出到前台
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//后台输出到前台
	private Date updateTime;

	private List<SkuDO> skus;
}
