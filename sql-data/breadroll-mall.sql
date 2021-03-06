/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : breadroll-mall

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 04/03/2022 21:58:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `banner_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '商品广告牌id',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品链接',
  `banner_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告宣传栏链接',
  PRIMARY KEY (`banner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品广告轮播图' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (4, 'Redmi 智能电视 MAX 98', 'http://mall.qiudb.top/#/MallPurchase?id=52', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/bannerImage/9ffd6bc5933445b5a2fe03174f951bac-lb2.jpg');
INSERT INTO `banner` VALUES (5, '小米电视大师 82英寸至尊纪念版', 'http://mall.qiudb.top/#/MallPurchase?id=53', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/bannerImage/9e4756c61e57481c9aed4717af6e0c7a-0ef4160c861b998239bce9adb82341e7.webp');
INSERT INTO `banner` VALUES (6, '米家智能多功能养生壶', 'http://mall.qiudb.top/#/MallPurchase?id=54', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/bannerImage/aa81049088b848348ab6a923e7c5bdf4-lb4.webp');
INSERT INTO `banner` VALUES (7, 'Redmi K30 5G', 'http://mall.qiudb.top/#/MallPurchase?id=55', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/bannerImage/aa8a69a89d184d07902e429e1b1e8460-lb1.jpg');
INSERT INTO `banner` VALUES (8, '小米10', 'http://mall.qiudb.top/#/MallPurchase?id=56', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/bannerImage/6a556da3d10142648197217bd4dba3b3-lb5.jpg');

-- ----------------------------
-- Table structure for logistics
-- ----------------------------
DROP TABLE IF EXISTS `logistics`;
CREATE TABLE `logistics`  (
  `logistic_id` int(6) NOT NULL AUTO_INCREMENT COMMENT '物流id',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `sender` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人',
  `sender_tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人联系方式',
  `sender_add` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人联系地址',
  `receiver` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `receiver_tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人联系方式',
  `receiver_add` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人联系地址',
  `parcel_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流公司',
  PRIMARY KEY (`logistic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物流表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of logistics
-- ----------------------------
INSERT INTO `logistics` VALUES (2, '21111319667', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (3, '21111111271', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (5, '21111447921', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (7, '21111420537', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (8, '211322314850', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (9, '211322311410', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (11, '211322312375', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (12, '211511323447', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (13, '211517184317', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '中通快递');
INSERT INTO `logistics` VALUES (14, '211518238993', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (15, '211518588512', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (16, '211521424925', '花卷', '400-626-1123', '河北师范大学', 'z', '15233187989', '河北省 衡水市 桃城区 (aaa)', '花卷物流');
INSERT INTO `logistics` VALUES (17, '211521426089', '花卷', '400-626-1123', '河北师范大学', 'z', '15233187989', '河北省 衡水市 桃城区 (aaa)', '花卷物流');
INSERT INTO `logistics` VALUES (18, '211518557886', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (19, '211322599987', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (20, '211322315037', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (21, '2141417431071', '花卷', '400-626-1123', '河北师范大学', '邵咏', '15233088662', '河北省 石家庄市 市辖区 (河北省衡水市枣强县张秀屯镇前崔家庄村)', '花卷物流');
INSERT INTO `logistics` VALUES (22, '2162123561642', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (23, '2222616509327', '花卷', '400-626-1123', '河北师范大学', 'M', '15233088662', '河北省 石家庄市 裕华区 (河北师范大学)', '花卷物流');
INSERT INTO `logistics` VALUES (25, '223110263492', '测试', '15233088662', '天津市 市辖区 河东区 (11)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (26, '223110157600', '花卷', '400-626-1123', '河北师范大学', '测试', '15233088662', '天津市 市辖区 河东区 (11)', '花卷物流');
INSERT INTO `logistics` VALUES (27, '223110455311', '花卷', '400-626-1123', '河北师范大学', '测试人员', '15233088662', '天津市 市辖区 河东区 (11)', '花卷物流');
INSERT INTO `logistics` VALUES (29, '223420428827', '花卷', '400-626-1123', '河北师范大学', '测试人员', '15233088662', '北京市 市辖区 东城区 (test)', '花卷物流');
INSERT INTO `logistics` VALUES (30, '223110451817', '花卷', '400-626-1123', '河北师范大学', '测试人员', '15233088662', '天津市 市辖区 河东区 (11)', '花卷物流');
INSERT INTO `logistics` VALUES (31, '223421202909', '测试人员', '15233088662', '北京市 市辖区 东城区 (test)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');
INSERT INTO `logistics` VALUES (33, '223421276224', '测试人员', '15233088662', '北京市 市辖区 东城区 (test)', '花卷', '400-626-1123', '河北师范大学', '花卷物流');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(7) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_specs` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `user_account` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `contact_way` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `pay_price` double NOT NULL COMMENT '商品金额',
  `pay_amount` int(6) NOT NULL COMMENT '购买数量',
  `pay_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `order_from` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单来源',
  `order_state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
  `accept_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `return_state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '退货状态',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`order_no`) USING BTREE,
  INDEX `product_no`(`product_no`) USING BTREE,
  INDEX `order_ibfk_2`(`user_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (8, '21111447921', '2021-12-24 14:10:48', '2020123114125441', '8GB+256GB', 'qiudb.top@aliyun.com', 'M', '15233088662', 8369, 1, '支付宝', '网页商城', '已退货', '河北省 石家庄市 裕华区 (河北师范大学)', 1);
INSERT INTO `order` VALUES (31, 'Vip2113217352', '2021-01-03 21:47:51', 'Vip0001', NULL, '1325554003@qq.com', '马英发', '15233088661', 88, 1, '支付宝', '网页商城', '开通成功', '河北省 石家庄市 裕华区 (河北师范大学)', 0);
INSERT INTO `order` VALUES (65, '2162123561642', '2021-12-25 23:05:57', '20211517042458', 'M', 'qiudb.top@aliyun.com', 'M', '15233088662', 136, 1, '支付宝', '网页商城', '已评价', '河北省 石家庄市 裕华区 (河北师范大学)', 0);
INSERT INTO `order` VALUES (66, '21122518383980', '2021-12-25 10:59:40', '20211516383756', 'S', 'qiudb.top@aliyun.com', 'M', '15233088662', 626, 1, '支付宝', '网页商城', '待付款', '河北省 石家庄市 裕华区 (河北师范大学)', 0);
INSERT INTO `order` VALUES (67, '2222616509327', '2022-02-26 16:48:52', '2020123114125441', '6GB+256GB', 'qiudb.top@aliyun.com', 'M', '15233088662', 8369, 1, '支付宝', '网页商城', '已评价', '河北省 石家庄市 裕华区 (河北师范大学)', 0);
INSERT INTO `order` VALUES (68, 'Vip2231101108', '2022-03-01 10:01:09', 'Vip0001', NULL, '15233088662@163.com', '测试', '15233088662', 88, 1, '支付宝', '网页商城', '开通成功', '天津市 市辖区 河东区 (11)', 0);
INSERT INTO `order` VALUES (69, '223110263492', '2022-03-01 10:03:28', '20211516383756', 'S', '15233088662@163.com', '测试', '15233088662', 626, 1, '支付宝', '网页商城', '已退货', '天津市 市辖区 河东区 (11)', 1);
INSERT INTO `order` VALUES (70, '223110157600', '2022-03-01 10:06:16', '20211517010257', 'M', '15233088662@163.com', '测试', '15233088662', 616, 4, '支付宝', '网页商城', '已拒绝', '天津市 市辖区 河东区 (11)', 0);
INSERT INTO `order` VALUES (71, '223110451817', '2022-03-01 10:15:45', '20211516344655', 'L', '15233088662@163.com', '测试人员', '15233088662', 626, 1, '支付宝', '网页商城', '已发货', '天津市 市辖区 河东区 (11)', 0);
INSERT INTO `order` VALUES (72, '223110455311', '2022-03-01 10:16:47', '202012310940061', '6GB+128GB', '15233088662@163.com', '测试人员', '15233088662', 6747, 3, '支付宝', '网页商城', '已评价', '天津市 市辖区 河东区 (11)', 0);
INSERT INTO `order` VALUES (73, '223420428827', '2022-03-04 20:57:43', '202012302222140', '6GB+128GB', '15233088662@163.com', '测试人员', '15233088662', 3299, 1, '支付宝', '网页商城', '已收货', '北京市 市辖区 东城区 (test)', 0);
INSERT INTO `order` VALUES (74, '223421202909', '2022-03-04 21:07:21', '2020123114525945', 'M', '15233088662@163.com', '测试人员', '15233088662', 659, 1, '支付宝', '网页商城', '已退货', '北京市 市辖区 东城区 (test)', 1);
INSERT INTO `order` VALUES (75, '223421276224', '2022-03-04 21:09:28', '20211517042458', 'M', '15233088662@163.com', '测试人员', '15233088662', 136, 1, '支付宝', '网页商城', '已退货', '北京市 市辖区 东城区 (test)', 1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别',
  `product_describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '副标题',
  `product_brand` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `in_price` double NOT NULL COMMENT '进价',
  `out_price` double NOT NULL COMMENT '售价',
  `product_stock` int(6) NOT NULL COMMENT '库存',
  `lowest_stock` int(6) NOT NULL COMMENT '最低库存',
  `is_stockout` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否缺货',
  `is_new` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否新品',
  `is_sale` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否上架',
  `sale_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间',
  `product_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`product_no`) USING BTREE,
  INDEX `product_type`(`product_type`) USING BTREE,
  INDEX `product_brand`(`product_brand`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '202012302222140', '米家互联网洗烘一体机Pro 20kg', '生活家电', '国标双A+级洗烘能力 / 22种洗烘模式 / 除菌率达99.9%+ / 支持小爱同学语音遥控 / 支持OTA在线智能升级 / 智能空气洗 / 智能投放洗涤剂', '小米', 2899, 3299, 10, 10, 0, 1, 1, '2022-03-01 10:16:16', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/c833369200f2465db37b64f64f604514-ec20453216dcd42f982cffe5fdbc3115.webp');
INSERT INTO `product` VALUES (2, '202012310940061', 'Redmi K30 至尊纪念版', '手机', '120Hz弹出全面屏 / 天玑1000+旗舰处理器 / 索尼6400万四摄 / 立体声双扬声器 / 4500mAh+33W闪充 / 双模5G / 多功能NFC / 线性震动马达 / 红外遥控', '小米', 1899, 2499, 6, 5, 0, 0, 1, '2021-01-01 09:31:54', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/2b740f7f5aa44429ae9304a0b3b8c9e3-3b19bf0e7e599c1bbbce510fb0dbc8bc.webp');
INSERT INTO `product` VALUES (6, '202012310948192', 'Redmi 10X Pro 5G', '手机', '双5G待机/天玑820处理器/4800万流光相机+800万超广角/6.57\"三星AMOLED屏幕/屏幕指纹/4520mAh长续航/红外遥控', '小米', 2099, 2499, 8, 8, 0, 0, 1, '2020-12-31 09:48:58', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/41f622beaf314f689f5109aff0112a66-pms_1590373498.27778397.jpg');
INSERT INTO `product` VALUES (7, '202012311032223', '天梭力洛克系列钢带80机芯机械男表', '腕表', '为纪念天梭诞生150周年而推出的系列，因此取名天梭诞生地瑞士小镇力洛克。\n该系列部分表款的后盖有独特的设计，令人一探腕表跳动的韵律。\n上市以来成为了天梭系列中的畅销系列。', '天梭', 4650, 4800, 10, 5, 0, 0, 1, '2020-12-31 10:35:34', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/fbf1f88e9ae94661a40994789f5da800-t0064071105300_1_1_1.png');
INSERT INTO `product` VALUES (8, '202012311035354', '天梭魅时系列钢带石英女表', '腕表', '魅时系列 简约纯粹\n魅时系列作为天梭入门级的腕表系列，\n设计简单大气，充满活力，以不同基调与风格\n迎合多种风格佩戴者的心情与品位，\n深受年轻时尚族喜爱。', '天梭', 1899, 2050, 10, 5, 0, 0, 1, '2020-12-31 10:37:26', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/bbb65aa08af345a1bde8f45bba15e73f-t1092101103100_1.png');
INSERT INTO `product` VALUES (9, '202012311037275', '天梭俊雅系列皮带石英男表', '腕表', '俊雅系列复古文艺\n天梭俊雅系列整体设计曲线具有简约美学感，\n与1950年代的复古风格结合，\n散发浓郁的文艺气息，\n俊雅系列腕表其中的细节隐藏着天梭逾百年制表传统的美意与祝福。', '天梭', 2100, 2300, 15, 12, 0, 0, 1, '2020-12-31 10:39:00', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/8e7670595e1c40fe908ec254ba1cbb50-t0636101605800_1.png');
INSERT INTO `product` VALUES (10, '202012311039016', '天梭魅时系列皮带石英女表', '腕表', '瑞士设计经典优雅\n本款腕表为现代丽人打造，\n简洁优雅的设计贯穿着表盘和表带\n经典阿拉伯数字将清晰的时刻尽显眼底，\n蓝宝石玻璃表镜将质感升级', '天梭', 1650, 1750, 15, 10, 0, 0, 1, '2020-12-31 10:41:10', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/e3313158ba124c8bad9011304d0274d3-t1092101603200_1.png');
INSERT INTO `product` VALUES (11, '202012311041107', '天梭杜鲁尔系列皮带80机芯机械男表', '腕表', '杜鲁尔经典系列\n命名灵感源于天梭1907年在瑞士力洛克创建的制表厂所在的街道，\n满载百年的厚重历史和匠心传承。\n该系列带着“瑞士制造”的烙印。', '天梭', 5600, 5800, 7, 5, 0, 0, 1, '2020-12-31 10:43:27', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a6221d796e7b404492ba0aa29c55a394-t0994071604800_4.png');
INSERT INTO `product` VALUES (12, '202012311044178', '天梭弗拉明戈系列钢带石英女表', '腕表', '弗拉明戈系列\n火烈鸟的尊贵气质源于它纤细修长的双腿，\n而天梭弗拉明戈系列腕表灵感来自于此，\n缔造了优雅与流畅的外观设计\n似裙裾飘飘，\n富有动感之美。', '天梭', 2300, 2600, 8, 5, 0, 0, 1, '2020-12-31 10:44:55', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/4850a93929804c419a2eccbd29df5353-t0942101111100_1.png');
INSERT INTO `product` VALUES (13, '202012311044559', '天梭弗拉明戈系列钢带石英女表', '腕表', '蓝宝石玻璃表镜\n人工合成蓝宝石是硬度仅次于\n钻石的材料\n耐腐蚀易清洁\n明亮通透不易磨损不易刮伤', '天梭', 3250, 3450, 8, 4, 0, 0, 1, '2020-12-31 10:46:03', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/60ff003ec35d45a6b5a7cde7079b93af-t0942103311601.png');
INSERT INTO `product` VALUES (14, '2020123110460410', '天梭弗拉明戈系列皮带石英女表', '腕表', '该腕表整体设计源于成对火烈鸟忠贞\n不渝的爱情，一高一低的表耳设计带\n着窃窃私语的爱意，令经典腕表的设\n计增加了俏皮感', '天梭', 2350, 2450, 8, 4, 0, 0, 1, '2020-12-31 10:47:13', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/6d056f7377f04e5bb9e86badbc59b5c6-t0942101601100_1.png');
INSERT INTO `product` VALUES (15, '2020123110471411', '天梭弗拉明戈系列钢带石英女表', '腕表', '珍珠贝母柔光四溢\n优雅古典的30亳米表壳，表盘采用\n柔光四溢的珍珠贝母材质\n将现代女性干练的形象柔和化，尽显\n精致女人味', '天梭', 2550, 2750, 6, 3, 0, 0, 1, '2020-12-31 10:48:38', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/be42a000d1734dbba1f7e6953dfbd48c-t0942101112600_1.png');
INSERT INTO `product` VALUES (16, '2020123110515412', 'RedmiBook Air 13', '电脑', 'Redmi Book Air 13\n轻盈便携轻松办公', '小米', 4299, 4499, 20, 10, 0, 0, 1, '2020-12-31 10:53:32', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/c6b1f6bd8744454a9b52ad31d52b153d-pms_1597115272.25169010.jpg');
INSERT INTO `product` VALUES (17, '2020123110533213', 'RedmiBook 14 Ⅱ', '电脑', 'Redmi Book 14 LI\n全面实力，全「芯」超越\n全新十代酷睿处理器 / MX350独显 / 14英寸超窄边框高清屏', '小米', 4200, 4499, 30, 12, 0, 0, 1, '2020-12-31 10:55:26', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/f66356c9fc934d86a351892c0fe707c1-pms_1594121477.94286021.jpg');
INSERT INTO `product` VALUES (18, '2020123110552614', 'RedmiBook 16', '电脑', '全新十代酷睿处理器 / MX350独显 / 16英寸超窄边框高清屏 \n16.1\"超大高清屏幕\n更大视野，更多可见', '小米', 4399, 4699, 18, 8, 0, 0, 1, '2020-12-31 10:57:21', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/4cb41c8ec4eb4bc3bfbfeb637f86b8f2-pms_1594113149.87593534.jpg');
INSERT INTO `product` VALUES (19, '2020123110572215', 'RedmiBook 14 增强版', '电脑', '全新十代酷睿处理器 / MX250独显 / 14英寸超窄边框高清屏 / 小米手环极速解锁 / 炫酷多彩任你挑选', '小米', 3688, 3999, 15, 5, 0, 0, 1, '2020-12-31 10:59:16', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a9c66147b1d64a99a6e5f6286024586d-pms_1566878924.45456023.jpg');
INSERT INTO `product` VALUES (20, '2020123110591616', 'HUAWEI MateBook D 14', '电脑', 'HUAWEI MateBook D 14 锐龙版 全新7nm R5 16GB+512GB（皓月银）14英寸莱茵护眼 多屏协同 轻薄金属机身办公学习笔记本', '华为', 3999, 4099, 15, 5, 0, 0, 1, '2020-12-31 11:00:43', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/294be499ee5848e5870ac2eb5b509beb-428_428_4623B15D5E474FE95C7867EE67C1D25A13BCA65E0412FB5Fmp.png');
INSERT INTO `product` VALUES (21, '2020123111004317', 'HUAWEI MateBook X 2020款', '电脑', 'HUAWEI MateBook X 2020款 集显 i5 16G 512G（冰霜银）13英寸3K触控全面屏 轻至1kg超轻薄华为笔记本电脑', '华为', 8200, 8999, 15, 5, 0, 0, 1, '2020-12-31 11:01:44', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/8588229f00484384b0022c3346518ee8-428_428_8C0DCB8B48F9A0DDDF1C3A8BC7958FBA2AE24D308646AAA2mp.png');
INSERT INTO `product` VALUES (22, '2020123111014518', 'HUAWEI MateBook D 15 2021款', '电脑', 'HUAWEI MateBook D 15 2021款 15英寸护眼全面屏笔记本电脑 全新11代酷睿i5 16GB 512GB 锐炬显卡 多屏协同 WiFi 6 皓月银', '华为', 4200, 4999, 15, 10, 0, 0, 1, '2020-12-31 11:03:06', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/4d84bcd52be948428a1d1267a0f2d57b-428_428_0CA3A957E88044DD39123396C850743AED2D2EAA2DBFBD61mp.png');
INSERT INTO `product` VALUES (23, '2020123111030619', 'HUAWEI MateBook 14 2020款', '电脑', 'HUAWEI MateBook 14 2020款 独显MX350 i5 16GB 512GB（深空灰）14英寸华为笔记本办公电脑 2K触控屏轻薄本 多屏协同便捷互传学习笔记本', '华为', 6299, 6399, 15, 5, 0, 0, 1, '2020-12-31 11:04:32', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/cbd1da41a39b4dc48e1db1545a2becb9-428_428_CB59BC49A4E1F60D2A1F4C6C349E591D1B3F546807A34180mp.png');
INSERT INTO `product` VALUES (24, '2020123111060320', 'HUAWEI Mate 30 Pro 5G', '手机', 'HUAWEI Mate 30 Pro 5G 全网通 8GB+512GB 麒麟990 双4000万徕卡电影四摄', '华为', 7199, 7399, 20, 12, 0, 0, 1, '2020-12-31 11:07:19', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/0556c05575ef4643b2e67ced5fbaf4fb-428_428_BBB155FB6C09744712785FB380EA616E967F4F8F8CAEC76Amp.png');
INSERT INTO `product` VALUES (25, '2020123111072021', 'HUAWEI Mate 40 5G', '手机', '5nm麒麟9000E旗舰芯片，超感知徕卡影像\n有线无线双超级快充1,EMUI11创新交互体验\n源于宇宙探索的星环美学，6.5英寸曲面屏2舒适握持感\nHUAWEI Mate40,智慧表现恰到好处', '华为', 4299, 4999, 20, 10, 0, 0, 1, '2020-12-31 11:09:58', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/088caefccff341488a24cdb83bedec61-428_428_9B6917F54FD19C85873D683A0118C9EE61CF70B260CE761Bmp.png');
INSERT INTO `product` VALUES (26, '2020123111095922', 'HUAWEI nova 7 Pro 5G', '手机', '轻盈灵动，一触倾心\n纤薄美学设计\n前后双曲面玻璃设计，nova7Pro机身最薄处7.98mm1,重量178g\n纤薄轻盈，握持感圆润舒适。\n如晶莹水晶，光影交织；如月影流沙，梦幻灵动。', '华为', 3900, 4099, 19, 10, 0, 0, 1, '2020-12-31 11:11:36', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/998571fdc30d40fda6bd77eee4723848-428_428_AE7282C89472CC29A19250DF5B6E4F08553B5DCC8506214Fmp.png');
INSERT INTO `product` VALUES (27, '2020123111113623', '华为畅享 10S', '手机', '6.3英寸炫彩OLED全面屏|4800万超广角AI三摄|手持超级夜景', '华为', 1299, 1699, 15, 8, 0, 0, 1, '2020-12-31 11:12:56', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/c15752d30e4540518f3019b7a4bed396-428_428_9FA695518CFC23552650A7145BD7A40DC1A9F088C4BA6A07mp.png');
INSERT INTO `product` VALUES (28, '2020123111125624', '华为畅享Z 5G', '手机', '90Hz畅滑屏，流畅跟手\n90Hz刷新率配合180Hz高触控采样率，带来全新\n顺滑跟手的游戏体验。轻轻滑动屏幕，灵敏“跟手”\n如丝般顺滑。', '华为', 1999, 2199, 15, 7, 0, 0, 1, '2020-12-31 11:14:02', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/12b7c4f12e33406aa2a47c7d26fb124d-428_428_A46A0A86BE45135BA47D1C6DBDB8BD2128A658F61202CDD0mp.png');
INSERT INTO `product` VALUES (29, '2020123111155625', '索尼(SONY)KD-55X9088H', '生活家电', '索尼(SONY)KD-55X9088H 55英寸 4K HDR 安卓智能液晶电视\n专业游戏模式，次世代游戏兼容性，HDMI2.1支持4K120帧模式，全阵列式背光', '索尼', 4000, 4299, 30, 13, 0, 0, 1, '2020-12-31 11:16:53', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/2bf7ead6823842abbf9f99427c80343d-A00NwZX6laRKLsiP333hBQ.jpg');
INSERT INTO `product` VALUES (30, '2020123111165326', '全面屏电视 55英寸E55X', '生活家电', '潮流全面屏设计 视野更广阔 / 4K超高清画质 细腻如真 / 杜比音效带来震撼体验 / 内置小爱同学 语音操控更便捷 / 智能PatchWall系统 / 2G+8G大存储空间', '小米', 2199, 2399, 25, 12, 0, 0, 1, '2020-12-31 11:18:38', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/b6f8b98c9e19421ebbb353a9b4974606-pms_1570600947.92372088.jpg');
INSERT INTO `product` VALUES (31, '2020123111183927', '小米电视4A 70英寸', '生活家电', '70英寸震撼巨屏 / 4K画质 细腻如真 / 杜比音效 身临其境 / PatchWall智能系统 内置小爱同学 / 海量好内容', '小米', 3199, 3129, 18, 12, 0, 0, 1, '2020-12-31 11:19:50', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/2c675677225c4df693305229e2f9ad3b-pms_1568199413.36224361.jpg');
INSERT INTO `product` VALUES (32, '2020123111195028', '小米全面屏电视65英寸 E65C', '生活家电', '震撼大屏 观影更爽 / 时尚全面屏设计 视野更广阔 / 4K超高清画质 细腻如真 / 杜比音效带来震撼体验 / 内置小爱同学 语音操控更便捷 / 智能PatchWall系统 / 2G+8G大存储空间', '小米', 2899, 2999, 18, 13, 0, 0, 1, '2020-12-31 11:20:40', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/d36116d2edf84334abe6b06a17afe423-pms_1569691861.38973660.jpg');
INSERT INTO `product` VALUES (33, '2020123111204029', '小米电视4C 32英寸', '生活家电', '人工智能系统 | 64位处理器 | 1GB+4GB 内存', '小米', 840, 989, 20, 10, 0, 0, 1, '2020-12-31 11:22:00', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/f233d587eb19490c97c3d9ad60a25aa4-pms_1527060327.66235934.jpg');
INSERT INTO `product` VALUES (34, '2020123111220030', '索尼(SONY)KD-55A8H', '生活家电', '【索尼 55英寸 OLED安卓智能 X1™旗舰版 智能电视】图像处理芯片 X1™旗舰版 智能电视，搭载安卓9.0系统', '索尼', 9999, 10999, 20, 12, 0, 0, 1, '2020-12-31 11:27:41', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/e898eaa5f49549cd8ef6cc2af54c9835-ZYkZ0LauFrlM8RK-mF-UCw.jpg');
INSERT INTO `product` VALUES (35, '2020123111305231', '华为智慧屏 V65', '生活家电', '华为智慧屏 V65（星际黑）65英寸 4K量子点 4+64GB 升降式AI摄像头 视频通话 智慧音响 鸿鹄芯片 智能家居控制', '华为', 7299, 7499, 20, 10, 0, 0, 1, '2020-12-31 11:31:57', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a0ddfebf8d0546439bec6e2334685961-428_428_21EEE8FC1E9B970CAA527CC00F668B933528C62FBC4CD1E4mp.png');
INSERT INTO `product` VALUES (36, '2020123113335132', '天梭波尔图系列皮带机械表刘亦菲同款', '腕表', '波尔图系列\n承载悠久制表历史和精湛制表技艺\n致敬经典，彰显贵族风范', '天梭', 9100, 9300, 10, 5, 0, 0, 1, '2020-12-31 13:35:24', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/b997d1560cd44752819b3f9c3dba6b9f-t1285053601200_1.png');
INSERT INTO `product` VALUES (38, '2020123113361433', '天梭俊雅系列钢带石英男表', '腕表', '曲线优美怀旧气息\n银色316L精钢表带为整体造型\n增加金属质感，提升佩戴者硬朗风度\n按钮式蝴蝶扣设计为整体注入怀旧感', '天梭', 2550, 2650, 20, 10, 0, 0, 1, '2020-12-31 13:38:27', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/862ea3223d124a7a96222fe15a6739d9-t0636101103700_1_1.png');
INSERT INTO `product` VALUES (39, '2020123113384134', '小米智能养生壶', '生活家电', '小米智能养生壶 烧水壶 电热水壶 煮茶器大加热功率 多档火力调节 安全防干烧 米家APP互联 MYSH0E1ACM', '小米', 150, 169, 30, 10, 0, 0, 1, '2020-12-31 13:53:27', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a5455965e19840f6a07147ac50a3b352-865469eafd7a3f99.jpg');
INSERT INTO `product` VALUES (40, '2020123113542135', '变频家用微波炉光波烧烤炉', '生活家电', '美的（Midea）X3-233A 变频家用微波炉光波烧烤炉 微波炉电烤箱一体机 智能湿度感应 900W微烤一体23升', '美的', 610, 639, 50, 20, 0, 0, 1, '2020-12-31 13:56:04', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/ea09ff37d4ed48cfa2b6764321e032c8-42933c26fee08c6c.jpg');
INSERT INTO `product` VALUES (41, '2020123113560536', '小米电吹风机家用吹风筒', '生活家电', '米家 负离子吹风机，5000万负离子，20m/s超大风速，大功率速干不伤发；57℃智能恒温，冷热风循环，三档温度', '小米', 99, 149, 50, 20, 0, 0, 1, '2020-12-31 13:57:48', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/7478723eb7e048a6aed637be3bc0354f-558626d6a5daf1c2.jpg');
INSERT INTO `product` VALUES (42, '2020123114001537', '联想(Lenovo) S550 14英寸 轻薄笔记本', '电脑', '7nm！新锐龙\n强劲实力，表现非凡。\n搭载AMD锐龙54600U移动处理器e,6核心12线程，睿频加速\n高达4.0GHz，处理器采用全新7nm技术工艺，让多任务处理\n更加迅捷稳定，操作使用流畅省心。', '联想', 2799, 3999, 30, 15, 0, 0, 1, '2020-12-31 14:01:57', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/4e1bc88d77254cfeaf7ca7cfaec61792-b46ee420bfce6581.jpg');
INSERT INTO `product` VALUES (43, '2020123114015738', '联想(Lenovo)小新Pro13', '电脑', '联想(Lenovo)小新Pro13 2020商务长续航版全面屏轻薄办公笔记本电脑(i5 16G 512G 2.5K屏 高色域 人脸识别)', '联想', 5399, 5699, 30, 10, 0, 0, 1, '2020-12-31 14:03:38', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a53be12e3cf146a6b7ad1e1f1b9ab398-0eac600febe4cf29.jpg');
INSERT INTO `product` VALUES (44, '2020123114033939', '索尼(SONY) KD-75Z8H', '生活家电', '索尼(SONY) KD-75Z8H 75英寸 8K HDR 安卓智能液晶电视\n8K迅锐图像处理引擎Pro / 图像处理芯片 X1™旗舰版 / 8K精锐光控Pro旗舰版', '索尼', 32999, 34999, 10, 4, 0, 0, 1, '2020-12-31 14:08:40', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/94f88cb0898c4fd3b85840436f6dc843-N6j4eV8FofJGkcVKGG7Jqw.jpg');
INSERT INTO `product` VALUES (45, '2020123114084040', 'MacBook Air', '电脑', 'Apple M1 芯片，配备 8 核中央处理器和 7 核图形处理器\n256GB 存储容量', '苹果', 7699, 7999, 10, 5, 0, 0, 1, '2020-12-31 14:12:53', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/f88b2a5f125a4da6874335b6de990751-macbook-air-space-gray-select-201810.jpg');
INSERT INTO `product` VALUES (46, '2020123114125441', 'iPhone 12 Pro', '手机', '5G 加身；A14 仿生速度超快，实力超前；Pro 级摄像头系统将低光摄影带上新高，在 iPhone 12 Pro Max 上更是突飞猛进；而超瓷晶面板，则将抗跌落能力提高到四倍。你所期待的，来了。', '苹果', 9199, 9299, 8, 3, 0, 0, 1, '2020-12-31 14:17:22', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a600a2291e7f4158bb18fa6d2184df43-iphone-12-pro-graphite-hero.png');
INSERT INTO `product` VALUES (47, '2020123114172342', 'iPhone SE', '手机', 'iPhone SE 拥有强大的芯片和惹人喜爱的尺寸，而价格更是让人动心。你期待的它，来了。', '苹果', 3699, 3799, 10, 5, 0, 0, 1, '2020-12-31 14:19:17', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/b96846d1d2164c9d804eaf6551270620-iphone-se-white-select-2020.png');
INSERT INTO `product` VALUES (48, '2020123114430143', '茵曼秋季新款棉针织上衣女', '服装', '茵曼秋季新款清新纯色优雅V领绣花简约百搭弹性棉针织上衣女', '茵曼', 452, 489, 30, 15, 0, 0, 1, '2020-12-31 14:46:16', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/349413ae39e7479b85d9465c689bbb29-160422964083238598.jpg');
INSERT INTO `product` VALUES (49, '2020123114461744', '2019秋季新款学生圆领卫衣', '服装', '苏宁款卫衣女宽松韩版2019秋季新款时尚学生圆领字母印花纯棉慵懒风加绒', '茵曼', 270, 299, 28, 10, 0, 0, 1, '2020-12-31 14:48:18', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/b460089cd1bb4d7f8fb0bd68ac39b391-160422956116154612.jpg');
INSERT INTO `product` VALUES (50, '2020123114525945', 'Lagogo宽松连衣裙女', '服装', 'Lagogo拉谷谷长袖高腰条纹中裙宽松连衣裙女', '茵曼', 639, 659, 29, 10, 0, 0, 1, '2020-12-31 14:53:47', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/7c156050769a4e36b71a70a7cbd01a66-160138138140657460.png');
INSERT INTO `product` VALUES (51, '2020123114535846', '菱格套头毛衣秋冬季新款女', '服装', '妖精的口袋waitmore菱格套头毛衣秋冬季新款女宽松外穿针织衫', '茵曼', 60, 75, 25, 10, 0, 0, 1, '2020-12-31 14:54:52', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/454eee8faa934da8a2d8a288143c057b-160138145934440586.png');
INSERT INTO `product` VALUES (52, '2020123116310447', 'Redmi 智能电视 MAX 98', '生活家电', '客厅里的巨幕影院 大视野带来超震撼的沉浸感/4K HDR超高清画质 逼真画面栩栩如生/杜比+DTS双解码 清澈立体的声音环绕整个客厅/MEMC运动补偿 无拖尾无重影/4G+64G大存储', '小米', 18999, 19999, 29, 20, 0, 0, 1, '2020-12-31 16:31:55', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/32ac248179554081be1f18693ab51748-pms_1584958755.36428250.jpg');
INSERT INTO `product` VALUES (53, '2020123120311148', '小米电视大师 82英寸至尊纪念版', '生活家电', '8K超高清/量子点技术/声动之翼/支持5G网络/全通道8K', '小米', 47999, 49999, 10, 5, 0, 0, 1, '2020-12-31 20:32:06', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/ad3b1bfe33ed4239b91b3c3e3407260b-597dc0f15e44d85928711966d540ed71.jpg');
INSERT INTO `product` VALUES (54, '2020123120363749', '米家智能多功能养生壶', '生活家电', '1000W加热功率 / 10挡火力调节 / App联网控制 / 智能在线食谱', '小米', 149, 169, 20, 10, 0, 0, 1, '2020-12-31 20:37:23', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/ba04be197366418199eb58bae64d7c53-pms_1605249917.43613694.jpg');
INSERT INTO `product` VALUES (55, '2020123120400750', 'Redmi K30 5G', '手机', '双模5G / 三路并发 / 高通骁龙765G / 7nm 5G低功耗处理器 / 120Hz高帧率流速屏 / 索尼6400万前后六摄 / 4500mAh+30W快充', '小米', 1299, 1599, 20, 12, 0, 0, 1, '2020-12-31 20:41:28', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/1f7f817eda3e4bd2937cf1d2b6cba115-pms_1575882053.33827103.jpg');
INSERT INTO `product` VALUES (56, '2020123120432951', '小米10', '手机', '骁龙865处理器 /90Hz刷新率+180Hz采样率 / UFS 3.0高速存储 / 30W极速闪充+30W无线闪充+10W无线反充 / 4780mAh大电量 / 多功能NFC', '小米', 3600, 3799, 17, 10, 0, 0, 1, '2020-12-31 20:44:32', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/435e6091eeed4018860bf5855aa2ad47-pms_1581493999.49592807.jpg');
INSERT INTO `product` VALUES (57, '2020123120432954', '韩版冬装新款外套', '服装', '美特斯邦威毛呢大衣男长款文艺帅气韩版冬装新款外套商场款', '茵曼', 620, 659, 999, 10, 0, 0, 1, '2021-01-01 11:41:34', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/1acfe0ec0cb24a2da6e9c5271d8e51a1-ew5MbyeEkWD_huloKblnXQ.jpg');
INSERT INTO `product` VALUES (58, '20211316161453', 'Redmi全自动波轮洗衣机1A 8kg', '生活家电', '8kg大容量 / 10种洗涤模式 / 10挡水位调节 / 耐腐蚀金属机身 / 桶自洁、桶风干模式避免细菌滋生 / 空气阻尼减震 / 免运费及基础安装费', '小米', 799, 899, 10, 5, 0, 0, 1, '2021-01-03 16:18:13', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/acb44c71af8e4aca9128d6d66dd111a8-b8c63a2024528fe5410ebe669b7d2407.webp');
INSERT INTO `product` VALUES (59, '20211516332754', '2020年尖领净色上装衬衣男装寸衫', '服装', '七匹狼长袖衬衫男士秋季2020年尖领净色上装衬衣男装寸衫', '七匹狼', 580, 647, 20, 8, 0, 0, 1, '2021-01-05 16:34:45', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/92b29087b60f4fd791097d518e63ec0e-048sxka-tkdqUSjMTGzzaw.jpg');
INSERT INTO `product` VALUES (60, '20211516344655', '冬装新款甜美浪漫蝴蝶结系连衣裙', '服装', '茵曼2020冬装新款甜美浪漫蝴蝶结系带修身橡筋泡泡袖连衣裙两件套', '茵曼', 600, 639, 19, 10, 0, 0, 1, '2021-01-05 16:36:55', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/a80dbc6f490843eaa1e4aa301f2c3c80-Zn4EiMhTNR2EHS_JxzbMyg.jpg');
INSERT INTO `product` VALUES (61, '20211516383756', '方形口袋长袖短外套上衣', '服装', '茵曼2020冬装新款温暖文艺格子拼接绒毛领方形口袋长袖短外套上衣', '茵曼', 599, 639, 17, 10, 0, 0, 1, '2021-01-05 16:40:26', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/8d5eb4c94ced46c3b9eaa46272573725-RuhTZO5ho3S7hAk74kc2oQ.jpg');
INSERT INTO `product` VALUES (62, '20211517010257', '海澜之家水洗牛津纺长袖休闲衬衫2020春季挺括有型长衬男', '服装', '海澜之家水洗牛津纺长袖休闲衬衫2020春季挺括有型长衬男\n牛津纺面料 水洗工艺 扣领尖领 有型穿着', '海澜之家', 128, 158, 26, 15, 0, 0, 1, '2021-01-05 17:02:50', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/5dba4a48d30d479696574fe76e9d2e99-FIl9rLaE1Of1YrMr_XUMRw.png');
INSERT INTO `product` VALUES (63, '20211517042458', '拉谷谷多色休闲连帽卫衣女上衣ICEE417G38', '服装', '简约多色百搭连帽卫衣\n每个季节对卫衣都情有独钟\n时尚达人必备的时髦单品\n具有超强时尚感和视觉上瘦身的效果。', '茵曼', 119, 139, 28, 20, 0, 0, 1, '2021-01-05 17:10:25', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/15dbe2cfaf0c4ee1ae0cbfb3f8171370-Q3jd-iexNlA8YnUFM51d9A.jpg');
INSERT INTO `product` VALUES (64, '20223110185959', '测试', '手机', '11', '七匹狼', 200, 400, 50, 50, 0, 1, 1, '2022-03-01 10:19:46', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productImage/25780f46047b4ccb8ed8ddaaff4f0f10-avatar.gif');

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand`  (
  `brand_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `brand_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名称',
  `brand_describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌描述',
  PRIMARY KEY (`brand_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`brand_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品品牌表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_brand
-- ----------------------------
INSERT INTO `product_brand` VALUES (1, '小米', '2021轻装上阵');
INSERT INTO `product_brand` VALUES (2, '苹果', 'touching is believing. （触摸到的就是真实的.）');
INSERT INTO `product_brand` VALUES (3, '华为', '专注于ICT领域，坚持稳健经营、持续创新、开放合作');
INSERT INTO `product_brand` VALUES (4, '联想', 'Lenovo：For those who do');
INSERT INTO `product_brand` VALUES (5, '华硕', '华而不奢,硕而不庸');
INSERT INTO `product_brand` VALUES (6, '索尼', 'SONY VAIO 创所未想');
INSERT INTO `product_brand` VALUES (7, '天梭', '简约时尚、掌控随心');
INSERT INTO `product_brand` VALUES (8, '美的', '原来生活可以更美的');
INSERT INTO `product_brand` VALUES (9, '茵曼', 'inman(茵曼)');
INSERT INTO `product_brand` VALUES (10, '花卷', '不断超越,追求完美');
INSERT INTO `product_brand` VALUES (11, '七匹狼', '分享智慧,品味成就');
INSERT INTO `product_brand` VALUES (12, '海澜之家', '海澜之家，男人的衣柜');

-- ----------------------------
-- Table structure for product_review
-- ----------------------------
DROP TABLE IF EXISTS `product_review`;
CREATE TABLE `product_review`  (
  `review_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '用户评论id',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_specs` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `star_level` double NOT NULL COMMENT '商品评星',
  `product_review` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品评价',
  PRIMARY KEY (`review_id`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE,
  INDEX `product_no`(`product_no`) USING BTREE,
  INDEX `order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评价' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_review
-- ----------------------------
INSERT INTO `product_review` VALUES (1, 'qiudb.top@aliyun.com', '202012302222140', '8GB+256GB', '211322315037', '2021-01-04 11:26:36', 5, '洗衣机收到了，使用后效果很不错，值得购买');
INSERT INTO `product_review` VALUES (2, 'qiudb.top@aliyun.com', '202012302222140', '8GB+256GB', '211322315037', '2021-01-04 11:27:24', 5, '洗衣机收到了，使用后效果很不错，值得购买');
INSERT INTO `product_review` VALUES (3, 'qiudb.top@aliyun.com', '2020123120311148', NULL, '211322312375', '2021-01-04 13:58:15', 4, '小米电视很清晰，值得购买');
INSERT INTO `product_review` VALUES (4, 'qiudb.top@aliyun.com', '2020123111060320', '8GB+256GB', '211322311410', '2021-01-04 16:55:50', 5, '商品很不错，物流速度也很快');
INSERT INTO `product_review` VALUES (5, 'qiudb.top@aliyun.com', '2020123114125441', '8GB+256GB', '211322314850', '2021-01-04 17:01:30', 5, '手机像素很高，值得购买');
INSERT INTO `product_review` VALUES (6, 'qiudb.top@aliyun.com', '2020123120311148', NULL, '211322312375', '2021-01-04 22:08:25', 5, '家里第一台是98寸的小米做影院，这一次购物。让我彻底感觉到小米的品质和实在。所以在众多8k电视中，我再次选择了小米。收到货后。除了极致的牛皮外。声音和画质是我目前见过最清晰，声音最真实的电视。');
INSERT INTO `product_review` VALUES (7, 'qiudb.top@aliyun.com', '2020123114525945', 'L', '211322315037', '2021-01-04 22:08:47', 4, '衣服很漂亮，也很好看哦');
INSERT INTO `product_review` VALUES (8, 'qiudb.top@aliyun.com', '2020123120400750', '8GB+128GB', '211511323447', '2021-01-05 17:27:37', 5, '物流速度很快，手机质量感觉也不错');
INSERT INTO `product_review` VALUES (9, 'qiudb.top@aliyun.com', '20211516344655', 'S', '211517184317', '2021-01-05 17:56:14', 5, '喜欢，款式很不错');
INSERT INTO `product_review` VALUES (10, 'qiudb.top@aliyun.com', '2020123110591616', ' i5-10200H/16G/512G/60Hz', '211518588512', '2021-01-05 18:12:24', 5, '帮同事买的，他说质量很不错，支持华为！！！');
INSERT INTO `product_review` VALUES (11, '2478448784@qq.com', '202012311041107', NULL, '211521424925', '2021-01-05 21:31:27', 4, '不错');
INSERT INTO `product_review` VALUES (12, '2478448784@qq.com', '2020123111095922', '6GB+128GB', '211521426089', '2021-01-05 21:31:36', 3, '商品不错，值得购买');
INSERT INTO `product_review` VALUES (13, 'qiudb.top@aliyun.com', '202012311037275', NULL, '211518238993', '2021-01-06 11:29:17', 5, '商品不错，值得购买');
INSERT INTO `product_review` VALUES (14, 'qiudb.top@aliyun.com', '202012302222140', NULL, '211322315037', '2021-01-06 14:04:37', 5, '洗衣机收到了，使用后效果很不错，值得购买');
INSERT INTO `product_review` VALUES (15, '1147066163@qq.com', '2020123116310447', NULL, '2141417431071', '2021-04-14 17:22:57', 5, '清晰度很高，值得购买');
INSERT INTO `product_review` VALUES (16, 'qiudb.top@aliyun.com', '20211517042458', 'M', '2162123561642', '2021-06-21 23:19:01', 5, '很好');
INSERT INTO `product_review` VALUES (17, 'qiudb.top@aliyun.com', '2020123114125441', '6GB+256GB', '2222616509327', '2022-02-26 16:50:13', 5, '很好用');
INSERT INTO `product_review` VALUES (18, '15233088662@163.com', '20211516383756', 'S', '223110263492', '2022-03-01 10:04:26', 4, '衣服还行');
INSERT INTO `product_review` VALUES (19, '15233088662@163.com', '202012310940061', '6GB+128GB', '223110455311', '2022-03-04 21:07:06', 3, '123');

-- ----------------------------
-- Table structure for product_specs
-- ----------------------------
DROP TABLE IF EXISTS `product_specs`;
CREATE TABLE `product_specs`  (
  `product_id` int(5) NOT NULL COMMENT '商品id',
  `specs_id` int(5) NOT NULL COMMENT '规格id',
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `specs_id`(`specs_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_specs
-- ----------------------------
INSERT INTO `product_specs` VALUES (1, 1);
INSERT INTO `product_specs` VALUES (1, 2);
INSERT INTO `product_specs` VALUES (1, 3);
INSERT INTO `product_specs` VALUES (2, 1);
INSERT INTO `product_specs` VALUES (2, 2);
INSERT INTO `product_specs` VALUES (6, 1);
INSERT INTO `product_specs` VALUES (6, 2);
INSERT INTO `product_specs` VALUES (16, 18);
INSERT INTO `product_specs` VALUES (16, 17);
INSERT INTO `product_specs` VALUES (16, 16);
INSERT INTO `product_specs` VALUES (17, 13);
INSERT INTO `product_specs` VALUES (17, 14);
INSERT INTO `product_specs` VALUES (17, 15);
INSERT INTO `product_specs` VALUES (18, 17);
INSERT INTO `product_specs` VALUES (18, 16);
INSERT INTO `product_specs` VALUES (19, 16);
INSERT INTO `product_specs` VALUES (19, 17);
INSERT INTO `product_specs` VALUES (19, 18);
INSERT INTO `product_specs` VALUES (20, 15);
INSERT INTO `product_specs` VALUES (20, 14);
INSERT INTO `product_specs` VALUES (20, 13);
INSERT INTO `product_specs` VALUES (21, 14);
INSERT INTO `product_specs` VALUES (21, 15);
INSERT INTO `product_specs` VALUES (22, 14);
INSERT INTO `product_specs` VALUES (22, 15);
INSERT INTO `product_specs` VALUES (23, 16);
INSERT INTO `product_specs` VALUES (23, 17);
INSERT INTO `product_specs` VALUES (24, 1);
INSERT INTO `product_specs` VALUES (24, 2);
INSERT INTO `product_specs` VALUES (25, 1);
INSERT INTO `product_specs` VALUES (25, 2);
INSERT INTO `product_specs` VALUES (26, 1);
INSERT INTO `product_specs` VALUES (26, 2);
INSERT INTO `product_specs` VALUES (27, 4);
INSERT INTO `product_specs` VALUES (27, 5);
INSERT INTO `product_specs` VALUES (27, 1);
INSERT INTO `product_specs` VALUES (28, 4);
INSERT INTO `product_specs` VALUES (28, 5);
INSERT INTO `product_specs` VALUES (28, 2);
INSERT INTO `product_specs` VALUES (28, 1);
INSERT INTO `product_specs` VALUES (42, 15);
INSERT INTO `product_specs` VALUES (42, 14);
INSERT INTO `product_specs` VALUES (43, 17);
INSERT INTO `product_specs` VALUES (43, 16);
INSERT INTO `product_specs` VALUES (46, 2);
INSERT INTO `product_specs` VALUES (47, 1);
INSERT INTO `product_specs` VALUES (48, 8);
INSERT INTO `product_specs` VALUES (48, 9);
INSERT INTO `product_specs` VALUES (48, 10);
INSERT INTO `product_specs` VALUES (49, 8);
INSERT INTO `product_specs` VALUES (49, 9);
INSERT INTO `product_specs` VALUES (49, 10);
INSERT INTO `product_specs` VALUES (50, 8);
INSERT INTO `product_specs` VALUES (50, 9);
INSERT INTO `product_specs` VALUES (50, 10);
INSERT INTO `product_specs` VALUES (51, 8);
INSERT INTO `product_specs` VALUES (51, 9);
INSERT INTO `product_specs` VALUES (51, 19);
INSERT INTO `product_specs` VALUES (51, 10);
INSERT INTO `product_specs` VALUES (55, 1);
INSERT INTO `product_specs` VALUES (55, 2);
INSERT INTO `product_specs` VALUES (55, 4);
INSERT INTO `product_specs` VALUES (56, 1);
INSERT INTO `product_specs` VALUES (56, 2);
INSERT INTO `product_specs` VALUES (56, 4);
INSERT INTO `product_specs` VALUES (59, 8);
INSERT INTO `product_specs` VALUES (59, 9);
INSERT INTO `product_specs` VALUES (59, 11);
INSERT INTO `product_specs` VALUES (59, 10);
INSERT INTO `product_specs` VALUES (59, 12);
INSERT INTO `product_specs` VALUES (60, 9);
INSERT INTO `product_specs` VALUES (60, 7);
INSERT INTO `product_specs` VALUES (60, 8);
INSERT INTO `product_specs` VALUES (60, 10);
INSERT INTO `product_specs` VALUES (60, 11);
INSERT INTO `product_specs` VALUES (61, 7);
INSERT INTO `product_specs` VALUES (61, 8);
INSERT INTO `product_specs` VALUES (61, 9);
INSERT INTO `product_specs` VALUES (62, 8);
INSERT INTO `product_specs` VALUES (62, 9);
INSERT INTO `product_specs` VALUES (62, 10);
INSERT INTO `product_specs` VALUES (62, 11);
INSERT INTO `product_specs` VALUES (63, 8);
INSERT INTO `product_specs` VALUES (63, 9);
INSERT INTO `product_specs` VALUES (63, 10);
INSERT INTO `product_specs` VALUES (64, 1);

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type`  (
  `type_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `type_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  `type_describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别描述',
  `type_url_left` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '左侧宣传图',
  `type_url_top` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '横幅宣传图',
  PRIMARY KEY (`type_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES (1, '手机1', '手机潮牌', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/c52d58ddf933460f9de0723b07df6c1b-小米.webp', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/84e07259f5cc4fb1919d0e05b4097540-小米手机.webp');
INSERT INTO `product_type` VALUES (2, '服装', '潮流大牌，潮流商品', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/5dff448559a44ee58179add6cb0196f8-155418958127287351.png', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/53963d95d1f7450e902455a4fe0c523c-9880db34d227b6c1df5b45cb7df4f465.webp');
INSERT INTO `product_type` VALUES (3, '生活家电', '智能生活', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/87e1decb07d44b2f859f939605a3568d-155532686442344865.jpg', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/df11764292b34f5f94c734a5a5095096-1615d1842fe85914a545297836a44271.webp');
INSERT INTO `product_type` VALUES (4, '电脑', '超多笔记本、台式机、支持DIY设计', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/e85be40c9d4c462eb0836b3c09fd43ce-155642144783774577.jpg', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/486d0b4fe2bb4129b853a8b9154db470-160880090211074442.jpg');
INSERT INTO `product_type` VALUES (5, '腕表', '光能表、机械表、石英表、智能表', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/7d9337ad8c04476a93617627f923a709-159523354099274893.jpg', 'https://spring-oss.oss-cn-beijing.aliyuncs.com/images/productTypeImage/cbe28413d2fd4992a08417351547b60d-160920756663231949.jpg');

-- ----------------------------
-- Table structure for products_featured
-- ----------------------------
DROP TABLE IF EXISTS `products_featured`;
CREATE TABLE `products_featured`  (
  `record_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `user_id` int(5) NOT NULL COMMENT '用户id',
  `product_id` int(5) NOT NULL COMMENT '商品id',
  `recommend` int(10) NOT NULL COMMENT '推荐分',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of products_featured
-- ----------------------------
INSERT INTO `products_featured` VALUES (13, 2, 26, 2);

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `purchase_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '采购id',
  `purchase_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '采购编号',
  `purchase_number` int(6) NOT NULL COMMENT '进货数量',
  `purchase_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '进货时间',
  `receipt_time` timestamp NULL DEFAULT NULL COMMENT '收货时间',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `supplier_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商编号',
  `supplier_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商名称',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员编号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员名称',
  `receipt_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '收货状态',
  PRIMARY KEY (`purchase_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`purchase_no`) USING BTREE,
  INDEX `product_no`(`product_no`) USING BTREE,
  INDEX `supplier_no`(`supplier_no`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (1, 'P20201230024346', 1, '2020-12-30 22:30:02', '2020-12-30 22:30:07', '202012302222140', '小米11', '20201230222915715', '天猫', '1325554003@qq.com', '马英发', 1);
INSERT INTO `purchase` VALUES (2, 'P202115492225', 1, '2021-01-05 17:25:48', '2021-01-05 17:26:03', '202012310948192', 'Redmi 10X Pro 5G', '20201230222915715', '天猫', '1325554003@qq.com', '马英发', 1);
INSERT INTO `purchase` VALUES (3, 'P202115542699', 2, '2021-01-05 17:25:53', '2021-01-05 17:26:05', '202012310948192', 'Redmi 10X Pro 5G', '20201230222915715', '天猫', '1325554003@qq.com', '马英发', 1);
INSERT INTO `purchase` VALUES (4, 'P202231305304', 3, '2022-03-01 10:17:31', '2022-03-01 10:17:35', '202012310940061', 'Redmi K30 至尊纪念版', '20201230222915715', '天猫', '1325554003@qq.com', '超级管理员', 1);

-- ----------------------------
-- Table structure for return_goods
-- ----------------------------
DROP TABLE IF EXISTS `return_goods`;
CREATE TABLE `return_goods`  (
  `return_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '退货id',
  `apply_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `user_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `return_price` double NOT NULL COMMENT '退款金额',
  `operator_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员账号',
  `operator_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员名称',
  `deal_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `return_reason` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退货原因',
  `return_state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退货状态',
  PRIMARY KEY (`return_id`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE,
  INDEX `return_goods_ibfk_3`(`operator_number`) USING BTREE,
  INDEX `return_goods_ibfk_1`(`order_no`) USING BTREE,
  INDEX `return_goods_ibfk_2`(`user_number`) USING BTREE,
  INDEX `return_goods_ibfk_4`(`return_reason`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品退货表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of return_goods
-- ----------------------------
INSERT INTO `return_goods` VALUES (9, '2021-01-01 14:12:06', '21111447921', 'qiudb.top@aliyun.com', 'M', 8369, '1325554003@qq.com', '马英发', '2021-01-01 14:12:30', NULL, '退货完成');
INSERT INTO `return_goods` VALUES (10, '2021-01-01 14:24:53', '21111420537', 'qiudb.top@aliyun.com', 'M', 2374, '1325554003@qq.com', '马英发', '2021-01-01 14:25:41', NULL, '退货完成');
INSERT INTO `return_goods` VALUES (11, '2021-01-05 10:05:59', '211322599987', 'qiudb.top@aliyun.com', 'M', 489, '1325554003@qq.com', '马英发', '2021-01-06 14:01:19', NULL, '待收货');
INSERT INTO `return_goods` VALUES (12, '2021-01-05 10:12:37', '211322315037', 'qiudb.top@aliyun.com', 'M', 1318, '1325554003@qq.com', '马英发', '2021-01-06 14:01:21', NULL, '待收货');
INSERT INTO `return_goods` VALUES (13, '2021-01-05 10:23:52', '211321405393', 'qiudb.top@aliyun.com', 'M', 73, '1325554003@qq.com', '马英发', '2021-01-05 10:27:08', '大小不合适', '已拒绝');
INSERT INTO `return_goods` VALUES (14, '2021-01-05 10:40:12', '211510236328', 'qiudb.top@aliyun.com', 'M', 3894, '1325554003@qq.com', '马英发', '2021-01-05 10:40:34', '大小不合适', '拒绝退款');
INSERT INTO `return_goods` VALUES (15, '2021-01-05 10:41:03', '211510236328', 'qiudb.top@aliyun.com', 'M', 3894, '1325554003@qq.com', '马英发', '2021-01-05 10:41:28', '大小不合适', '拒绝退款');
INSERT INTO `return_goods` VALUES (16, '2021-01-05 10:41:54', '211510236328', 'qiudb.top@aliyun.com', 'M', 3894, '1325554003@qq.com', '马英发', '2021-01-05 11:24:16', '大小不合适', '退款完成');
INSERT INTO `return_goods` VALUES (17, '2021-01-05 17:49:52', '211517117744', 'qiudb.top@aliyun.com', 'M', 154, '1325554003@qq.com', '马英发', '2021-01-05 17:50:02', '价格有点贵', '拒绝退款');
INSERT INTO `return_goods` VALUES (18, '2021-01-05 17:50:11', '211517117744', 'qiudb.top@aliyun.com', 'M', 154, '1325554003@qq.com', '马英发', '2021-01-05 17:50:29', '大小不合适', '退款完成');
INSERT INTO `return_goods` VALUES (19, '2021-01-05 17:53:37', '211517517058', 'qiudb.top@aliyun.com', 'M', 626, '1325554003@qq.com', '马英发', '2021-01-05 17:53:51', '质量问题', '退款完成');
INSERT INTO `return_goods` VALUES (20, '2021-01-05 18:07:00', '211518368179', 'qiudb.top@aliyun.com', 'M', 136, '1325554003@qq.com', '马英发', '2021-01-05 18:07:11', '质量问题', '退款完成');
INSERT INTO `return_goods` VALUES (21, '2022-03-01 10:04:47', '223110263492', '15233088662@163.com', '测试', 626, '1325554003@qq.com', '超级管理员', '2022-03-01 10:05:12', '七天无理由退换', '退货完成');
INSERT INTO `return_goods` VALUES (22, '2022-03-01 10:06:57', '223110157600', '15233088662@163.com', '测试', 616, '1325554003@qq.com', '超级管理员', '2022-03-01 10:07:05', '质量问题', '已拒绝');
INSERT INTO `return_goods` VALUES (23, '2022-03-04 21:08:24', '223421202909', '15233088662@163.com', '测试人员', 659, '1325554003@qq.com', '超级管理员', '2022-03-04 21:08:52', '七天无理由退换', '退货完成');
INSERT INTO `return_goods` VALUES (24, '2022-03-04 21:10:19', '223421276224', '15233088662@163.com', '测试人员', 136, '1325554003@qq.com', '超级管理员', '2022-03-04 21:10:34', '七天无理由退换', '退货完成');

-- ----------------------------
-- Table structure for return_reason
-- ----------------------------
DROP TABLE IF EXISTS `return_reason`;
CREATE TABLE `return_reason`  (
  `reason_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '退货id',
  `reason_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退货理由',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`reason_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`reason_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退货原因表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of return_reason
-- ----------------------------
INSERT INTO `return_reason` VALUES (1, '七天无理由退换', 1);
INSERT INTO `return_reason` VALUES (2, '质量问题', 1);
INSERT INTO `return_reason` VALUES (3, '大小不合适', 1);
INSERT INTO `return_reason` VALUES (4, '价格有点贵', 1);
INSERT INTO `return_reason` VALUES (5, '规格 / 款式 /  数量拍错', 1);
INSERT INTO `return_reason` VALUES (6, '收货地址拍错', 1);
INSERT INTO `return_reason` VALUES (7, '暂时不需要了', 1);
INSERT INTO `return_reason` VALUES (8, '其他', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_describe` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  `role_state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = COMPRESSED;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '超级管理员', 1);
INSERT INTO `role` VALUES (2, '订单管理员', '订单管理员', 1);
INSERT INTO `role` VALUES (3, '商品管理员', '商品管理员', 1);
INSERT INTO `role` VALUES (4, '人事管理员', '人事管理员', 1);
INSERT INTO `role` VALUES (5, '顾客', '顾客', 1);

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `cart_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号',
  `product_id` int(5) NOT NULL COMMENT '商品id',
  `pay_amount` int(5) NOT NULL COMMENT '购买数量',
  `product_specs` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  PRIMARY KEY (`cart_id`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`account_number`) REFERENCES `user` (`account_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopping_cart
-- ----------------------------

-- ----------------------------
-- Table structure for specs
-- ----------------------------
DROP TABLE IF EXISTS `specs`;
CREATE TABLE `specs`  (
  `specs_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '规格id',
  `specs_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格类型',
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别',
  PRIMARY KEY (`specs_id`) USING BTREE,
  INDEX `product_type`(`product_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of specs
-- ----------------------------
INSERT INTO `specs` VALUES (1, '6GB+128GB', '手机');
INSERT INTO `specs` VALUES (2, '6GB+256GB', '手机');
INSERT INTO `specs` VALUES (3, '8GB+128GB', '手机');
INSERT INTO `specs` VALUES (4, '8GB+256GB', '手机');
INSERT INTO `specs` VALUES (5, '12GB+128GB', '手机');
INSERT INTO `specs` VALUES (6, '12GB+256GB', '手机');
INSERT INTO `specs` VALUES (7, 'S', '服装');
INSERT INTO `specs` VALUES (8, 'M', '服装');
INSERT INTO `specs` VALUES (9, 'L', '服装');
INSERT INTO `specs` VALUES (10, 'XL', '服装');
INSERT INTO `specs` VALUES (11, 'XXL', '服装');
INSERT INTO `specs` VALUES (12, 'XXXL', '服装');
INSERT INTO `specs` VALUES (13, 'i5-10300H/16G/512G/144Hz', '电脑');
INSERT INTO `specs` VALUES (14, ' i5-10200H/16G/512G/60Hz', '电脑');
INSERT INTO `specs` VALUES (15, 'i7-10750H/16G/512G/144Hz', '电脑');
INSERT INTO `specs` VALUES (16, 'I5/8GB/512G/2.5K 100%sRGB', '电脑');
INSERT INTO `specs` VALUES (17, 'I5/16GB/512G/2.5K 100%sRGB', '电脑');
INSERT INTO `specs` VALUES (18, 'I7/8GB/512G/2.5K 100%sRGB', '电脑');
INSERT INTO `specs` VALUES (19, 'I7/16GB/512G/2.5K 100%sRGB', '电脑');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '供应商id',
  `supplier_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商编号',
  `supplier_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商名称',
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别',
  `principal` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负责人',
  `contact_way` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`supplier_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`supplier_no`) USING BTREE,
  INDEX `product_type`(`product_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '20201230222915715', '天猫', '手机', '李四', '15233088663', 1);
INSERT INTO `supplier` VALUES (2, '20201230222940792', '苏宁', '手机', '王五', '15233088665', 1);
INSERT INTO `supplier` VALUES (3, '20201231102335236', '京东', '生活家电', '李泽涛', '15488622335', 1);
INSERT INTO `supplier` VALUES (4, '202231101745330', '小米', '手机', '李红', '15233004458', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_sex` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `user_state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户状态',
  `summary` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `background_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景图片',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`account_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (11, '1325554003@qq.com', '超级管理员', '839768ab327f67dac8dda87127a2c6d3', NULL, '15233088661', '2022-03-01 09:54:48', '2022-03-04 20:45:32', 1, NULL, NULL, NULL, NULL, 'ADMIN');
INSERT INTO `user` VALUES (14, '15233088662@163.com', '测试人员', 'd19dd6131be492930a4b1ed8835a00f9', '男', '15233088662', '2022-03-04 20:52:32', '2022-03-04 21:06:41', 1, '测试', '北京市 市辖区 东城区 (test)', NULL, NULL, 'CUSTOMER');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(5) NOT NULL COMMENT '用户id',
  `role_id` int(5) NOT NULL COMMENT '角色id',
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (6, 1);
INSERT INTO `user_role` VALUES (7, 1);
INSERT INTO `user_role` VALUES (8, 1);
INSERT INTO `user_role` VALUES (9, 1);
INSERT INTO `user_role` VALUES (10, 1);
INSERT INTO `user_role` VALUES (11, 1);
INSERT INTO `user_role` VALUES (12, 5);
INSERT INTO `user_role` VALUES (13, 3);
INSERT INTO `user_role` VALUES (14, 5);

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip`  (
  `vip_id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'vipid',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号',
  `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '充值时间',
  `overdue_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`vip_id`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'vip表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vip
-- ----------------------------
INSERT INTO `vip` VALUES (1, '15233088662@163.com', '2022-03-01 10:01:48', '2023-03-01 10:01:48');

-- ----------------------------
-- Event structure for product_event
-- ----------------------------
DROP EVENT IF EXISTS `product_event`;
delimiter ;;
CREATE EVENT `product_event`
ON SCHEDULE
EVERY '1' DAY STARTS '2021-01-07 02:00:00'
ON COMPLETION PRESERVE
DO BEGIN
		UPDATE product SET is_new = 0 WHERE sale_time<(CURRENT_TIMESTAMP() + INTERVAL -15 DAY);#修改15天前的新品数据
		DELETE FROM qiu.`order` WHERE order_time < DATE_SUB(CURDATE(),INTERVAL 1 DAY) AND order_state='待付款';	#清除24小时内未付款的订单信息
		DELETE FROM vip WHERE overdue_time <= CURDATE(); #清除 VIP表 会员过期的信息
	END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table order
-- ----------------------------
DROP TRIGGER IF EXISTS `order_delete`;
delimiter ;;
CREATE TRIGGER `order_delete` BEFORE DELETE ON `order` FOR EACH ROW BEGIN
	UPDATE product SET product_stock = product_stock + old.pay_amount WHERE product_no = old.product_no;
    END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `user_insert`;
delimiter ;;
CREATE TRIGGER `user_insert` AFTER INSERT ON `user` FOR EACH ROW BEGIN
	DECLARE roleId INT;
	SET roleId = (SELECT role_id FROM role WHERE role_name ='顾客');
		INSERT user_role VALUES(new.user_id,roleId);
    END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
