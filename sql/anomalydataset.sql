/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : anomalydataset

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 02/01/2021 20:46:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for abnormal_info
-- ----------------------------
DROP TABLE IF EXISTS `abnormal_info`;
CREATE TABLE `abnormal_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `transfer_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `anomaly_document` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` bigint(20) NULL DEFAULT NULL,
  `end_time` bigint(20) NULL DEFAULT NULL,
  `video_id` int(11) NULL DEFAULT NULL,
  `video_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `video_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of abnormal_info
-- ----------------------------
INSERT INTO `abnormal_info` VALUES (1, '1', '2021-01-02 20:16:17', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (2, '1', '2021-01-02 20:18:43', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (3, '1', '2021-01-02 20:19:17', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (4, '1', '2021-01-02 20:21:59', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (5, '1', '2021-01-02 20:25:54', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (6, '1', '2021-01-02 20:26:22', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (7, '1', '2021-01-02 20:31:23', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (8, '1', '2021-01-02 20:31:31', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (9, '1', '2021-01-02 20:31:34', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (10, '1', '2021-01-02 20:31:41', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (11, '1', '2021-01-02 20:38:44', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (12, '1', '2021-01-02 20:39:01', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (13, '1', '2021-01-02 20:39:18', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (14, '1', '2021-01-02 20:39:18', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (15, '1', '2021-01-02 20:39:23', NULL, '视频Avg_C.mp4在12微秒到36微秒出现了1', 12, 36, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (16, '1', '2021-01-02 20:41:56', NULL, '视频Avg_C.mp4在12000微秒到36000微秒出现了1', 12000, 36000, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');
INSERT INTO `abnormal_info` VALUES (17, '1', '2021-01-02 20:42:18', NULL, '视频Avg_C.mp4在12000微秒到36000微秒出现了1', 12000, 36000, 111111, 'Avg_C.mp4', 'd:/mediapath/', 'zhangsan');

-- ----------------------------
-- Table structure for default_setting
-- ----------------------------
DROP TABLE IF EXISTS `default_setting`;
CREATE TABLE `default_setting`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '默认设置id',
  `input_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入类型',
  `video_id` int(11) NULL DEFAULT NULL COMMENT '视频id',
  `save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `weights` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型权重',
  `model_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of default_setting
-- ----------------------------
INSERT INTO `default_setting` VALUES (1, '打劫', 111111, 'd:/mediapath/', '3', 'd:/modulepath');

-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `transfer_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_document` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明文档',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of log_info
-- ----------------------------
INSERT INTO `log_info` VALUES (1, '开始', '2021-01-02 20:12:16', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (2, '1', '2021-01-02 20:12:27', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (3, '1', '2021-01-02 20:12:27', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (4, '开始', '2021-01-02 20:13:07', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (5, '1', '2021-01-02 20:13:36', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (6, '1', '2021-01-02 20:13:40', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (7, '开始', '2021-01-02 20:16:03', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (8, '1', '2021-01-02 20:16:10', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (9, '1', '2021-01-02 20:16:11', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (10, '开始', '2021-01-02 20:18:28', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (11, '1', '2021-01-02 20:18:38', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (12, '1', '2021-01-02 20:18:39', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (13, '开始', '2021-01-02 20:19:17', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (14, '1', '2021-01-02 20:19:17', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (15, '1', '2021-01-02 20:19:17', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (16, '开始', '2021-01-02 20:21:59', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (17, '1', '2021-01-02 20:21:59', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (18, '1', '2021-01-02 20:21:59', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (19, '暂停', '2021-01-02 20:22:14', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了暂停按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (20, '停止', '2021-01-02 20:22:22', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了停止按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (21, '开始', '2021-01-02 20:25:54', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (22, '1', '2021-01-02 20:25:54', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (23, '1', '2021-01-02 20:25:54', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (24, '开始', '2021-01-02 20:26:22', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (25, '1', '2021-01-02 20:26:22', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (26, '1', '2021-01-02 20:26:22', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (27, '暂停', '2021-01-02 20:30:55', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了暂停按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (28, '停止', '2021-01-02 20:31:04', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了停止按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (29, '暂停', '2021-01-02 20:31:04', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了暂停按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (30, '停止', '2021-01-02 20:31:14', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了停止按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (31, '停止', '2021-01-02 20:31:15', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了停止按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (32, '继续', '2021-01-02 20:31:23', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了继续按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (33, '1', '2021-01-02 20:31:23', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (34, '1', '2021-01-02 20:31:23', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (35, '开始', '2021-01-02 20:31:31', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (36, '1', '2021-01-02 20:31:31', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (37, '1', '2021-01-02 20:31:31', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (38, '开始', '2021-01-02 20:31:34', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (39, '1', '2021-01-02 20:31:34', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (40, '1', '2021-01-02 20:31:34', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (41, '暂停', '2021-01-02 20:31:37', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了暂停按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (42, '继续', '2021-01-02 20:31:41', NULL, '用户zhangsan在视频Avg2_C.mp4播放到0秒时点击了继续按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (43, '1', '2021-01-02 20:31:41', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (44, '1', '2021-01-02 20:31:41', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (45, '开始', '2021-01-02 20:38:44', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (46, '1', '2021-01-02 20:38:44', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (47, '1', '2021-01-02 20:38:44', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (48, '开始', '2021-01-02 20:39:01', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (49, '1', '2021-01-02 20:39:01', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (50, '1', '2021-01-02 20:39:01', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (51, '暂停', '2021-01-02 20:39:16', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了暂停按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (52, '停止', '2021-01-02 20:39:17', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了停止按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (53, '继续', '2021-01-02 20:39:17', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了继续按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (54, '1', '2021-01-02 20:39:17', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (55, '1', '2021-01-02 20:39:18', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (56, '开始', '2021-01-02 20:39:18', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (57, '1', '2021-01-02 20:39:18', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (58, '1', '2021-01-02 20:39:18', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (59, '开始', '2021-01-02 20:39:23', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (60, '1', '2021-01-02 20:39:23', NULL, '视频Avg_C.mp4在12微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (61, '1', '2021-01-02 20:39:23', NULL, '视频Avg_C.mp4在36微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (62, '开始', '2021-01-02 20:41:55', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (63, '1', '2021-01-02 20:41:55', NULL, '视频Avg_C.mp4在12000微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (64, '1', '2021-01-02 20:41:56', NULL, '视频Avg_C.mp4在36000微秒中处结束了1', 'zhangsan');
INSERT INTO `log_info` VALUES (65, '开始', '2021-01-02 20:42:02', NULL, '用户zhangsan在视频Avg_C.mp4播放到0秒时点击了开始按钮', 'zhangsan');
INSERT INTO `log_info` VALUES (66, '1', '2021-01-02 20:42:02', NULL, '视频Avg_C.mp4在12000微秒中处出现了1', 'zhangsan');
INSERT INTO `log_info` VALUES (67, '1', '2021-01-02 20:42:13', NULL, '视频Avg_C.mp4在36000微秒中处结束了1', 'zhangsan');

-- ----------------------------
-- Table structure for storage_manager
-- ----------------------------
DROP TABLE IF EXISTS `storage_manager`;
CREATE TABLE `storage_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(20) NULL DEFAULT NULL,
  `video_width` int(11) NULL DEFAULT NULL,
  `video_height` int(11) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `transfer_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `frame_num` int(11) NULL DEFAULT NULL COMMENT '帧数',
  `fps` int(11) NULL DEFAULT NULL COMMENT '每秒帧数',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage_manager
-- ----------------------------
INSERT INTO `storage_manager` VALUES (1, '111111', 'Avg_C.mp4', 1974456057, 360, 480, '2021-01-02 20:21:59', NULL, 4512, 60, 'zhangsan');

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '系统配置id',
  `input_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入类型',
  `video_id` int(11) NULL DEFAULT NULL COMMENT '视频id',
  `video` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频文件名',
  `save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `weights` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型权重',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模型文件名',
  `play_set` int(11) NULL DEFAULT NULL COMMENT '播放设置',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用于确定由哪个用户设定的',
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES (1, '打劫', 111111, 'Avg2_C.mp4', 'd:/mediapath/', 'Plot_All_ROC.m', 'Save_C3DFeatures_32Segments_beifeng.m', 1, 'zhangsan');
INSERT INTO `sys_setting` VALUES (2, '摔跤', 222222, 'Avg_C.mp4', 'd:/mediapath/', 'Plot_All_ROC.m', 'Save_C3DFeatures_32Segments_beifeng.m', 3, 'zhangsan');

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `userstatus` int(20) NULL DEFAULT NULL COMMENT '状态',
  `create_date` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '建档日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (1, 'zhangsan', 'zhangsan', '123456', 1, '2021-01-02 19:22:51.091');
INSERT INTO `user_inf` VALUES (4, 'lisi', 'lisi', '123456', 1, '2021-01-02 19:23:26.756');

-- ----------------------------
-- Table structure for video_info
-- ----------------------------
DROP TABLE IF EXISTS `video_info`;
CREATE TABLE `video_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `video_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_size` bigint(30) NULL DEFAULT NULL,
  `video_width` int(20) NULL DEFAULT NULL,
  `video_height` int(20) NULL DEFAULT NULL,
  `frame_num` int(20) NULL DEFAULT NULL,
  `fps` int(20) NULL DEFAULT NULL,
  `video_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `event_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` int(11) NULL DEFAULT NULL,
  `end_time` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_info
-- ----------------------------
INSERT INTO `video_info` VALUES (1, '111111', 'Avg_C.mp4', 1974456057, 360, 480, 4512, 60, 'D:\\IDEA_Project\\AbnormalDetection\\video\\Avg2_C.mp4', '1', 12000, 36000);
INSERT INTO `video_info` VALUES (2, '222222', 'Avg2_C.mp4', 121323, 360, 480, 5000, 60, 'D:\\IDEA_Project\\AbnormalDetection\\video\\Avg_C.mp4', '1', 12000, 24000);

SET FOREIGN_KEY_CHECKS = 1;
