package nuc.gml.mall.controller.vo;

import lombok.Data;
import nuc.gml.mall.dataobject.OrderDO;
import nuc.gml.mall.service.model.OrderDetailModel;

import java.util.List;

@Data
public class OrderVO {
    private OrderDO order;
    private List<OrderDetailModel> orderDetailList;
}
