-- ----------------------------
DROP TABLE IF EXISTS `mall_user`;
CREATE TABLE `mall_user`
(
    `id`            int(20) NOT NULL AUTO_INCREMENT,
    `username`      varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户名',
    `password`      varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码，加密存储',
    `telphone`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
    `role`          int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户,2-商户',
    `register_mode` varchar(255)                                            NOT NULL DEFAULT '' COMMENT '//byphone,bywechat,byalipay',
    `create_time`   datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `telphone_unique_index` (`telphone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;


种类分类表
一级：禽畜肉蛋
二级：活畜
三级：羊
CREATE TABLE `mall_category`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
    `parent_id`   int(11) NULL DEFAULT 0 COMMENT '父类别id当id=0时说明是根节点,一级类别',
    `name`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '类别名称',
    `status`      int(11) NULL DEFAULT 1 COMMENT '类别状态1-正常,2-已废弃',
    `sort_order`  int(4) NULL DEFAULT 1 COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
    `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `mall_category` (`id`, `parent_id`, `name`, `status`, `sort_order`, `create_time`, `update_time`)
VALUES (10001, 0, '禽畜肉蛋', 1, 1, '2000-03-25 16:46:00', '2000-03-25 16:46:00'),
       (10002, 0, '水果', 1, 1, '2000-03-25 16:46:21', '2000-03-25 16:46:21'),
       (10003, 0, '蔬菜', 1, 1, '2000-03-25 16:49:53', '2000-03-25 16:49:53'),
       (10004, 0, '苗木花草', 1, 1, '2000-03-25 16:50:19', '2000-03-25 16:50:19'),
       (10005, 0, '种子种苗', 1, 1, '2000-03-25 16:50:29', '2000-03-25 16:50:29'),
       (10006, 10001, '活畜', 1, 1, '2000-03-25 16:52:15', '2000-03-25 16:52:15'),
       (10007, 10001, '活禽', 1, 1, '2000-03-25 16:52:26', '2000-03-25 16:52:26'),
       (10008, 10001, '蛋类', 1, 1, '2000-03-25 16:52:39', '2000-03-25 16:52:39'),
       (10009, 10006, '羊', 1, 1, '2000-03-25 16:56:45', '2000-03-25 16:56:45'),
       (10010, 10006, '牛', 1, 1, '2000-03-25 16:57:05', '2000-03-25 16:57:05');


规格属性表
CREATE TABLE `mall_specification`
(
    `category_id`    int(20) NOT NULL COMMENT '规格模板所属商品分类id',
    `specifications` varchar(3000) NOT NULL DEFAULT '' COMMENT '规格参数模板，json格式 ，其实就是相当于一个分类里比如羊，这个大类别的共有属性',
    PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格参数模板，json格式。';

SPU表
：
ps:这个表里需要加common_id吗
CREATE TABLE `mall_spu`
(
    `id`             int(20) NOT NULL AUTO_INCREMENT COMMENT 'spu id',
    `title`          varchar(255)                                              NOT NULL DEFAULT '' COMMENT '标题',
    `sub_title`      varchar(255)                                                       DEFAULT '' COMMENT '子标题',
    `cid1`           int(20) NOT NULL COMMENT '1级类目id',
    `cid2`           int(20) NOT NULL COMMENT '2级类目id',
    `cid3`           int(20) NOT NULL COMMENT '3级类目id',
    `main_image`     varchar(500)                                                       DEFAULT NULL COMMENT '产品主图,url相对地址',
    `sub_images`     text COMMENT '图片地址,json格式,扩展用',
    `description`    text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品描述信息',
    `specifications` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '全部规格参数数据 其实就是相当于一个分类里比如羊，这个大类别的共有属性',
    `spec_template`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '特有规格参数及可选值信息，json格式 , 其实就是sku的不同规格',
    `province`       varchar(10)                                                        DEFAULT NULL COMMENT '发货省份（描述）',
    `city`           varchar(10)                                                        DEFAULT NULL COMMENT '发货城市（描述）',
    `area`           varchar(10)                                                        DEFAULT NULL COMMENT '发货区域（描述）',
    `address`        varchar(100)                                                       DEFAULT NULL COMMENT '发货详细地址',
    `status`         int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
    `create_time`    datetime                                                           DEFAULT NULL COMMENT '添加时间',
    `update_time`    datetime                                                           DEFAULT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8 COMMENT='spu表，该表描述的是一个抽象的商品，比如 羊';



sku表
ps:不同规格的产品的价格
CREATE TABLE `mall_sku`
(
    `id`          int(20) NOT NULL AUTO_INCREMENT COMMENT 'sku id',
    `spu_id`      int(20) NOT NULL COMMENT 'spu id',
    `name`        varchar(255)   NOT NULL COMMENT '规格名称',
    `amount`      int(9) NOT NULL COMMENT '起批量',
    `price`       decimal(20, 2) NOT NULL COMMENT '单价,单位-元保留两位小数',
    `stock`       int(9) NOT NULL COMMENT '库存数量',
    `own_spec`    varchar(1000) COMMENT 'sku的特有规格参数，json格式，反序列化时应使用linkedHashMap，保证有序。这里指特有的sku组合，比如“颜色+内存+...”,"单位+.."',
    `freight`     decimal(20, 2) NOT NULL COMMENT '运费,单位-元保留两位小数',
    `enable`      int(1) NOT NULL DEFAULT '1' COMMENT '是否有效，0无效，1有效',
    `create_time` datetime       NOT NULL COMMENT '添加时间',
    `update_time` datetime       NOT NULL COMMENT '最后修改时间',
    PRIMARY KEY (`id`),
    KEY           `key_spu_id` (`spu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sku表,该表表示具体的商品实体,如黑色的64GB的iphone 8';

店铺表
DROP TABLE IF EXISTS `tz_shop_detail`;

CREATE TABLE `mall_shop`
(
    `shop_id`     int(20) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
    `shop_name`   varchar(50)   DEFAULT NULL COMMENT '店铺名称(数字、中文，英文(可混合，不可有特殊字符)，可修改)、不唯一',
    `user_id`     int(20) DEFAULT NULL COMMENT '店长用户id',
    `shop_type`   varchar(36)   DEFAULT NULL COMMENT '主营类目',
    `shop_notice` varchar(50)   DEFAULT NULL COMMENT '店铺公告(可修改)',
    `shop_owner`  varchar(20)   DEFAULT NULL COMMENT '店长',
    `mobile`      varchar(20)   DEFAULT NULL COMMENT '店铺绑定的手机(登录账号：唯一)',
    `province`    varchar(10)   DEFAULT NULL COMMENT '店铺所在省份（描述）',
    `city`        varchar(10)   DEFAULT NULL COMMENT '店铺所在城市（描述）',
    `area`        varchar(10)   DEFAULT NULL COMMENT '店铺所在区域（描述）',
    `address`     varchar(100)  DEFAULT NULL COMMENT '店铺详细地址',
    `shop_logo`   varchar(200)  DEFAULT NULL COMMENT '店铺logo(可修改)',
    `shop_photos` varchar(1000) DEFAULT NULL COMMENT '店铺相册',
    `shop_status` int(4) DEFAULT NULL COMMENT '店铺状态(-1:未开通 0: 停业中 1:营业中)，可修改',
    `create_time` datetime      DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime      DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`shop_id`),
    UNIQUE KEY `mobile` (`mobile`),
    UNIQUE KEY `shop_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



mall_shop_detail
作为扩展表：店主省份（批发商，供应上），店主资历，实人认证等字段



评论表
DROP TABLE IF EXISTS `tz_prod_comm`;

CREATE TABLE `tz_prod_comm`
(
    `prod_comm_id`  bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `prod_id`       bigint(20) unsigned NOT NULL COMMENT '商品ID',
    `order_item_id` bigint(20) unsigned DEFAULT NULL COMMENT '订单项ID',
    `user_id`       varchar(36)   DEFAULT NULL COMMENT '评论用户ID',
    `content`       varchar(500)  DEFAULT '' COMMENT '评论内容',
    `reply_content` varchar(500)  DEFAULT '' COMMENT '掌柜回复',
    `rec_time`      datetime      DEFAULT NULL COMMENT '记录时间',
    `reply_time`    datetime      DEFAULT NULL COMMENT '回复时间',
    `reply_sts`     int(1) DEFAULT '0' COMMENT '是否回复 0:未回复  1:已回复',
    `postip`        varchar(16)   DEFAULT NULL COMMENT 'IP来源',
    `score`         tinyint(2) DEFAULT '0' COMMENT '得分，0-5分',
    `useful_counts` int(11) DEFAULT '0' COMMENT '有用的计数',
    `pics`          varchar(1000) DEFAULT NULL COMMENT '晒图的json字符串',
    `is_anonymous`  int(1) DEFAULT '0' COMMENT '是否匿名(1:是  0:否)',
    `status`        int(1) DEFAULT NULL COMMENT '是否显示，1:为显示，0:待审核， -1：不通过审核，不显示。 如果需要审核评论，则是0,，否则1',
    `evaluate`      tinyint(2) DEFAULT NULL COMMENT '评价(0好评 1中评 2差评)',
    增加累计评价字段 --
        PRIMARY KEY (`prod_comm_id`),
    KEY             `prod_id` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评论';

收藏表
DROP TABLE IF EXISTS `tz_prod_favorite`;

CREATE TABLE `tz_prod_favorite`
(
    `favorite_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `prod_id`     bigint(20) unsigned NOT NULL COMMENT '商品ID',
    `rec_time`    datetime    NOT NULL COMMENT '收藏时间',
    `user_id`     varchar(36) NOT NULL DEFAULT '' COMMENT '用户ID',
    PRIMARY KEY (`favorite_id`),
    KEY           `prod_id` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品收藏表';

收藏表
DROP TABLE IF EXISTS `tz_user_collection`;

CREATE TABLE `tz_user_collection`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏表',
    `prod_id`     bigint(20) DEFAULT NULL COMMENT '商品id',
    `user_id`     varchar(36) NOT NULL COMMENT '用户id',
    `create_time` datetime DEFAULT NULL COMMENT '收藏时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

