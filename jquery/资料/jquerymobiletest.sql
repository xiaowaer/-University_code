/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : jquerymobiletest

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 12/11/2019 22:58:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `commenter` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zan` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (25, '测试1', '小娃儿', 'true');
INSERT INTO `comment` VALUES (26, '测试代码', 'JUNLI', 'true');
INSERT INTO `comment` VALUES (27, '测试代码', 'JUNLI', 'true');
INSERT INTO `comment` VALUES (28, '测试代码', 'JUNLI', 'true');
INSERT INTO `comment` VALUES (29, '测试代码', 'JUNLI', 'true');
INSERT INTO `comment` VALUES (30, '测试代码', '阿斯蒂芬', 'fasle');
INSERT INTO `comment` VALUES (31, 'rett', 'wtr', 'true');
INSERT INTO `comment` VALUES (32, '///', 'GEWR', 'true');
INSERT INTO `comment` VALUES (33, '你好呀', 'ljj', 'true');
INSERT INTO `comment` VALUES (34, '', '', 'fasle');
INSERT INTO `comment` VALUES (35, 'jquerymobile大作业', '利俊杰', 'true');
INSERT INTO `comment` VALUES (36, 'jquery', '嘻嘻嘻嘻', 'true');
INSERT INTO `comment` VALUES (37, '不好呀', '额呵呵', 'fasle');
INSERT INTO `comment` VALUES (38, '快想', '你好', 'true');
INSERT INTO `comment` VALUES (39, '利俊杰', '大家觉得记得记得就', 'true');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `usertype` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123', 'su');
INSERT INTO `user` VALUES (2, 'ljj', '123456', 'user');

SET FOREIGN_KEY_CHECKS = 1;
