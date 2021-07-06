package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.consts.UserConst;
import nuc.gml.mall.dao.ShopDOMapper;
import nuc.gml.mall.dao.SysUserRoleDOMapper;
import nuc.gml.mall.dataobject.ShopDO;
import nuc.gml.mall.dataobject.SysUserRoleDO;
import nuc.gml.mall.error.BusinessException;
import nuc.gml.mall.error.EmBusinessError;
import nuc.gml.mall.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ShopServiceImpl extends ServiceImpl<ShopDOMapper, ShopDO> implements ShopService {
    @Autowired
    private ShopDOMapper shopDOMapper;

    @Autowired
    private SysUserRoleDOMapper sysUserRoleDOMapper;



    @Override
    @Transactional
    public void registerShop(ShopDO shopDO) throws BusinessException {
        shopDO.setCreateTime(new Date());
        shopDO.setShopStatus(0);

        try {
            shopDOMapper.insert(shopDO);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "手机号已重复注册");
        }
        sysUserRoleDOMapper.update(null,
                new UpdateWrapper<SysUserRoleDO>()
                .eq("user_id",shopDO.getUserId())
                .set("role_id", UserConst.SHOP)
        );

        return ;
    }


}
