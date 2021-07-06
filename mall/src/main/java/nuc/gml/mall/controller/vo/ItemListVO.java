package nuc.gml.mall.controller.vo;

import lombok.Data;

import java.util.List;

@Data
public class ItemListVO {

	private List<SpuVO> itemVOList;

	//当前页
	private int pageNum;
	//每页的数量
	private int pageSize;
	//总记录数
	private long total;
	//总页数
	private int pages;
	//所有导航页号
	private int[] navigatepageNums;
}
