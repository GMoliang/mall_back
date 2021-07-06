package nuc.gml.mall.service.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SpuDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rwenjie
 * @since 2021-05-27
 */
@Data
public class OrderDetailModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer orderId;

    private SpuDO spu;

    private SkuDO sku;

    private BigDecimal amount;

    private Integer count;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
