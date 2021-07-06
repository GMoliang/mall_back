package nuc.gml.mall.service;

import nuc.gml.mall.config.utils.ServiceResult;
import nuc.gml.mall.dataobject.CommentDO;
import nuc.gml.mall.dataobject.SysUserDO;


import java.util.List;
import java.util.Map;

public interface CommentService {

	ServiceResult<Map> selCommentReviewer(Integer itemId, Integer page, Integer pageSize);

	/**
	 * 插入评论
	 *
	 * @param comment 评论信息
	 * @param sysUserDO   用户身份
	 * @return
	 */
	ServiceResult<Boolean> insComment(CommentDO comment, SysUserDO sysUserDO);
}
