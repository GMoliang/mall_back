package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mall_address")
public class AddressDO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private String receiverName;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverArea;

    private String receiverAddress;

    private String defaultAddr;
}
