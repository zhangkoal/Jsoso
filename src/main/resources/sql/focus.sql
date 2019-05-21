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
  `id` varchar(50) NOT NULL,
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
  `id` varchar(50) NOT NULL,
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


