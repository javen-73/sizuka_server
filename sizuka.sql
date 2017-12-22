/*
Navicat MySQL Data Transfer

Source Server         : sizuka
Source Server Version : 50173
Source Host           : 119.29.35.223:3306
Source Database       : sizuka

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-22 11:16:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account_book
-- ----------------------------
DROP TABLE IF EXISTS `account_book`;
CREATE TABLE `account_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `book_name` varchar(20) DEFAULT NULL COMMENT '账簿名称',
  `status` int(2) DEFAULT '1' COMMENT '0 软删除 ,1正常',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kf_1` (`user_id`),
  KEY `index` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for account_item
-- ----------------------------
DROP TABLE IF EXISTS `account_item`;
CREATE TABLE `account_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `book_id` int(11) NOT NULL,
  `price_type` int(6) NOT NULL,
  `price` int(11) NOT NULL,
  `item_status` int(2) DEFAULT '0' COMMENT '0支出 1收入',
  `status` int(2) DEFAULT '1' COMMENT '0 软删除 1正常',
  `remark` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kf_1` (`user_id`),
  KEY `kf_2` (`book_id`),
  KEY `index` (`id`),
  KEY `fk_3` (`price_type`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for item_type
-- ----------------------------
DROP TABLE IF EXISTS `item_type`;
CREATE TABLE `item_type` (
  `id` int(11) NOT NULL,
  `type_name` varchar(10) DEFAULT NULL,
  `status` int(2) DEFAULT '0' COMMENT '0 支出 1 收入  3 删除',
  PRIMARY KEY (`id`),
  KEY `index` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `alias_name` varchar(20) DEFAULT '' COMMENT '别名',
  `sex` int(2) NOT NULL DEFAULT '3' COMMENT '0女 1男 3未知',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `profile_picture` varchar(255) DEFAULT '' COMMENT '头像url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `key` (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `version` varchar(20) NOT NULL,
  `update` text,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
