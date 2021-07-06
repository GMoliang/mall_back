package nuc.gml.mall.service.model;

import lombok.Data;

import java.util.Date;

@Data
public class SpuModel {
    private Integer id;

    private Integer shopId;

    private String title;

    private String subTitle;

    private Integer cid1;

    private Integer cid2;

    private Integer cid3;

    private String cname;

    private String mainImage;

    private String subImages;

    private String description;

    private String specifications;

    private String specTemplate;

    private String province;

    private String city;

    private String area;

    private String address;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
