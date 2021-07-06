package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mall_shop")
public class ShopDO {
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Integer shopId;

    private String shopName;

    private Long userId;

    private String shopType;

    private String shopNotice;

    private String shopOwner;

    private String mobile;

    private String province;

    private String city;

    private String area;

    private String address;

    private String shopLogo;

    private String shopPhotos;

    private Integer shopStatus;

    private Date createTime;

    private Date updateTime;


}