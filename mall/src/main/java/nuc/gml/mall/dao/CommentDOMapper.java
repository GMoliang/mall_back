package nuc.gml.mall.dao;

import nuc.gml.mall.dataobject.CommentDO;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface CommentDOMapper {
	/**
	 * 查询当前文章的首条评论
	 *
	 * @param itemId 文章id
	 * @return
	 */
	LinkedList<CommentDO> selCommentReviewer(Integer itemId);


	/**
	 * 查询当前评论下的子评论
	 *
	 * @param itemId 文章id
	 * @param parentId  当前评论的id
	 * @return
	 */
	LinkedList<CommentDO> selCommentReply(Integer itemId, String parentId);

	/**
	 * 插入评论
	 *
	 * @param comment 评论信息
	 * @return
	 */
	int insComment(CommentDO comment);
}