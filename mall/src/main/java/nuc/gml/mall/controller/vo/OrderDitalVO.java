package nuc.gml.mall.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import nuc.gml.mall.dataobject.AddressDO;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SpuDO;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDitalVO {
    private Integer orderId;

    private Long userId;

    private Integer shopId;

    //订单编号
    private Integer orderStatus;

    //商品总价
    private BigDecimal productAmountTotal;

    //运费
    private BigDecimal logisticsFee;

    private Integer addressId;

    //订单支付编号
    private String outTradeNo;

    private String escrowTradeNo;

    //支付时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    //发货时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliveryTime;

    //快递单号
    private String expressId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    private Integer OrderDetaiId;

    private SpuDO spu;

    private SkuDO sku;

    private AddressDO address;

    private BigDecimal amount;

    private Integer count;

}
