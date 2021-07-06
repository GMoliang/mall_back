package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.controller.vo.OrderVO;
import nuc.gml.mall.dao.OrderDOMapper;
import nuc.gml.mall.dao.OrderDetailDOMapper;
import nuc.gml.mall.dao.SkuDOMapper;
import nuc.gml.mall.dataobject.OrderDO;
import nuc.gml.mall.dataobject.OrderDetailDO;
import nuc.gml.mall.dataobject.SkuDO;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.service.CartService;
import nuc.gml.mall.service.OrderService;
import nuc.gml.mall.service.SkuService;
import nuc.gml.mall.service.SpuService;
import nuc.gml.mall.service.model.CartModel;
import nuc.gml.mall.service.model.OrderDetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderDOMapper, OrderDO> implements OrderService {


    @Autowired
    CartService cartService;

    @Autowired
    SpuService spuService;

    @Autowired
    SkuService skuService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    OrderDetailDOMapper detailMapper;

    @Override
    public int createOrderByCart(Integer[] cartId, SysUserDO user,Integer addressId) {
        List<CartModel> cartModels = cartService.getCartListByIds(cartId);
        Integer shopId=cartService.getCartById(cartId[0]).getShopId();

        OrderDO order=new OrderDO();
        order.setUserId(user.getUserId());
        //1为未付款
        order.setOrderStatus(1);
        order.setShopId(shopId);
        order.setAfterStatus(0);
        order.setAddressId(addressId);
        order.setCreatedTime(new Date());
        orderDOMapper.insert(order);

        String po="20210609"+order.getId();

        //插入订单号改变out_trade_no值
        orderDOMapper.update(null,
                new UpdateWrapper<OrderDO>()
                 .eq("id",order.getId())
                .set("out_trade_no",po));


        int row = order.getId();
        cartModels.forEach( cart -> {
            //减库存
            skuService.update(null,
                    new UpdateWrapper<SkuDO>()
                    .eq("id",cart.getSkuId())
                            .set("stock",cart.getSku().getStock()-cart.getCount())
            );

            OrderDetailDO detailDO = new OrderDetailDO();
            detailDO.setOrderId(order.getId());
            detailDO.setCount(cart.getCount());
            detailDO.setAmount(cart.getAmount());
            detailDO.setSpuId(cart.getItemId());
            detailDO.setSkuId(cart.getSkuId());
            detailDO.setCreateTime(new Date());
            detailMapper.insert(detailDO);
        });
        return row;
    }

    @Override
    public List<OrderVO> getOrderByUser(SysUserDO user) {
        //更具用户id所有订单
        List<OrderDO> orderList = orderDOMapper.selectList(new QueryWrapper<OrderDO>()
                .eq("user_id", user.getUserId()));
        List<OrderVO> orderVOS = new ArrayList<>();
        //再更每一个订单id，查出这个订单的所有订单详情
        orderList.forEach( orderEntity -> {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrder(orderEntity);
            //查出这个订单的所有订单详情
            List<OrderDetailModel> detailModels = getOrderDetailByOrder(orderEntity);
            orderVO.setOrderDetailList(detailModels);

            orderVOS.add(orderVO);
        });
        return orderVOS;
    }

    //更具商户查订单
    @Override
    public List<OrderVO> getOrderByShop(Integer shopId) {
        //更具用户id所有订单
        List<OrderDO> orderList = orderDOMapper.selectList(new QueryWrapper<OrderDO>()
                .eq("shop_id", shopId));
        List<OrderVO> orderVOS = new ArrayList<>();
        //再更每一个订单id，查出这个订单的所有订单详情
        orderList.forEach( orderEntity -> {
            OrderVO orderVO = new OrderVO();
            orderVO.setOrder(orderEntity);
            //查出这个订单的所有订单详情
            List<OrderDetailModel> detailModels = getOrderDetailByOrder(orderEntity);
            orderVO.setOrderDetailList(detailModels);

            orderVOS.add(orderVO);
        });
        return orderVOS;
    }


    //发货改变OrderStatus
   public void updateFaHuo(Integer oid){

       orderDOMapper.update(null,
               new UpdateWrapper<OrderDO>()
               .eq("id",oid)
               .set("order_status",3)
               .set("delivery_time",new Date()));
       return ;
    }

    //删除订单详情
    public void deleteOrderDetail(Integer orderDetailId){
        detailMapper.deleteById(orderDetailId);
        return ;
    }

    /**
     * 根据订单编号id查询
     */
    @Override
    public OrderVO getOrderByOid(Integer oid) {
        OrderVO orderVO = new OrderVO();
        OrderDO orderEntity = orderDOMapper.selectById(oid);
        orderVO.setOrder(orderEntity);
        orderVO.setOrderDetailList(getOrderDetailByOrder(orderEntity));
        return orderVO;
    }

    //更具订单号查出所有的订单详情
    public List<OrderDetailModel> getOrderDetailByOrder(OrderDO orderEntity) {
        List<OrderDetailDO> detailDOS = detailMapper.selectList(new QueryWrapper<OrderDetailDO>()
                .eq("order_id", orderEntity.getId()));

        List<OrderDetailModel> detailModelList = new ArrayList<>();
        detailDOS.forEach(detailDO -> {
            OrderDetailModel detailModel = convertFromModel(detailDO);
            if (detailModel!=null) {
                detailModelList.add(detailModel);
            }
        });
        return detailModelList;
    }
    private OrderDetailModel convertFromModel(OrderDetailDO detailDO){
        if (detailDO == null){
            return null;
        }
        OrderDetailModel orderDetailModel = new OrderDetailModel();
        BeanUtils.copyProperties(detailDO, orderDetailModel);
        orderDetailModel.setSpu(spuService.getById(detailDO.getSpuId()));
        orderDetailModel.setSku(skuService.getById(detailDO.getSkuId()));
        return orderDetailModel;
    }
}
