package nuc.gml.mall.controller;

import nuc.gml.mall.controller.vo.FavoriteVO;
import nuc.gml.mall.dataobject.FavoriteDO;
import nuc.gml.mall.response.CommonReturnType;
import nuc.gml.mall.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller("favorite")
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    //收藏
    @RequestMapping(value = "/collectItem", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType collectItem(@RequestParam("userId") Long  userId , @RequestBody Integer spuId){
        System.out.println(userId+""+spuId);
        FavoriteDO favoriteDO=new FavoriteDO();
        favoriteDO.setUserId(userId);
        favoriteDO.setItemId(spuId);
        favoriteDO.setCreateTime(new Date());
        favoriteService.save(favoriteDO);
        return CommonReturnType.create(null);
    }

    //取消收藏
    @RequestMapping(value = "/cancleItem", method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType cancleItem(@RequestParam("userId") Long userId,@RequestBody Integer spuId){
        System.out.println(userId+""+spuId);
        favoriteService.cancelItem(userId,spuId);
        return CommonReturnType.create(null);
    }

    //商品是否收藏
    @RequestMapping(value = "/getItemStart", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItemStart(Long userId,Integer spuId){

       int flag= favoriteService.getItemStart(userId,spuId);

       if(flag==0){
           //操作失败
           return CommonReturnType.create(false);
       }
        return CommonReturnType.create(true);
    }

    //返回商品收藏列表页
    @RequestMapping(value = "/getFavoriteList", method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getFavoriteList(Long userId){

        List<FavoriteVO> favoriteVOList = favoriteService.getFavoriteList(userId);
        System.out.println(favoriteVOList);

        return CommonReturnType.create(favoriteVOList);
    }
}
