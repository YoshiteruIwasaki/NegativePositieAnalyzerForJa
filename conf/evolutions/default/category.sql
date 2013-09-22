-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- ホスト: localhost
-- 生成日時: 2013 年 9 月 11 日 16:48
-- サーバのバージョン: 5.5.29
-- PHP のバージョン: 5.4.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- データベース: `book-negaposi`
--

--
-- テーブルのデータのダンプ `category`
--

INSERT INTO `category` (`category_id`, `title`, `link`, `consumer_key`, `consumer_secret`, `access_token`, `access_token_secret`, `create_date`, `update_date`) VALUES
(1, 'Amazon Book', 'http://www.amazon.co.jp/gp/rss/bestsellers/books/ref=zg_bs_books_rsslink', 'KmUsH1mqVSZ0Hbl4TaRqA', 'neMxfdsRgtorCJLHFXb04YGM8WLkqrkHelVVfQY', '1707072319-pM5F5yT5Tlmljnk6NtdkwXb8f9rTmEQ5NglLpd7', 'yDYibH49Xlb3SfawvGJKL08fT7kAgIw6Fttq5nyM', '2013-09-11 23:21:42', '2013-09-11 23:21:42'),
(2, '開発言語', NULL, 'KmUsH1mqVSZ0Hbl4TaRqA', 'neMxfdsRgtorCJLHFXb04YGM8WLkqrkHelVVfQY', '1707072319-pM5F5yT5Tlmljnk6NtdkwXb8f9rTmEQ5NglLpd7', 'yDYibH49Xlb3SfawvGJKL08fT7kAgIw6Fttq5nyM', '2013-09-11 23:44:04', '2013-09-11 23:44:04'),
(3, 'フレームワーク', NULL, 'KmUsH1mqVSZ0Hbl4TaRqA', 'neMxfdsRgtorCJLHFXb04YGM8WLkqrkHelVVfQY', '1707072319-pM5F5yT5Tlmljnk6NtdkwXb8f9rTmEQ5NglLpd7', 'yDYibH49Xlb3SfawvGJKL08fT7kAgIw6Fttq5nyM', '2013-09-11 23:44:04', '2013-09-11 23:44:04'),
(4, 'Google Trends', 'http://www.google.co.jp/trends/hottrends/atom/feed?pn=p4', 'KmUsH1mqVSZ0Hbl4TaRqA', 'neMxfdsRgtorCJLHFXb04YGM8WLkqrkHelVVfQY', '1707072319-pM5F5yT5Tlmljnk6NtdkwXb8f9rTmEQ5NglLpd7', 'yDYibH49Xlb3SfawvGJKL08fT7kAgIw6Fttq5nyM', '2013-09-11 23:44:04', '2013-09-11 23:44:04');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
