package nuc.gml.mall.service.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuModel {
	private Integer id;

	private Integer spuId;

	private String name;

	private Integer amount;

	private BigDecimal price;

	private Integer stock;

	private String ownSpec;//sku选择的属性

	private BigDecimal freight;

	private Integer enable;//该sku是否启用 启用为1
}
