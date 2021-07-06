package nuc.gml.mall.controller;


import nuc.gml.mall.config.utils.JSONResult;
import nuc.gml.mall.config.utils.ServiceResult;
import nuc.gml.mall.dao.SpuDOMapper;
import nuc.gml.mall.dataobject.CommentDO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.service.CommentService;

import nuc.gml.mall.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller("comment")
@RequestMapping("/comment")
public class CommentController  {

	@Autowired
	private CommentService commentService;


	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	JSONResult result;


	@GetMapping("/test")
	public JSONResult tests(){
		return result.ok();
	}

	//根据文章查询评论
	@GetMapping("/selCommentReviewer")
	@ResponseBody
	public JSONResult selCommentReviewer(Integer itemId,
										 @RequestParam(defaultValue = "1") Integer page,
										 @RequestParam(defaultValue = "8") Integer pageSize) {

		ServiceResult<Map> kp=commentService.selCommentReviewer(itemId,page,pageSize);
		System.out.println(kp);
		// 获取数据
		return result.ok(kp);
	}

	//添加新的评论
	@PostMapping("/insComment")
	@ResponseBody
	public JSONResult insComment(@RequestBody CommentDO comment){
	//	UserEntity userModel = (UserEntity) authentication.getPrincipal();

		SysUserDO sysUserDO = (SysUserDO) httpServletRequest.getSession().getAttribute("LOGIN_USER");
		System.out.println(sysUserDO);


		ServiceResult<Boolean> serviceResult = commentService.insComment(comment, sysUserDO);
		return serviceResult.isSuccess() ? result.ok() : result.error(serviceResult.getCode(),serviceResult.getMsg());
	}

}
