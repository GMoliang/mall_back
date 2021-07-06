package nuc.gml.mall.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
	private Integer commId;

	private Integer itemId;//spu_Id

	private Integer userId;

	private String content;

	private String replyContent;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//后台输出到前台
	private Date commTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//后台输出到前台
	private Date replyTime;

	private Integer replySts;

	private String pics;

	private Integer status;

	private Integer evaluate;
}
