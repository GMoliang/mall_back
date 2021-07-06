package nuc.gml.mall.service.model;

import lombok.Data;

import java.util.Date;

@Data
public class CommentModel {
	private Integer commId;

	private Integer itemId;

	private Integer orderItemId;

	private Integer userId;

	private String content;

	private String replyContent;

	private Date commTime;

	private Date replyTime;

	private Integer replySts;

	private String pics;

	private Integer status;

	private Integer evaluate;

}
