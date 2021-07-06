package nuc.gml.mall.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import nuc.gml.mall.config.Ali.pay.AliPayConfig;
import nuc.gml.mall.controller.vo.OrderDitalVO;
import nuc.gml.mall.controller.vo.OrderVO;
import nuc.gml.mall.dao.OrderDetailDOMapper;
import nuc.gml.mall.dataobject.*;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.error.EmBusinessError;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.AddressService;
import nuc.gml.mall.service.CartService;
import nuc.gml.mall.service.OrderService;
import nuc.gml.mall.service.SkuService;
import nuc.gml.mall.service.model.OrderDetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller("order")
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    OrderService orderService;

    @Autowired
    AddressService addressService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    OrderDetailDOMapper detailMapper;

    @Autowired
    SkuService skuService;

    @Autowired
    CartService cartService;



    //在个人中心取消支付
    @PostMapping("/cancleOrder")
    @ResponseBody
    @Transactional
    public CommonReturnType cancleOrder(@RequestBody Integer[] orderDetailIdList , @RequestParam Integer orderId) throws BusinessException{
        System.out.println(orderDetailIdList[0]);
        System.out.println(orderId);
        //修改订单状态为取消支付
        orderService.update(null,
                new UpdateWrapper<OrderDO>()
                        .eq("id",orderId)
                        .set("order_status",4)
                        .set("pay_time",new Date())
        );
        //加库存
        for (Integer detailId:orderDetailIdList) {
            OrderDetailDO orderDetailDO=detailMapper.selectById(detailId);
            SkuDO skuDO=skuService.getById(orderDetailDO.getSkuId());
            skuService.update(null,
                    new UpdateWrapper<SkuDO>()
                    .eq("id",orderDetailDO.getSkuId())
                    .set("stock",skuDO.getStock()+orderDetailDO.getCount()));
        }

        return CommonReturnType.create(null);
    }


    //在购物车创建新的订单
    @PostMapping("/createOrder")
    @ResponseBody
    @Transactional
    public CommonReturnType createOrderByCart(@RequestBody Integer[] cartId , @RequestParam Integer addressId) throws BusinessException{


        SysUserDO sysUserDO = (SysUserDO) httpServletRequest.getSession().getAttribute("LOGIN_USER");

        if(sysUserDO==null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        
        int orderId = orderService.createOrderByCart(cartId, sysUserDO,addressId);

        for (Integer id:cartId) {
            cartService.deleteCartItem(id);
        }

        System.out.println(orderId);
        return CommonReturnType.create(orderId);
    }

    //根据用户查询商品
    @GetMapping("/getOrderByUser")
    @ResponseBody
    public CommonReturnType getOrderByUser() throws BusinessException{
        System.out.println("user/all");
        SysUserDO sysUserDO = (SysUserDO) httpServletRequest.getSession().getAttribute("LOGIN_USER");
        System.out.println(sysUserDO);

        if(sysUserDO==null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        List<OrderVO> orderVOS =  orderService.getOrderByUser(sysUserDO);
        return CommonReturnType.create(orderVOS);
    }

    //根据id查询订单
    @GetMapping("/getOrderByOid")
    @ResponseBody
    public CommonReturnType getOrderByOid(Integer oid) {
        System.out.println("根据id查询订单======================================"+oid);
        OrderVO orderVO = orderService.getOrderByOid(oid);
        return CommonReturnType.create(orderVO);
    }

    //根据idx修改订单状态
    @PostMapping("/updateFaHuo")
    @ResponseBody
    public CommonReturnType updateFaHuo(@RequestBody Integer oid) {
        System.out.println("根据idx修改订单状态==================================="+oid);
        orderService.updateFaHuo(oid);
        return CommonReturnType.create(null);
    }

    //修改订单状态为支付
    @GetMapping("/updateOrderPayStatus")
    @ResponseBody
    public CommonReturnType updateOrderPayStatus(@RequestParam String outTradeNo, String tradeNo) {
        System.out.println("修改订单状态为支付==================================="+outTradeNo);
        System.out.println("修改订单状态为支付==============================="+tradeNo);
        String oId=outTradeNo.substring(outTradeNo.length()-2);

        Integer orderId=Integer.parseInt(oId);
        orderService.update(null,
                new UpdateWrapper<OrderDO>()
                .eq("id",orderId)
                .set("order_status",2)
                .set("out_trade_no",outTradeNo)
                .set("escrow_trade_no",tradeNo)
                .set("pay_time",new Date())
        );
        return CommonReturnType.create(null);
    }




    //删除订单详情
    @PostMapping("/deleteOrderDetail")
    @ResponseBody
    public CommonReturnType deleteOrderDetail(@RequestBody Integer orderDetailId) {
        System.out.println("==============================================="+orderDetailId);
        orderService.deleteOrderDetail(orderDetailId);
        return CommonReturnType.create(null);
    }

    //根据店铺id查订单
    @GetMapping("/getOrderByShop")
    @ResponseBody
    public CommonReturnType getOrderByShop(@RequestParam("shopId") Integer shopId) throws BusinessException{
        List<OrderVO> orderVOS =  orderService.getOrderByShop(shopId);

        List<OrderDitalVO> orderDitalVOS=new ArrayList<>();
        for (OrderVO orderVO : orderVOS) {
            OrderDO order=orderVO.getOrder();
            List<OrderDetailModel> orderDetailList=orderVO.getOrderDetailList();

            //拿到地址：
            AddressDO addressDO=addressService.getById(order.getAddressId());


            for(OrderDetailModel orderDetailModel:orderDetailList){

                System.out.println("order++++******"+order.toString());

                OrderDitalVO orderDitalVO=new OrderDitalVO();
                BeanUtils.copyProperties(order,orderDitalVO);

                orderDitalVO.setAddress(addressDO);
              orderDitalVO.setOrderDetaiId(orderDetailModel.getId());
              orderDitalVO.setOrderId(orderDetailModel.getOrderId());
              orderDitalVO.setAmount(orderDetailModel.getAmount());
              orderDitalVO.setCount(orderDetailModel.getCount());
              orderDitalVO.setSku(orderDetailModel.getSku());
              orderDitalVO.setSpu(orderDetailModel.getSpu());

                System.out.println(orderDitalVO);

              orderDitalVOS.add(orderDitalVO);
            }
            System.out.println(orderDitalVOS);
        }
        return CommonReturnType.create(orderDitalVOS);
    }



}
