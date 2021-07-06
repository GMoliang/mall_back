package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.controller.vo.OrderVO;
import nuc.gml.mall.dataobject.OrderDO;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SysUserDO;

import java.util.List;

public interface OrderService extends IService<OrderDO> {

    /**
     * 根据购物车创建订单
     * @Param: cartId
     * @return int
     **/
    int createOrderByCart(Integer[] cartId, SysUserDO user , Integer addressId);

    /**
     * 根据用户查询订单
     * @Param: user
     **/
    List<OrderVO> getOrderByUser(SysUserDO user);

    /**
     * 根据商户查询订单
     **/
    List<OrderVO> getOrderByShop(Integer shopId);


    //更具订单编号查订单
    OrderVO getOrderByOid(Integer orderId);

    //更具订单编号查订单
    void updateFaHuo(Integer orderId);

    //删除订单详情
    void deleteOrderDetail(Integer orderDetailId);
}
