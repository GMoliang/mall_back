package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("mall_cart")
public class CartDO {

	@TableId(value = "cart_id", type = IdType.AUTO)
	private Integer cartId;

	private Integer shopId;

	private Integer itemId;

	private Integer skuId;

	private Long userId;

	private Integer count;

	private BigDecimal amount;

	private Date createTime;

}