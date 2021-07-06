package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.controller.vo.FavoriteVO;
import nuc.gml.mall.dataobject.FavoriteDO;

import java.util.List;


public interface FavoriteService extends IService<FavoriteDO> {

    void cancelItem(Long userId, Integer itemId);

    int getItemStart(Long userId, Integer itemId);

    List<FavoriteVO> getFavoriteList(Long userId);
}
