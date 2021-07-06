package nuc.gml.mall.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SpuDO;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CartModel {

	private Integer cartId;

	private Integer shopId;

	private Integer itemId;

	private Integer skuId;

	private Long userId;


	private Integer count;

	private BigDecimal amount;


	private Date createTime;

	private SpuDO spu;

	private SkuDO sku;

}
