/*
 Navicat Premium Data Transfer

 Source Server         : battlehawk233.cn-test
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : battlehawk233.cn:10036
 Source Schema         : datavisualization

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 22/04/2021 10:45:39
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
  UNIQUE INDEX `username`(`username`, `email`, `phone`) USING BTREE COMMENT '唯一索引',
  INDEX `nickname`(`nickname`) USING BTREE COMMENT '昵称索引(加快搜索速度)',
  INDEX `login_key`(`username`, `email`, `phone`) USING BTREE COMMENT '登录关键字索引',
  INDEX `login_pass`(`passwd`) USING BTREE COMMENT '登录密码索引'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for v_login
-- ----------------------------
DROP VIEW IF EXISTS `v_login`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_login` AS select `t_user`.`userid` AS `userid`,`t_user`.`username` AS `keyword`,`t_user`.`passwd` AS `passwd` from `t_user` where (`t_user`.`islocked` = 0) union select `t_user`.`userid` AS `userid`,`t_user`.`email` AS `keyword`,`t_user`.`passwd` AS `passwd` from `t_user` where ((`t_user`.`islocked` = 0) and (`t_user`.`email` is not null)) union select `t_user`.`userid` AS `userid`,`t_user`.`phone` AS `keyword`,`t_user`.`passwd` AS `passwd` from `t_user` where ((`t_user`.`islocked` = 0) and (`t_user`.`phone` is not null));

SET FOREIGN_KEY_CHECKS = 1;
