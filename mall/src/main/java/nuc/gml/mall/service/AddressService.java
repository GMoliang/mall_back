package nuc.gml.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import nuc.gml.mall.dataobject.AddressDO;
import nuc.gml.mall.dataobject.FavoriteDO;

import java.util.List;

public interface AddressService extends IService<AddressDO> {

    List<AddressDO> getAddressByUser(Long userId);

    void insertAddress(AddressDO addressDO);

    void updateAddress(AddressDO addressDO,Long userId);

    void deleteAddress(Integer aid);

    void updateAddress(Integer aid);
}
