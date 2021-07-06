package nuc.gml.mall.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import nuc.gml.mall.config.utils.*;

import nuc.gml.mall.dao.CommentDOMapper;
import nuc.gml.mall.dataobject.CommentDO;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDOMapper commentDOMapper;

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public ServiceResult<Map> selCommentReviewer(Integer itemId,Integer page, Integer pageSize) {

		Page<Object> pageData = PageHelper.startPage(page, pageSize);


		// 查询父评论信息
		LinkedList<CommentDO> comments = commentDOMapper.selCommentReviewer(itemId);

		System.out.println("comments"+comments);
		Iterator<CommentDO> iterator = comments.iterator();
		// 查找子评论信息
		Map<String, Object> replyMap = new HashMap<>();
		while (iterator.hasNext()) {
			CommentDO next = iterator.next();

			LinkedList<CommentDO> commentsReply = new LinkedList<>();
			selCommentReply(itemId, next.getId(), commentsReply);
			// 查询当前评论下的全部子评论
			replyMap.put(next.getId(), commentsReply);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("reviewer", PagedUtilHelper.genResultPagedData(comments, pageData));
		// 保存评论内容
		map.put("reply", replyMap);
		return ServiceResultHelper.genResultWithDataBaseSuccess(map);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void selCommentReply(Integer itemId, String parentId, LinkedList<CommentDO> commentsReply) {
		LinkedList<CommentDO> comments = commentDOMapper.selCommentReply(itemId,parentId);
		Iterator<CommentDO> iterator = comments.iterator();
		while (iterator.hasNext()) {
			// 获取评论信息
			CommentDO next = iterator.next();
			commentsReply.add(next);
			// 递归遍历
			selCommentReply(itemId, next.getId(), commentsReply);
		}
	}




	/**
	 * 插入评论
	 *
	 * @param comment 评论信息
	 * @param sysUserDO   用户身份
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public ServiceResult<Boolean> insComment(CommentDO comment, SysUserDO sysUserDO) {

		// 设置评论id
		comment.setId(UUID.randomUUID().toString());

		comment.setReviewerUser(sysUserDO);
		comment.setCommentTime(TypeConvert.DateToString(new Date()));

		System.out.println("test"+comment);
		if (commentDOMapper.insComment(comment) > 0) {

			return ServiceResultHelper.genResultWithDataBaseSuccess();
		}
		return ServiceResultHelper.genResultWithDataBaseFailed();
	}



}
