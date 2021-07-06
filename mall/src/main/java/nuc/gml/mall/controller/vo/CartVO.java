package nuc.gml.mall.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CartVO {
	@JsonProperty(value = "cart_id")
	private Integer cartId;
	@JsonProperty(value = "shop_id")
	private Integer shopId;
	@JsonProperty(value = "item_id")
	private Integer itemId;
	@JsonProperty(value = "sku_id")
	private Integer skuId;

	private String title;
	@JsonProperty(value = "main_image")
	private String mainImage;

	private Integer count;

	private BigDecimal unitPrice;

	private BigDecimal freight;
	@JsonProperty(value = "own_spec")
	private String ownSpec;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//后台输出到前台
	private Date createTime;
}
