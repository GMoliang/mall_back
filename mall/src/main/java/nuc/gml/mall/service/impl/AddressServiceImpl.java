package nuc.gml.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nuc.gml.mall.dao.AddressDOMapper;
import nuc.gml.mall.dao.FavoriteDOMapper;
import nuc.gml.mall.dataobject.AddressDO;
import nuc.gml.mall.dataobject.FavoriteDO;
import nuc.gml.mall.service.AddressService;
import nuc.gml.mall.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressDOMapper, AddressDO> implements AddressService {

    @Autowired
    private AddressDOMapper addressDOMapper;

    @Override
    public List<AddressDO> getAddressByUser(Long userId) {

        List<AddressDO> addressDOList = addressDOMapper.selectList(new QueryWrapper<AddressDO>()
                .eq("user_id", userId));
        return addressDOList;
    }

    @Override
    public void insertAddress(AddressDO addressDO) {
        //如果这个新增的地址是默认地址
        //则其余所有地址的的addr=0
        if (addressDO.getDefaultAddr().equals("1")) {
            updateAddress(addressDO,addressDO.getUserId());
        }
        addressDOMapper.insert(addressDO);
        return ;
    }

    //如果这个新增的地址是默认地址
    //则其余所有地址的的addr=0
    @Override
    public void updateAddress(AddressDO addressDO,Long userId) {
        List<AddressDO> addressEntityList = addressDOMapper.selectList(new QueryWrapper<AddressDO>()
                .eq("user_id", userId));
        addressEntityList.forEach( address ->  {
            address.setDefaultAddr("0");
            addressDOMapper.updateById(address);
        });
        return ;
    }
    @Override
    public void deleteAddress(Integer aid) {
        addressDOMapper.deleteById(aid);
        return ;
    }

    //设该地址为默认地址
    @Override
    public void updateAddress(Integer aid) {
        AddressDO addressDO = addressDOMapper.selectById(aid);
        updateAddress(addressDO,addressDO.getUserId());
        addressDO.setDefaultAddr("1");
        addressDOMapper.updateById(addressDO);
        return  ;
    }

}
