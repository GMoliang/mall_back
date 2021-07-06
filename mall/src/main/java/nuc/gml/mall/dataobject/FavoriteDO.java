package nuc.gml.mall.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("mall_favorite")
public class FavoriteDO {
    private Long userId;
    private Integer itemId;
    private Date createTime;
}
