/*
 Navicat Premium Data Transfer

 Source Server         : cloud.upenv.com7707
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : cloud.upenv.com:7707
 Source Schema         : sp03

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 10/06/2021 11:10:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for depart
-- ----------------------------
DROP TABLE IF EXISTS `depart`;
CREATE TABLE `depart`  (
  `dept_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `db_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`dept_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of depart
-- ----------------------------
INSERT INTO `depart` VALUES ('1', '财务部', 'sp03');
INSERT INTO `depart` VALUES ('c91f9d127f853520839ad5a322379b78', '人事部', '123456');

SET FOREIGN_KEY_CHECKS = 1;
