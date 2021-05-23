/*
 Navicat Premium Data Transfer

 Source Server         : Local
 Source Server Type    : MySQL
 Source Server Version : 50520
 Source Host           : localhost:3306
 Source Schema         : ttmsnew

 Target Server Type    : MySQL
 Target Server Version : 50520
 File Encoding         : 65001

 Date: 21/01/2021 00:23:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `cus_id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `cus_gender` smallint(6) DEFAULT 1 COMMENT '说明：\r\n            0 女\r\n            1 男',
  `cus_telnum` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `cus_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `cus_uid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `cus_pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '登陆密码',
  `cus_status` smallint(6) DEFAULT 1 COMMENT '说明：\r\n            0：禁用\r\n            1：启用',
  `cus_balance` decimal(10, 0) DEFAULT 1000 COMMENT '账户余额',
  `cus_paypwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付密码',
  PRIMARY KEY (`cus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '田冲', 1, '18911111111', 'tianchong@163.com', 'tianchong', '123456', 1, 1000, '123456');
INSERT INTO `customer` VALUES (2, '张萌', 0, '18922222222', 'zhangmeng@tom.com', 'zhangmeng', '123456', 1, 1000, '123456');

-- ----------------------------
-- Table structure for data_dict
-- ----------------------------
DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict`  (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `super_dict_id` int(11) DEFAULT NULL COMMENT '父id',
  `dict_index` int(11) DEFAULT NULL COMMENT '同级顺序',
  `dict_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`dict_id`) USING BTREE,
  INDEX `FK_super_child_dict`(`super_dict_id`) USING BTREE,
  CONSTRAINT `FK_super_child_dict` FOREIGN KEY (`super_dict_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of data_dict
-- ----------------------------
INSERT INTO `data_dict` VALUES (1, NULL, 1, '数据字典', '根');
INSERT INTO `data_dict` VALUES (2, 1, 1, 'PLAYTYPE', '影片类型');
INSERT INTO `data_dict` VALUES (3, 1, 2, 'PLAYLANG', '影片语言');
INSERT INTO `data_dict` VALUES (4, 1, 3, '锁失效时间', '10');
INSERT INTO `data_dict` VALUES (5, 2, 1, 'History', '历史');
INSERT INTO `data_dict` VALUES (6, 2, 2, 'Anime', '动漫');
INSERT INTO `data_dict` VALUES (7, 2, 3, 'Drama', '生活');
INSERT INTO `data_dict` VALUES (8, 2, 4, 'Horror', '恐怖');
INSERT INTO `data_dict` VALUES (9, 2, 5, 'War', '战争');
INSERT INTO `data_dict` VALUES (10, 2, 6, 'Sci-Fi', '科幻');
INSERT INTO `data_dict` VALUES (11, 2, 7, 'Romance', '爱情');
INSERT INTO `data_dict` VALUES (12, 2, 8, 'Comedy', '喜剧');
INSERT INTO `data_dict` VALUES (13, 2, 9, 'Action', '动作');
INSERT INTO `data_dict` VALUES (14, 3, 1, 'Chinese', '国语');
INSERT INTO `data_dict` VALUES (15, 3, 2, 'English', '英语');
INSERT INTO `data_dict` VALUES (16, 3, 3, 'Japanese', '日语');
INSERT INTO `data_dict` VALUES (17, 3, 4, 'Korean', '韩语');
INSERT INTO `data_dict` VALUES (18, 1, 4, 'admin', '系统管理员');
INSERT INTO `data_dict` VALUES (19, 1, 5, 'Accountant', '会计');
INSERT INTO `data_dict` VALUES (20, 1, 6, 'Saler', '售票员');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_id` int(11) DEFAULT NULL,
  `emp_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `emp_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `emp_gender` smallint(6) DEFAULT 1 COMMENT '说明：\r\n            0：女\r\n            1：男',
  `emp_telnum` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `emp_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `emp_pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `emp_status` smallint(6) DEFAULT 1 COMMENT '说明：\r\n            0：禁用\r\n            1：启用',
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `FK_emp_position`(`dict_id`) USING BTREE,
  CONSTRAINT `FK_emp_position` FOREIGN KEY (`dict_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 18, '000001', '张三', 1, '13311111111', '111@163.com', '123456', 1);
INSERT INTO `employee` VALUES (2, 20, '000002', '李四', 0, '13322222222', '222@21cn.com', '123456', 1);
INSERT INTO `employee` VALUES (3, 20, '000003', '王五', 1, '13333333333', '333@yeah.net', '123456', 1);
INSERT INTO `employee` VALUES (4, 20, '000004', '刘六', 0, '13344444444', '444@sina.com.cn', '123456', 1);
INSERT INTO `employee` VALUES (5, 20, '000005', '郑七', 1, '13355555555', '555@tom.com', '123456', 1);

-- ----------------------------
-- Table structure for play
-- ----------------------------
DROP TABLE IF EXISTS `play`;
CREATE TABLE `play`  (
  `play_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type_id` int(11) DEFAULT NULL,
  `dict_lang_id` int(11) DEFAULT NULL,
  `play_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `play_introduction` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `play_image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `play_video` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `play_length` int(11) DEFAULT NULL,
  `play_ticket_price` decimal(10, 2) DEFAULT NULL,
  `play_status` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n            0：待安排演出\r\n            1：已安排演出\r\n            -1：下线',
  PRIMARY KEY (`play_id`) USING BTREE,
  INDEX `FK_dict_lan_play`(`dict_lang_id`) USING BTREE,
  INDEX `FK_dict_type_play`(`dict_type_id`) USING BTREE,
  CONSTRAINT `FK_dict_lan_play` FOREIGN KEY (`dict_lang_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_dict_type_play` FOREIGN KEY (`dict_type_id`) REFERENCES `data_dict` (`dict_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of play
-- ----------------------------
INSERT INTO `play` VALUES (1, 7, 14, '我和我的家乡', '电影《我和我的家乡》定档2020年国庆，延续《我和我的祖国》集体创作的方式，由张艺谋担当总监制，宁浩担任总导演，张一白担任总策划，宁浩、徐峥、陈思诚、闫非&彭大魔、邓超&俞白眉分别执导五个故事。', 'images/property/1.jpg', NULL, 120, 35.00, 0);
INSERT INTO `play` VALUES (2, 7, 14, '七号房的礼物', '《7号房的礼物》\r\n是由李焕庆执导，柳承龙、葛素媛、郑进永、朴信惠等主演的喜剧电影。影片讲述了蒙受不白之冤入狱的李龙久和为了给父亲洗去冤屈而不懈努力的女儿之间的故事。该片于2013年1月23日在韩国上映。并成为2013年韩国电影票房排名第一。\r\n\r\n1997年，只有6岁儿童智商的智障男子李海报海报(12张)龙久（柳承龙饰）和可爱的女儿艺胜（葛素媛饰）相依为命，生活虽然简单清贫，却充满幸福。某天，执着为女儿买美少女战士书包的龙久意外卷入一起幼童诱拐奸杀案，而死者竟是警察局长的女儿。龙久懵懂无知，搞不清状况，昏头昏脑就被投入监狱。在7号牢房中，聚集着走私犯苏杨浩、诈骗犯崔春浩、通奸犯姜万范、恐吓犯老徐和抢劫犯申奉植等五毒俱全的“社会渣滓”。龙久孩子般纯洁的心渐渐感动了这几个“大坏蛋”，他们甚至不惜冒险将艺胜带入牢房与父亲相会。黑暗冰冷的监牢内，7号牢房阳光满满。', 'images/property/5.jpg', NULL, 108, 30.00, 0);
INSERT INTO `play` VALUES (3, 9, 14, '天道王', '《天道王》\r\n是一部由缘世达国际影视文化传媒(北京)公司、奇雪文化投影城有限公司联合出品，苑本立执导，巩峥、陶红、杨钧丞、刘楚玄主演的电影。《天道王》以中国东北猎人加入抗日联军,与日军展开惨烈战斗为故事主线。主要讲述了抗战时期，东北深山老林猎人为了小家，为了国家奋起抗日英勇杀敌，为掩护东北抗联总部撤退，顽强阻击日军，谱写惊天动地抗日史诗的故事。东北抗日联军“十二烈士小孤山阻击战”和“李炮营阻击战”的原型故事；平民视角的东北猎人抗日；强情节、超反转的生死阻击。\r\n\r\n《天道王》以东北抗日联军为史料,以中国抗日战役中以少胜多最著名一次战役——“烈士山战役”为渊源,主要讲述了抗战时期,东北山野猎人奋起抗日救国,义勇军战败后猎人返山继续游猎生活,又谱写惊天动地抗日史诗的故事。 [1] 不愿做亡国奴的猎人们,1937年在抗联的感召下加入了抗联举起抗日救国大旗,成为主力,与日军斗智斗勇。1938年日军蓄谋大讨伐,猎人出身的十二名战士用生命全力阻击敌人,为抗联总部突围和群众转移赢得宝贵时间。', 'images/property/6.jpg', NULL, 125, 33.00, 0);
INSERT INTO `play` VALUES (4, 7, 14, '喜宝', '《喜宝》\r\n《喜宝》是由北京日光鼎盛影视文化有限公司、北京轴心影视文化有限公司出品的作品，王丹阳执导，郭采洁、张国柱主演。该片根据著名作家亦舒的原著同名小说改编的电影，讲述了成绩优异但家境贫寒的剑桥学生姜喜宝在飞机上认识了富家女勖聪慧，此后更是被其父亲和兄长追求，最终与其父勖存姿展开一段恋情，命运也因此彻底改变的故事。\r\n\r\n喜宝（郭采洁饰）因家庭困境中止了英国学业返回国内，在飞机上结识了单纯可爱的富家千金勖聪慧（李彦漫 饰），回国后先后遭遇了母亲过世、从未见过的父亲上门索要钱财，被房东赶出无家可归。勖家帮助喜宝摆脱了困境，同时喜宝也深陷勖家男人的感情旋涡。曾经想要很多很多爱的喜宝，忽然拥有了选择很多很多钱的机会，年轻的喜宝能否明白爱情的真谛……', 'images/property/8.jpg', NULL, 153, 42.00, 0);
INSERT INTO `play` VALUES (5, 6, 14, '姜子牙', '动画电影《姜子牙》的故事发生于封神大战后。姜子牙率领众神伐纣，赢得封神大战胜利，以为可以唤回世间安宁。然而，一切并未结束。一个偶然，姜子牙发现了原来“封神大战”之下酝酿着更大的阴谋。姜子牙开始踏上探寻真相的旅途......', 'images/property/2.jpg', NULL, 110, 40.00, 0);
INSERT INTO `play` VALUES (6, 7, 14, '夺冠', '2008年8月15日，北京奥运会女排比赛，中国VS美国。戴着金丝框眼镜的郎平（巩俐 饰）坐在美国队教练席上，大气沉稳，目光如电；中国队教练陈忠和（黄渤 饰）站在场边，全神贯注，面带笑容。陈忠和望向郎平，目光充满深意，不断经过的人影遮蔽了他的视线，中国女排三十余年的沉浮图景被缓缓打开……', 'images/property/3.jpg', NULL, 135, 38.00, 0);
INSERT INTO `play` VALUES (7, 7, 14, '一点就到家', '《一点就到家》\r\n是由陈可辛监制，许宏宇执导，刘昊然、彭昱畅、尹昉领衔主演的农村题材喜剧片。该片讲述了三个性格迥异的年轻人从大城市回到云南千年古寨开启创业旅程的故事。\r\n\r\n魏晋北（刘昊然 饰）、彭秀兵（彭昱畅 饰）、李绍群（尹昉 饰）三个性格迥异的年轻人从大城市回到云南千年古寨，机缘巧合下合伙干电商。三个年轻人在走弯路后，一拍即合携手开启创业之旅，他们明白了电商下乡，不仅仅是把城里面的东西卖到农村来，也是要把农村的东西卖出去。与古寨“格格不入”的他们用真诚改变了所有人，开启了一段纯真且荒诞的创业旅程。', 'images/property/7.jpg', NULL, 147, 35.00, 0);
INSERT INTO `play` VALUES (8, 13, 14, '急先锋', '中国商人秦国立在英国惨遭雇佣兵组织“北极狼”绑架，秦的女儿Fareeda（徐若晗 饰）也卷入其中，遭到追杀。千钧一发之际，急先锋国际安保团队成为他们唯一的希望，由总指挥唐焕庭（成龙饰）带领雷震宇（杨洋饰）、张凯旋（艾伦饰）、弥雅（母其弥雅饰）、神雕（朱正廷饰）等组成的急先锋行动小组，上天入地，辗转全球各地施展惊险营救。解救人质的过程中，竟发现“北极狼”背后的犯罪集团还策划了一场惊天密谋……', 'images/property/4.jpg', NULL, 108, 36.00, 0);
INSERT INTO `play` VALUES (9, 7, 14, '\r\n我在时间尽头等你', '林格（李鸿其 饰）一次次重启时空，只为与恋人邱倩（李一桐 饰）再次相遇。一生只爱一个人，希望开头是你，结尾也是你。2020年七夕最深情的告白: 我在时间尽头等你。', 'images/property/9.jpg', NULL, 115, 37.00, 0);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_parent` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `res_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `res_URL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`res_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '剧院管理', '演出厅管理', 'studio');
INSERT INTO `resource` VALUES (2, '剧院管理', '剧目管理', 'play');
INSERT INTO `resource` VALUES (3, '剧院管理', '演出计划', 'schedule');
INSERT INTO `resource` VALUES (4, '剧院管理', '验票管理', 'check');
INSERT INTO `resource` VALUES (5, '用户管理', '观众管理', 'audience');
INSERT INTO `resource` VALUES (6, '用户管理', '员工管理', 'employee');
INSERT INTO `resource` VALUES (7, '票务管理', '售票管理', 'ticket');
INSERT INTO `resource` VALUES (8, '票务管理', '退票管理', 'refund');
INSERT INTO `resource` VALUES (9, '票务管理', '销售统计', 'personalstat');
INSERT INTO `resource` VALUES (10, '财务管理', '票款管理', 'fare');
INSERT INTO `resource` VALUES (11, '财务管理', '销售业绩', 'saleperf');
INSERT INTO `resource` VALUES (12, '财务管理', '销售统计', 'salestat');
INSERT INTO `resource` VALUES (13, '权限管理', '资源管理', 'resource');
INSERT INTO `resource` VALUES (14, '权限管理', '角色管理', 'role');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `role_res_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `res_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_res_id`) USING BTREE,
  INDEX `FK_res_role`(`res_id`) USING BTREE,
  INDEX `FK_role_resource`(`role_id`) USING BTREE,
  CONSTRAINT `FK_res_role` FOREIGN KEY (`res_id`) REFERENCES `resource` (`res_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_role_resource` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `sale_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `cus_id` int(11) DEFAULT NULL,
  `sale_time` datetime DEFAULT NULL,
  `sale_payment` decimal(10, 2) DEFAULT NULL,
  `sale_change` decimal(10, 2) DEFAULT NULL,
  `sale_type` smallint(6) DEFAULT NULL COMMENT '类别取值含义：\r\n            1：销售单\r\n            -1：退款单',
  `sale_status` smallint(6) DEFAULT NULL COMMENT '销售单状态如下：\r\n            0：代付款\r\n            1：已付款',
  `sale_sort` smallint(6) DEFAULT NULL COMMENT '取值说明：\r\n            1：顾客自购/退票\r\n            0：售票员销售/退票',
  PRIMARY KEY (`sale_ID`) USING BTREE,
  INDEX `FK_customer_sale`(`cus_id`) USING BTREE,
  INDEX `FK_employee_sale`(`emp_id`) USING BTREE,
  CONSTRAINT `FK_customer_sale` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_employee_sale` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sale_item
-- ----------------------------
DROP TABLE IF EXISTS `sale_item`;
CREATE TABLE `sale_item`  (
  `sale_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint(20) DEFAULT NULL,
  `sale_ID` bigint(20) DEFAULT NULL,
  `sale_item_price` decimal(10, 2) DEFAULT NULL,
  PRIMARY KEY (`sale_item_id`) USING BTREE,
  INDEX `FK_sale_sale_item`(`sale_ID`) USING BTREE,
  INDEX `FK_ticket_sale_item`(`ticket_id`) USING BTREE,
  CONSTRAINT `FK_sale_sale_item` FOREIGN KEY (`sale_ID`) REFERENCES `sale` (`sale_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ticket_sale_item` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `sched_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_id` int(11) DEFAULT NULL,
  `play_id` int(11) DEFAULT NULL,
  `sched_time` datetime NOT NULL,
  `sched_ticket_price` decimal(10, 2) DEFAULT NULL,
  `sched_status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`sched_id`) USING BTREE,
  INDEX `FK_play_sched`(`play_id`) USING BTREE,
  INDEX `FK_studio_sched`(`studio_id`) USING BTREE,
  CONSTRAINT `FK_play_sched` FOREIGN KEY (`play_id`) REFERENCES `play` (`play_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_studio_sched` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (1, 7, 1, '2020-10-17 10:00:00', 35.00, 1);
INSERT INTO `schedule` VALUES (2, 7, 1, '2020-10-17 12:20:00', 35.00, 1);
INSERT INTO `schedule` VALUES (3, 7, 1, '2020-10-17 14:40:00', 35.00, 1);
INSERT INTO `schedule` VALUES (4, 7, 1, '2020-10-17 17:00:00', 35.00, 1);
INSERT INTO `schedule` VALUES (5, 7, 1, '2020-10-17 19:40:00', 35.00, 1);

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat`  (
  `seat_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_id` int(11) DEFAULT NULL,
  `seat_row` int(11) DEFAULT NULL,
  `seat_column` int(11) DEFAULT NULL,
  `seat_status` smallint(6) DEFAULT NULL COMMENT '说明：0损坏，1正常',
  PRIMARY KEY (`seat_id`) USING BTREE,
  INDEX `FK_studio_seat`(`studio_id`) USING BTREE,
  CONSTRAINT `FK_studio_seat` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of seat
-- ----------------------------
INSERT INTO `seat` VALUES (1, 7, 1, 1, 1);
INSERT INTO `seat` VALUES (2, 7, 1, 2, 1);
INSERT INTO `seat` VALUES (3, 7, 1, 3, 1);
INSERT INTO `seat` VALUES (4, 7, 1, 4, 1);
INSERT INTO `seat` VALUES (5, 7, 1, 5, 1);
INSERT INTO `seat` VALUES (6, 7, 1, 6, 1);
INSERT INTO `seat` VALUES (7, 7, 1, 7, 1);
INSERT INTO `seat` VALUES (8, 7, 1, 8, 1);
INSERT INTO `seat` VALUES (9, 7, 2, 1, 1);
INSERT INTO `seat` VALUES (10, 7, 2, 2, 1);
INSERT INTO `seat` VALUES (11, 7, 2, 3, 1);
INSERT INTO `seat` VALUES (12, 7, 2, 4, 1);
INSERT INTO `seat` VALUES (13, 7, 2, 5, 1);
INSERT INTO `seat` VALUES (14, 7, 2, 6, 1);
INSERT INTO `seat` VALUES (15, 7, 2, 7, 1);
INSERT INTO `seat` VALUES (16, 7, 2, 8, 1);
INSERT INTO `seat` VALUES (17, 7, 3, 1, 1);
INSERT INTO `seat` VALUES (18, 7, 3, 2, 1);
INSERT INTO `seat` VALUES (19, 7, 3, 3, 1);
INSERT INTO `seat` VALUES (20, 7, 3, 4, 1);
INSERT INTO `seat` VALUES (21, 7, 3, 5, 1);
INSERT INTO `seat` VALUES (22, 7, 3, 6, 1);
INSERT INTO `seat` VALUES (23, 7, 3, 7, 1);
INSERT INTO `seat` VALUES (24, 7, 3, 8, 1);
INSERT INTO `seat` VALUES (25, 7, 4, 1, 1);
INSERT INTO `seat` VALUES (26, 7, 4, 2, 1);
INSERT INTO `seat` VALUES (27, 7, 4, 3, 1);
INSERT INTO `seat` VALUES (28, 7, 4, 4, 1);
INSERT INTO `seat` VALUES (29, 7, 4, 5, 1);
INSERT INTO `seat` VALUES (30, 7, 4, 6, 1);
INSERT INTO `seat` VALUES (31, 7, 4, 7, 1);
INSERT INTO `seat` VALUES (32, 7, 4, 8, 1);
INSERT INTO `seat` VALUES (33, 7, 5, 1, 1);
INSERT INTO `seat` VALUES (34, 7, 5, 2, 1);
INSERT INTO `seat` VALUES (35, 7, 5, 3, 1);
INSERT INTO `seat` VALUES (36, 7, 5, 4, 1);
INSERT INTO `seat` VALUES (37, 7, 5, 5, 1);
INSERT INTO `seat` VALUES (38, 7, 5, 6, 1);
INSERT INTO `seat` VALUES (39, 7, 5, 7, 1);
INSERT INTO `seat` VALUES (40, 7, 5, 8, 1);
INSERT INTO `seat` VALUES (41, 7, 6, 1, 1);
INSERT INTO `seat` VALUES (42, 7, 6, 2, 1);
INSERT INTO `seat` VALUES (43, 7, 6, 3, 1);
INSERT INTO `seat` VALUES (44, 7, 6, 4, 1);
INSERT INTO `seat` VALUES (45, 7, 6, 5, 1);
INSERT INTO `seat` VALUES (46, 7, 6, 6, 1);
INSERT INTO `seat` VALUES (47, 7, 6, 7, 1);
INSERT INTO `seat` VALUES (48, 7, 6, 8, 1);
INSERT INTO `seat` VALUES (49, 7, 7, 1, 1);
INSERT INTO `seat` VALUES (50, 7, 7, 2, 1);
INSERT INTO `seat` VALUES (51, 7, 7, 3, 1);
INSERT INTO `seat` VALUES (52, 7, 7, 4, 1);
INSERT INTO `seat` VALUES (53, 7, 7, 5, 1);
INSERT INTO `seat` VALUES (54, 7, 7, 6, 1);
INSERT INTO `seat` VALUES (55, 7, 7, 7, 1);
INSERT INTO `seat` VALUES (56, 7, 7, 8, 1);
INSERT INTO `seat` VALUES (57, 7, 8, 1, 1);
INSERT INTO `seat` VALUES (58, 7, 8, 2, 1);
INSERT INTO `seat` VALUES (59, 7, 8, 3, 1);
INSERT INTO `seat` VALUES (60, 7, 8, 4, 1);
INSERT INTO `seat` VALUES (61, 7, 8, 5, 1);
INSERT INTO `seat` VALUES (62, 7, 8, 6, 1);
INSERT INTO `seat` VALUES (63, 7, 8, 7, 1);
INSERT INTO `seat` VALUES (64, 7, 8, 8, 1);

-- ----------------------------
-- Table structure for studio
-- ----------------------------
DROP TABLE IF EXISTS `studio`;
CREATE TABLE `studio`  (
  `studio_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `studio_row_count` int(11) DEFAULT NULL,
  `studio_col_count` int(11) DEFAULT NULL,
  `studio_introduction` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `studio_status` smallint(6) DEFAULT 1 COMMENT '说明：\r\n   0：禁用\r\n   1：启用',
  PRIMARY KEY (`studio_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of studio
-- ----------------------------
INSERT INTO `studio` VALUES (1, '1号厅', 8, 8, '1号厅', 1);
INSERT INTO `studio` VALUES (2, '激光MAX厅', 8, 8, '激光MAX厅', 1);
INSERT INTO `studio` VALUES (5, '全景声MAX厅', 10, 10, '全景声MAX厅', 1);
INSERT INTO `studio` VALUES (7, 'VIP厅', 8, 8, 'VIP厅', 1);
INSERT INTO `studio` VALUES (8, '杜比厅', 9, 9, '杜比厅', 1);

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) DEFAULT NULL,
  `sched_id` int(11) DEFAULT NULL,
  `ticket_price` decimal(10, 2) DEFAULT NULL,
  `ticket_status` smallint(6) DEFAULT NULL COMMENT '含义如下：\r\n            0：待销售\r\n            1：锁定\r\n            9：卖出',
  `ticket_locktime` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00',
  PRIMARY KEY (`ticket_id`) USING BTREE,
  INDEX `FK_sched_ticket`(`sched_id`) USING BTREE,
  INDEX `FK_seat_ticket`(`seat_id`) USING BTREE,
  CONSTRAINT `FK_sched_ticket` FOREIGN KEY (`sched_id`) REFERENCES `schedule` (`sched_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_seat_ticket` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES (1, 1, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (2, 2, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (3, 3, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (4, 4, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (5, 5, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (6, 6, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (7, 7, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (8, 8, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (9, 9, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (10, 10, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (11, 11, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (12, 12, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (13, 13, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (14, 14, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (15, 15, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (16, 16, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (17, 17, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (18, 18, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (19, 19, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (20, 20, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (21, 21, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (22, 22, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (23, 23, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (24, 24, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (25, 25, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (26, 26, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (27, 27, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (28, 28, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (29, 29, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (30, 30, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (31, 31, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (32, 32, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (33, 33, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (34, 34, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (35, 35, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (36, 36, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (37, 37, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (38, 38, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (39, 39, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (40, 40, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (41, 41, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (42, 42, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (43, 43, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (44, 44, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (45, 45, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (46, 46, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (47, 47, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (48, 48, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (49, 49, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (50, 50, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (51, 51, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (52, 52, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (53, 53, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (54, 54, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (55, 55, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (56, 56, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (57, 57, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (58, 58, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (59, 59, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (60, 60, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (61, 61, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (62, 62, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (63, 63, 1, 35.00, 0, '2000-01-01 00:00:00');
INSERT INTO `ticket` VALUES (64, 64, 1, 35.00, 0, '2000-01-01 00:00:00');

-- ----------------------------
-- Table structure for usr_role
-- ----------------------------
DROP TABLE IF EXISTS `usr_role`;
CREATE TABLE `usr_role`  (
  `usr_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`usr_role_id`) USING BTREE,
  INDEX `FK_role_user`(`role_id`) USING BTREE,
  INDEX `FK_user_role`(`emp_id`) USING BTREE,
  CONSTRAINT `FK_role_user` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_user_role` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for v_choose_ticket_info
-- ----------------------------
DROP VIEW IF EXISTS `v_choose_ticket_info`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_choose_ticket_info` AS select ticket.ticket_id, ticket.ticket_price,ticket.ticket_status, ticket.ticket_locktime
,seat.seat_id, seat.seat_row,seat.seat_column,seat.seat_status,
studio_name, schedule.sched_time,play.play_name
from ticket, seat, studio,schedule,play
where ticket.seat_id=seat.seat_id and studio.studio_id=seat.studio_id
and schedule.sched_id=ticket.sched_id=schedule.sched_id and play.play_id=schedule.play_id ;

SET FOREIGN_KEY_CHECKS = 1;
