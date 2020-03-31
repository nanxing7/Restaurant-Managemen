/*
SQLyog Ultimate v8.32 
MySQL - 8.0.17 : Database - cygl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cygl` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `cygl`;

/*Table structure for table `foods` */

DROP TABLE IF EXISTS `foods`;

CREATE TABLE `foods` (
  `Fid` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜品编号',
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜品名字',
  `Price` double NOT NULL COMMENT '菜品价格',
  `Image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜品图片',
  `Result` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功效',
  `Sort` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `Status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`Fid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `foods` */

insert  into `foods`(`Fid`,`Name`,`Price`,`Image`,`Result`,`Sort`,`Status`) values (1,'鱼香肉丝',30,'/images/yuxiangrousi.jpg','改善缺铁性贫血;具有补肾养血,滋阴润燥，养血驻颜、令人肌肤红润。','热菜','无'),(2,'红烧肉',25,'/images/hongshaorou.jpg','改善缺铁性贫血 - 养颜美容 - 补肾养血 - 滋阴润燥','热菜','有'),(3,'白菜炖粉条',30,'/images/baicaidunfentiao.jpg','能增强皮肤的抗损伤能力,可以起到很好的护肤和养颜效果。','热菜','有'),(4,'鸡公煲',40,'/images/jigongbao.jpg','有温补肾阳、补精填髓的功效,杀菌消炎预防感冒的功效,同时还有抗衰老、降血脂的功效.','热菜','有'),(5,'铁板豆腐',20,'/images/tiebandoufu.jpg','有助于减肥,还能提高记忆力、预防老年痴呆以及延年益寿等等功效','热菜','有'),(6,'口水猪肝',35,'/images/koushuizhugan.jpg','食用猪肝可调节和改善人体造血系统，维持健康的肤色，对皮肤的健美具有重要的意义。','热菜','有'),(7,'西红柿炒鸡蛋',20,'/images/xihongshichaojidan.jpg','具有抗坏血病、润肤、保护血管、降压、助消化等功效。','热菜','有'),(8,'家常干锅排骨',43,'/images/jiachangganguopaigu.jpg','可以补肾、益气、除湿等的功效。','热菜','有'),(9,'麻辣鱼',40,'/images/malayu.jpg','此菜辣味道,美味爽口。同时具有治疗高血压、营养不良等多种功效。','热菜','有'),(10,'麻婆豆腐',20,'/images/mapodoufu.jpg','富含动植物蛋白质、钙、磷、铁、维生素及碳水化合物,具有温中益气、补中生津、解毒润燥、补精添髓的功效。','热菜','有'),(11,'秋葵蘸酱',15,'/images/qiukuizhanjiang.jpg','可促进胃肠蠕动,有益于助消化,益肠胃。能有效降低血清胆固醇,预防心血管病。可提高耐缺氧能力。','凉菜','有'),(12,'凉拌黄瓜',15,'/images/liangbanhuanggua.jpg','抗肿瘤，抗衰老，防酒精中毒，降血糖，减肥强体。','凉菜','有'),(13,'香菜拌豆腐皮',15,'/images/xiangcaibandoufupi.jpg','1、豆腐营养丰富，含有铁、钙、磷、镁等人体必需的多种微量元素，还含有糖类、植物油和丰富的优质蛋白，素','凉菜','有'),(14,'香菜拌木耳',15,'/images/xiangcaibanmuer.jpg','能益气强身，有活血效能，并可防治缺铁性贫血等；可养血驻颜，令人肌肤红润，容光焕发，能够疏通肠胃，润滑肠道，同时对高血压患者也有一定帮助。味道鲜美，可素可荤，营养丰富。','凉菜','有');

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `Odid` int(11) NOT NULL AUTO_INCREMENT COMMENT '详细订单号',
  `Number` int(11) DEFAULT NULL COMMENT '食物序号',
  `Oid` varchar(50) DEFAULT NULL COMMENT '所属订单',
  `Fname` varchar(20) DEFAULT NULL COMMENT '食物名字',
  `Price` double DEFAULT NULL COMMENT '食物价格',
  `Tips` varchar(50) DEFAULT NULL COMMENT '食物备注',
  PRIMARY KEY (`Odid`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`Odid`,`Number`,`Oid`,`Fname`,`Price`,`Tips`) values (47,1,'1583822244944051195','白菜炖粉条',30,'测试'),(48,2,'1583822244944051195','口水猪肝',35,'测试'),(49,3,'1583822244944051195','香菜拌木耳',15,'测试'),(50,1,'1583833163253598203','白菜炖粉条',30,'1'),(51,2,'1583833163253598203','香菜拌豆腐皮',15,'1'),(52,1,'1584009337165882234','白菜炖粉条',30,'测试'),(53,2,'1584009337165882234','鸡公煲',40,'测试'),(54,3,'1584009337165882234','香菜拌豆腐皮',15,'测试'),(55,1,'1584172816119780125','红烧肉',25,'测试'),(56,2,'1584172816119780125','白菜炖粉条',30,'测试'),(57,3,'1584172816119780125','凉拌黄瓜',15,'测试'),(58,1,'1584172857822869743','白菜炖粉条',30,'测试'),(59,2,'1584172857822869743','鸡公煲',40,'测试'),(60,3,'1584172857822869743','香菜拌豆腐皮',15,'测试'),(61,1,'1584176205839884805','鸡公煲',40,'测试'),(62,2,'1584176205839884805','铁板豆腐',20,'测试'),(63,3,'1584176205839884805','香菜拌木耳',15,'测试');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `Oid` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单号',
  `Tid` int(11) NOT NULL COMMENT '桌台号（外键）',
  `Number` int(11) NOT NULL COMMENT '顾客人数',
  `Sid` int(11) NOT NULL COMMENT '负责员工号',
  `Time` datetime NOT NULL COMMENT '下单时间',
  `PrePrice` double DEFAULT '0' COMMENT '预计价格',
  `DiscountPrice` double DEFAULT '0' COMMENT '折扣价格',
  `RealPrice` double DEFAULT '0' COMMENT '实际价格',
  `Payment` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '支付方式',
  `Phone` varchar(18) NOT NULL COMMENT '电话号码',
  `Name` varchar(10) DEFAULT NULL COMMENT '顾客姓名',
  `Status` int(11) DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`Oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`Oid`,`Tid`,`Number`,`Sid`,`Time`,`PrePrice`,`DiscountPrice`,`RealPrice`,`Payment`,`Phone`,`Name`,`Status`) values ('1583822244944051195',1,6,3,'2020-03-10 14:37:41',80,0,80,'微信','12345678911','李四',1),('1583833163253598203',2,10,3,'2020-03-10 17:39:34',45,0,45,'微信','56565656566','王五',1),('1584009337165882234',1,8,1,'2020-03-12 18:35:55',85,0,85,'支付宝','12345678911','六六',1),('1584172816119780125',1,10,1,'2020-03-14 16:00:55',70,0,70,'微信','12345678911','李六',1),('1584172857822869743',2,10,2,'2020-03-14 16:01:12',85,0,85,'微信','12345678911','王六',1),('1584176205839884805',1,10,1,'2020-03-14 16:57:02',NULL,NULL,NULL,NULL,'12345679111','六六',0);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接口路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限表';

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`url`) values (1,'indexpm','/order/main.html'),(2,'menupm','/food/fmain.html'),(3,'staffpm','/staff/smain.html'),(4,'tablepm','/table/tmain.html'),(5,'financepm','/finance/fmain.html'),(6,'syspm','/sys_user/sysusermain.html');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(64) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`description`) values (1,'root','超级管理员'),(2,'boos','餐饮老板'),(3,'staff','员工');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` int(32) NOT NULL,
  `permission_id` int(32) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_id`,`permission_id`) values (1,6),(2,1),(2,2),(2,3),(2,4),(2,5),(3,1);

/*Table structure for table `staffs` */

DROP TABLE IF EXISTS `staffs`;

CREATE TABLE `staffs` (
  `Sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `Name` varchar(10) NOT NULL COMMENT '员工姓名',
  `Gender` enum('男','女') DEFAULT NULL COMMENT '性别',
  `Year` int(11) DEFAULT NULL COMMENT '年龄',
  `Salary` double DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`Sid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `staffs` */

insert  into `staffs`(`Sid`,`Name`,`Gender`,`Year`,`Salary`) values (1,'宋小宝','男',38,200),(2,'宋丹丹','女',40,200),(3,'陈赫','男',35,200),(8,'赵尚博','男',24,1);

/*Table structure for table `tables` */

DROP TABLE IF EXISTS `tables`;

CREATE TABLE `tables` (
  `Tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '桌台号',
  `SeatNum` int(11) NOT NULL COMMENT '座位数',
  `Room` varchar(10) NOT NULL COMMENT '所属房间',
  `Status` int(11) DEFAULT NULL COMMENT '使用状态',
  PRIMARY KEY (`Tid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `tables` */

insert  into `tables`(`Tid`,`SeatNum`,`Room`,`Status`) values (1,8,'福寿厅',1),(2,10,'吉祥厅',0),(3,8,'吉祥厅',0),(4,12,'忘忧厅',0),(5,10,'忘忧厅',0),(6,10,'忘忧厅',0),(7,10,'月圆厅',0),(8,12,'月圆厅',0),(9,8,'月圆厅',0),(10,8,'吉祥厅',0),(11,10,'吉祥厅',0),(12,20,'如意厅',0),(13,20,'如意厅',0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(32) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(128) DEFAULT NULL COMMENT '用户名',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `create_time` datetime DEFAULT NULL,
  `salt` varchar(128) DEFAULT NULL,
  `role_id` int(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`create_time`,`salt`,`role_id`) values (1,'test','8288a5853d0497fb0e3251215734f4ec','2020-02-23 21:02:13','ccf895fbbcff4f8d1dc44ce7a009a118',3),(2,'admin','dddd16656e45b56d75cbf937752e171d','2020-02-24 16:11:30','1d78e8cb6d091b7e0f7de2b43fbef199',1),(3,'xiaoming','0c11f3467699f62eda293f591a24e8f0','2020-02-24 16:14:30','1f537fbb20d8ab767e1bd5cbdb820c34',2),(4,'xiaohong','17ada036d565e513d1b785c440fb51d7','2020-02-24 16:19:07','4bddfbae648b04cecc7fe8d92a1cad18',3);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `role_id` int(32) NOT NULL,
  `user_id` int(32) NOT NULL,
  `remarks` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`role_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

/*Data for the table `user_role` */

insert  into `user_role`(`role_id`,`user_id`,`remarks`) values (1,2,'admin是超级管理员'),(2,3,'xiaoming是老板'),(3,1,'test是员工'),(3,4,'xiaohong是员工');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
