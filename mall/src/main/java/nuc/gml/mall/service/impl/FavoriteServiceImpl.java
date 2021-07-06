package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.controller.vo.FavoriteVO;
import nuc.gml.mall.controller.vo.SpuVO;
import nuc.gml.mall.dao.FavoriteDOMapper;
import nuc.gml.mall.dataobject.FavoriteDO;
import nuc.gml.mall.dataobject.SpuDO;
import nuc.gml.mall.service.FavoriteService;
import nuc.gml.mall.service.SpuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteDOMapper, FavoriteDO> implements FavoriteService {

    @Autowired
    private FavoriteDOMapper favoriteDOMapper;

    @Autowired
    private SpuService  spuService;

    //取消收藏
    @Override
   public  void cancelItem(Long userId, Integer itemId){
       int row = favoriteDOMapper.delete(new QueryWrapper<FavoriteDO>()
               .eq("user_id", userId)
               .eq("item_id", itemId)
        );
       return;
    }

    //是否收藏
    @Override
    public int getItemStart(Long userId, Integer itemId) {
        FavoriteDO favoriteDO =favoriteDOMapper.selectOne(new QueryWrapper<FavoriteDO>()
                .eq("user_id", userId)
                .eq("item_id", itemId)
        );
        if (favoriteDO!=null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<FavoriteVO> getFavoriteList(Long userId) {
        List<FavoriteDO> favoriteDOList=favoriteDOMapper.selectList(
                new QueryWrapper<FavoriteDO>()
                .eq("user_id",userId)
        );
        List<FavoriteVO> favoriteVOList=new ArrayList<>();
        for (FavoriteDO favoriteDO:favoriteDOList) {
            SpuDO spuDO=spuService.getById(favoriteDO.getItemId());

            FavoriteVO favoriteVO=new FavoriteVO();
            BeanUtils.copyProperties(spuDO,favoriteVO);
            favoriteVO.setSpuId(spuDO.getId());
            favoriteVOList.add(favoriteVO);

        }
        return favoriteVOList;
    }

}
