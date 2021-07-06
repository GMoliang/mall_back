package nuc.gml.mall.controller;

import nuc.gml.mall.dataobject.AddressDO;
import nuc.gml.mall.dataobject.FavoriteDO;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller("address")
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    //根据用户ID查地址
    @RequestMapping(value = "/getAddressByUser", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getAddressByUser(Long userId){
        List<AddressDO> addressDOList=addressService.getAddressByUser(userId);
        return CommonReturnType.create(addressDOList);
    }

    //新增地址
    @RequestMapping(value = "/insertAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType insertAddress(@RequestParam Long userId,@RequestBody AddressDO addressDO){
        addressDO.setUserId(userId);
        addressService.insertAddress(addressDO);
        List<AddressDO> addressDOList=addressService.getAddressByUser(userId);
        return CommonReturnType.create(addressDOList);
    }

    //删除地址
    @RequestMapping(value = "/deleteAddress", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType deleteAddress(@RequestBody Integer addressId){
        addressService.deleteAddress(addressId);
        return CommonReturnType.create(null);
    }

    //设给地址为默认地址
    @RequestMapping(value = "/default", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType updateDefault(@RequestParam Integer addressId,@RequestParam Long userId){
        addressService.updateAddress(addressId);
        return CommonReturnType.create(null);
    }

}
