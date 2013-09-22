-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- ホスト: localhost
-- 生成日時: 2013 年 9 月 11 日 16:06
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
-- テーブルのデータのダンプ `item`
--

INSERT INTO `item` (`category_id`, `title`, `search_word`, `point`, `description`, `link`, `pub_date`, `create_date`, `update_date`) VALUES
(2, 'PHP', 'PHP', 0, '', '', NULL, '2013-09-11 00:48:19', '2013-09-11 00:48:19'),
(2, 'Java', 'Java', 0, '', '', NULL, '2013-09-11 00:48:19', '2013-09-11 00:48:19'),
(2, 'Javascript', 'Javascript', 0, '', '', NULL, '2013-09-11 00:48:44', '2013-09-11 00:48:44'),
(2, 'Ruby', 'Ruby', 0, '', '', NULL, '2013-09-11 00:48:44', '2013-09-11 00:48:44'),
(2, 'Python', 'Python', 0, '', '', NULL, '2013-09-11 00:49:15', '2013-09-11 00:49:15'),
(2, 'Perl', 'Perl', 0, '', '', NULL, '2013-09-11 00:49:15', '2013-09-11 00:49:15'),
(3, 'CakePHP', 'CakePHP', 0, '', '', NULL, '2013-09-11 00:52:15', '2013-09-11 00:52:15'),
(3, 'Symfony', 'Symfony', 0, '', '', NULL, '2013-09-11 00:52:15', '2013-09-11 00:52:15'),
(3, 'Zend Framework', 'Zend Framework', 0, '', '', NULL, '2013-09-11 00:53:29', '2013-09-11 00:53:29'),
(3, 'CodeIgniter', 'CodeIgniter', 0, '', '', NULL, '2013-09-11 00:53:29', '2013-09-11 00:53:29'),
(3, 'Wicket', 'Wicket', 0, '', '', NULL, '2013-09-11 00:54:08', '2013-09-11 00:54:08'),
(3, 'Play Framework', 'Play Framework', 0, '', '', NULL, '2013-09-11 00:54:08', '2013-09-11 00:54:08'),
(3, ' FuelPHP', ' FuelPHP', 0, '', '', NULL, '2013-09-11 00:55:12', '2013-09-11 00:55:12'),
(3, 'Django', 'Django', 0, '', '', NULL, '2013-09-11 00:55:12', '2013-09-11 00:55:12'),
(3, 'Sinatra', 'Sinatra', 0, '', '', NULL, '2013-09-11 00:55:59', '2013-09-11 00:55:59'),
(3, 'Ruby on Rails', 'Ruby on Rails', 0, '', '', NULL, '2013-09-11 00:55:59', '2013-09-11 00:55:59');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
