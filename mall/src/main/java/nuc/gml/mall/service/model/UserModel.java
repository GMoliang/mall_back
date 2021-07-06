package nuc.gml.mall.service.model;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class UserModel {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String encrptPassword;

    @NotBlank(message = "手机号不能为空")
    private String telphone;

    private Integer role;

    private String registerMode;

    private Date createTime;

    private Date updateTime;

}
