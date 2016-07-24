-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.32 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for mall
DROP DATABASE IF EXISTS `mall`;
CREATE DATABASE IF NOT EXISTS `mall` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mall`;


-- Dumping structure for table mall.cart
DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_no` int(10) NOT NULL AUTO_INCREMENT,
  `item_no` int(10) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `cart_count` int(10) NOT NULL,
  PRIMARY KEY (`cart_no`),
  KEY `FK__item` (`item_no`),
  KEY `FK__member` (`member_id`),
  CONSTRAINT `FK__item` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`),
  CONSTRAINT `FK__member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='장바구니 테이블';

-- Dumping data for table mall.cart: ~0 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`cart_no`, `item_no`, `member_id`, `cart_count`) VALUES
	(16, 13, 'id001', 1),
	(17, 12, 'id001', 1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;


-- Dumping structure for table mall.comment
DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `comment_no` int(10) NOT NULL AUTO_INCREMENT,
  `item_no` int(10) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `comment_content` text NOT NULL,
  `comment_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `comment_title` varchar(200) NOT NULL,
  `comment_rate` int(11) NOT NULL,
  `comment_star` varchar(50) NOT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `FK_comment_item` (`item_no`),
  KEY `FK_comment_member` (`member_id`),
  CONSTRAINT `FK_comment_item` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='댓글 테이블';

-- Dumping data for table mall.comment: ~0 rows (approximately)
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`comment_no`, `item_no`, `member_id`, `comment_content`, `comment_date`, `comment_title`, `comment_rate`, `comment_star`) VALUES
	(1, 10, 'id001', 'test', '2016-07-12 15:14:45', 'test', 0, ''),
	(6, 13, '', 'test', '2016-07-12 15:36:53', 'test', 1, '★☆☆☆☆'),
	(7, 10, '', 'test', '2016-07-12 15:37:51', 'test', 1, '★☆☆☆☆'),
	(8, 13, '', 'test', '2016-07-12 15:39:59', 'test', 2, '★★☆☆☆'),
	(10, 12, 'id001', 'ㄹ', '2016-07-17 15:56:14', '굿', 4, '★★★★☆');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;


-- Dumping structure for table mall.item
DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `item_no` int(10) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) NOT NULL,
  `item_category` varchar(50) NOT NULL,
  `item_image` longtext NOT NULL,
  `item_price` int(11) NOT NULL,
  `item_origin` varchar(50) NOT NULL,
  `item_quantity` int(11) NOT NULL,
  `seller_no` int(11) NOT NULL,
  `item_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `item_harvest` date NOT NULL DEFAULT '0000-00-00',
  `item_stock` int(11) NOT NULL,
  `item_detail` varchar(50) DEFAULT NULL,
  `item_out` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`item_no`),
  KEY `FK_item_seller` (`seller_no`),
  CONSTRAINT `FK_item_seller` FOREIGN KEY (`seller_no`) REFERENCES `seller` (`seller_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='상품테이블';

-- Dumping data for table mall.item: ~0 rows (approximately)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`item_no`, `item_name`, `item_category`, `item_image`, `item_price`, `item_origin`, `item_quantity`, `seller_no`, `item_date`, `item_harvest`, `item_stock`, `item_detail`, `item_out`) VALUES
	(9, '사과', 'spring', 'http://www.dietsupporters.kr/upload/smarteditor_56711a1218de2.png', 2000, '경북안동', 20, 3, '2016-07-17 15:20:05', '2015-07-01', 30, '0', NULL),
	(10, '귤', 'summer', 'http://cfile25.uf.tistory.com/image/230F8B3E5576F099026D57', 2000, '경북구미', 21, 1, '2016-07-17 15:20:23', '2016-07-10', 50, '0', NULL),
	(11, '낑깡', 'fall', 'http://cfile28.uf.tistory.com/image/2213BD39573ACE791E469C', 3000, '부산', 30, 2, '2016-07-17 15:20:19', '2016-06-30', 20, '0', NULL),
	(12, '복숭아', 'winter', 'http://www.healiving.co.kr/files/attach/images/2381/531/005/3e028ed4ae8d2a26e4a2f33297910f90.png', 3000, '익산', 20, 3, '2016-07-17 15:20:58', '2015-12-31', 50, '0', NULL),
	(13, '키위', '겨울', '\r\ndata:image/jpeg;base64,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', 3000, '중국', 50, 1, '2016-07-17 15:19:52', '1992-01-11', 30, '맛있어요 중국산키위', NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


-- Dumping structure for table mall.member
DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (
  `member_id` varchar(50) NOT NULL,
  `member_pw` varchar(50) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `member_post` varchar(50) NOT NULL COMMENT '우편번호',
  `member_parcle_addr` varchar(200) NOT NULL COMMENT '지번주소',
  `member_road_addr` varchar(200) NOT NULL COMMENT '도로명주소',
  `member_phone` varchar(50) NOT NULL,
  `member_out` varchar(50) NOT NULL DEFAULT 'N',
  `member_level` varchar(50) NOT NULL DEFAULT '회원' COMMENT '권한',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회원테이블';

-- Dumping data for table mall.member: ~5 rows (approximately)
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`, `member_pw`, `member_name`, `member_post`, `member_parcle_addr`, `member_road_addr`, `member_phone`, `member_out`, `member_level`) VALUES
	('gkssud123', '123123', '이한녕', '06267', '서울 강남구 도곡동 953-3', '서울 강남구 강남대로 242 (도곡동)', '01005050505', 'Y', '회원'),
	('id001', 'pw001', '안소영', '111350', '이건주소다', '이건 도로명주소다', '010', 'N', '회원'),
	('id002', 'pw002', '안재혁', '632001', '하하하', '후후후', '010', 'N', '관리자'),
	('id003', 'pw0000', '김김', '46760', '부산 강서구 르노삼성대로 14 (신호동)', '부산 강서구 신호동 294', '0202', 'N', '회원'),
	('id005', 'pw00500', '안안안', '42957', '대구 달성군 화원읍 설화리 870', '대구 달성군 화원읍 류목정길 5', '01024222706', 'N', '회원');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


-- Dumping structure for table mall.notice
DROP TABLE IF EXISTS `notice`;
CREATE TABLE IF NOT EXISTS `notice` (
  `notice_no` int(10) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(50) NOT NULL,
  `notice_content` longtext NOT NULL,
  `notice_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `member_id` varchar(50) NOT NULL,
  PRIMARY KEY (`notice_no`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8 COMMENT='공지사항';

-- Dumping data for table mall.notice: ~0 rows (approximately)
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`notice_no`, `notice_title`, `notice_content`, `notice_date`, `member_id`) VALUES
	(52, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(53, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(54, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(55, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(56, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(57, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(58, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(59, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(60, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(61, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(62, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(63, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(64, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(65, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(66, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(67, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(68, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(69, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(70, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(71, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(72, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(73, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(74, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(75, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(76, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(77, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(78, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(79, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(80, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(81, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(82, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(83, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(84, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(85, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(86, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(87, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(88, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(89, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(90, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(91, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(92, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(93, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(94, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(95, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(96, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(97, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(98, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(99, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001'),
	(100, 'ㄹㄹ', 'ㄹㄹ', '2016-07-12 14:44:39', 'id001');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;


-- Dumping structure for table mall.orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `orders_no` int(10) NOT NULL AUTO_INCREMENT,
  `item_no` int(10) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `orders_group` varchar(50) NOT NULL,
  `orders_total_price` int(11) NOT NULL,
  `orders_delivery_confirm` varchar(50) NOT NULL DEFAULT 'N',
  `orders_delivery_date` timestamp NULL DEFAULT NULL,
  `orders_count` int(11) NOT NULL,
  `orders_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `orders_recipient_name` varchar(50) NOT NULL,
  `orders_recipient_phone` varchar(50) NOT NULL,
  `orders_recipient_post` int(11) NOT NULL,
  `orders_recipient_parceladdr` varchar(50) NOT NULL,
  `orders_recipient_roadaddr` varchar(50) NOT NULL,
  `orders_confirm` varchar(50) NOT NULL DEFAULT 'N',
  `orders_confirm_date` timestamp NULL DEFAULT NULL,
  `orders_refund` varchar(50) NOT NULL DEFAULT 'N',
  `orders_payment_method` varchar(50) NOT NULL DEFAULT 'N',
  PRIMARY KEY (`orders_no`),
  KEY `FK_orders_item` (`item_no`),
  KEY `FK_orders_member` (`member_id`),
  CONSTRAINT `FK_orders_item` FOREIGN KEY (`item_no`) REFERENCES `item` (`item_no`),
  CONSTRAINT `FK_orders_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='주문내역 테이블';

-- Dumping data for table mall.orders: ~0 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`orders_no`, `item_no`, `member_id`, `orders_group`, `orders_total_price`, `orders_delivery_confirm`, `orders_delivery_date`, `orders_count`, `orders_date`, `orders_recipient_name`, `orders_recipient_phone`, `orders_recipient_post`, `orders_recipient_parceladdr`, `orders_recipient_roadaddr`, `orders_confirm`, `orders_confirm_date`, `orders_refund`, `orders_payment_method`) VALUES
	(55, 13, 'id001', '1', 10000, 'N', NULL, 100, '2016-07-17 11:58:39', '안소영', '010', 111350, '이건주소다', '이건 도로명주소다', 'N', NULL, 'N', 'payCard'),
	(56, 13, 'id001', '1', 10000, 'N', NULL, 3, '2016-07-17 11:58:39', '안소영', '010', 111350, '이건주소다', '이건 도로명주소다', 'N', NULL, 'N', 'payCard'),
	(57, 13, 'id001', '2', 10000, 'N', NULL, 1, '2016-07-17 15:15:49', '안소영', '010', 111350, '이건주소다', '이건 도로명주소다', 'N', NULL, 'N', 'payCard'),
	(58, 12, 'id001', '2', 10000, 'N', NULL, 1, '2016-07-17 15:15:49', '안소영', '010', 111350, '이건주소다', '이건 도로명주소다', 'N', NULL, 'N', 'payCard');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Dumping structure for table mall.refund
DROP TABLE IF EXISTS `refund`;
CREATE TABLE IF NOT EXISTS `refund` (
  `refund_no` int(10) NOT NULL AUTO_INCREMENT,
  `orders_no` int(10) NOT NULL,
  `member_id` varchar(50) NOT NULL,
  `refund_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `refund_reason` text NOT NULL,
  `refund_confirm` varchar(50) NOT NULL DEFAULT 'N',
  `refund_confirm_date` timestamp NULL DEFAULT NULL,
  `refund_money` int(10) NOT NULL,
  PRIMARY KEY (`refund_no`),
  KEY `FK_refund_orders` (`orders_no`),
  KEY `FK_refund_member` (`member_id`),
  CONSTRAINT `FK_refund_member` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK_refund_orders` FOREIGN KEY (`orders_no`) REFERENCES `orders` (`orders_no`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='환불 테이블';

-- Dumping data for table mall.refund: ~0 rows (approximately)
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
