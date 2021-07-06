package nuc.gml.mall.service.model;

import lombok.Data;

import java.util.Date;
@Data
public class ShopModel {
    private Integer shopId;

    private String shopName;

    private Integer userId;

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
