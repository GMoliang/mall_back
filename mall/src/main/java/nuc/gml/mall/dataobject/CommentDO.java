package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mall_comment")
public class CommentDO {
	@TableId(value = "id", type = IdType.AUTO)
	private String id;


	private String content;


	private String commentTime;


	private Integer likeCounts;


	private Integer state;


	private SysUserDO reviewerUser;


	private SysUserDO replyUser;


	private Integer itemId;


	private String parentId;
}