package nuc.gml.mall.service.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class AddressModel {

    private Integer id;

    private Integer userId;

    @NotBlank(message = "收货人姓名不能为空")
    private String receiverName;

    @NotBlank(message = "收货人手机号不能为空")
    private String receiverMobile;

    @NotBlank(message = "收货人省地址不能为空")
    private String receiverProvince;

    @NotBlank(message = "收货人市地址不能为空")
    private String receiverCity;

    @NotBlank(message = "收货人区地址不能为空")
    private String receiverDistrict;

    @NotBlank(message = "收货人具体地址不能为空")
    private String receiverAddress;



}