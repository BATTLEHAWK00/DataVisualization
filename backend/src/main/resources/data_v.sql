/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : data_v

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 19/04/2021 17:41:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userid` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID(主键)',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `passwd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码(MD5加盐存储)',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
  `regtime` datetime(0) NOT NULL COMMENT '注册时间',
  `usertype` tinyint(0) NOT NULL COMMENT '用户类型',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `islocked` bit(1) NOT NULL DEFAULT b'0' COMMENT '账户锁定状态',
  PRIMARY KEY (`userid`, `username`) USING BTREE,
  INDEX `nickname`(`nickname`) USING BTREE,
  INDEX `email`(`email`, `phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_login
-- ----------------------------
DROP VIEW IF EXISTS `v_login`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_login` AS select `t_user`.`userid` AS `userid`,`t_user`.`username` AS `username`,`t_user`.`passwd` AS `passwd` from `t_user` where (`t_user`.`islocked` = 0);

SET FOREIGN_KEY_CHECKS = 1;
