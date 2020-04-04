
database name is springboot_study

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `business_line` tinyint(4) NOT NULL DEFAULT '20' COMMENT '业务线',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '人名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '人名称',
  `yn` int(11) NOT NULL DEFAULT '1' COMMENT '是否删除',
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户表';

