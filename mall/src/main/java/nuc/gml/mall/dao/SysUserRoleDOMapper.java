package nuc.gml.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nuc.gml.mall.dataobject.SysUserDO;
import nuc.gml.mall.dataobject.SysUserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface SysUserRoleDOMapper  extends BaseMapper<SysUserRoleDO> {

}