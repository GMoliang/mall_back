package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("mall_order")
public class OrderDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private Integer shopId;

    private Integer orderStatus;

    private Integer afterStatus;

    private BigDecimal productAmountTotal;

    private BigDecimal logisticsFee;

    private Integer addressId;

    private Integer payChannel;

    private String outTradeNo;

    private String escrowTradeNo;

    private Date payTime;

    private Date deliveryTime;

    private String expressId;

    private Integer orderSettlementStatus;

    private Integer orderSettlementTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deletedTime;
}
