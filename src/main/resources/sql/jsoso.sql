DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(50) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `creatDate` TIMESTAMP(6),
  `updateDate` TIMESTAMP(6),
  `status` int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` varchar(36) NOT NULL,
  `rolename` varchar(20) DEFAULT NULL,
  `rolecode` varchar(20) DEFAULT NULL,
	`status` int,
  `des` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_resource`;
CREATE TABLE `tb_resource` (
  `id` varchar(36) NOT NULL,
  `resourcename` varchar(20) DEFAULT NULL,
  `resourcenamecode` varchar(200) DEFAULT NULL,
  `des` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` varchar(50) NOT NULL,
  `userid` varchar(50),
  `roleid` varchar(50),
  `status` int,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user_role_link
-- ----------------------------
CREATE VIEW v_user_role AS
    SELECT
        user.id,
				user.username,
			  role.rolecode
    FROM tb_user_role link
		LEFT JOIN tb_user user on user.id = link.userid and user.status = 10
		LEFT JOIN tb_role role on role.id = link.roleid and role.status = 10;


-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` VARCHAR(36) NOT NULL COMMENT '分类表',
  `name` varchar(255) default NULL COMMENT '分类名',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
CREATE TABLE `tb_orderitem` (
  `id` VARCHAR(36) NOT NULL COMMENT '订单项表',
  `pid` VARCHAR(36) default NULL COMMENT '产品表id字段',
  `oid` VARCHAR(36) default NULL COMMENT '订单表id字段',
  `uid` VARCHAR(36) default NULL COMMENT '用户表id字段',
  `number` int(11) default NULL COMMENT '购买数量',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------

-- ----------------------------
-- Table structure for `order_`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` VARCHAR(36) NOT NULL COMMENT '订单表',
  `orderCode` varchar(36) default NULL COMMENT '订单号',
  `address` varchar(255) default NULL COMMENT '收货地址',
  `post` varchar(255) default NULL COMMENT '邮编',
  `receiver` varchar(255) default NULL COMMENT '收货人信息',
  `phone` varchar(255) default NULL COMMENT '手机号码',
  `userMessage` varchar(255) default NULL COMMENT '用户备注信息',
  `createDate` datetime default NULL COMMENT '订单创建日期',
  `payDate` datetime default NULL COMMENT '支付日期',
  `deliveryDate` datetime default NULL COMMENT '发货日期',
  `confirmDate` datetime default NULL COMMENT '确认收货日期',
  `uid` VARCHAR(36) default NULL COMMENT '用户表id字段',
  `status` int default NULL COMMENT '订单状态',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_
-- ----------------------------

-- ----------------------------
-- Table structure for `productimage`
-- ----------------------------
DROP TABLE IF EXISTS `tb_productimage`;
CREATE TABLE `tb_productimage` (
  `id` VARCHAR(36) NOT NULL COMMENT '产品图片表',
  `pid` VARCHAR(36) default NULL COMMENT '产品表的id字段',
  `type` varchar(255) default NULL COMMENT '产品类型,产品图片分单个图片和详情图片',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productimage
-- ----------------------------

-- ----------------------------
-- Table structure for `property`
-- ----------------------------
DROP TABLE IF EXISTS `tb_property`;
CREATE TABLE `tb_property` (
  `id` VARCHAR(36) NOT NULL COMMENT '属性表',
  `cid` VARCHAR(36) default NULL COMMENT '分类表的id字段',
  `name` varchar(255) default NULL COMMENT '属性名',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------

-- ----------------------------
-- Table structure for `propertyvalue`
-- ----------------------------
DROP TABLE IF EXISTS `tb_propertyvalue`;
CREATE TABLE `tb_propertyvalue` (
  `id` VARCHAR(36) NOT NULL  COMMENT '属性值表',
  `pid` VARCHAR(36) default NULL COMMENT '产品表的id字段',
  `ptid` VARCHAR(36) default NULL COMMENT '属性表的id字段',
  `value` varchar(255) default NULL COMMENT '属性值',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of propertyvalue
-- ----------------------------

DROP TABLE IF EXISTS `tb_review`;
CREATE TABLE `tb_review` (
  `id` VARCHAR(36) NOT NULL COMMENT '评价表',
  `content` varchar(4000) default NULL COMMENT '评价内容',
  `uid` VARCHAR(36) default NULL COMMENT '用户表的id字段',
  `pid` VARCHAR(36) default NULL COMMENT '产品表的id字段',
  `createDate` datetime default NULL COMMENT '评论时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review
-- ----------------------------