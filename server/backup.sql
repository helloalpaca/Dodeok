-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: localhost    Database: dodeok
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `classname` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `answer` text COLLATE utf8mb4_general_ci,
  `emotion` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `solved` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `classname` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nick` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `emotion` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `emotiontime` datetime DEFAULT NULL,
  `nowUnit` int(11) DEFAULT NULL,
  `nowNum` int(11) DEFAULT NULL,
  `status` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `id` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `classname` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `classcode` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `emotions`
--

DROP TABLE IF EXISTS `emotions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emotions` (
  `id` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `classname` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `emotion` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `emotiontime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `feedid` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `classname` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `feedback` text COLLATE utf8mb4_general_ci,
  `feedtime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `missions`
--

DROP TABLE IF EXISTS `missions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missions` (
  `classname` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `unitname` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `mission` text COLLATE utf8mb4_general_ci,
  `type` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `url` text COLLATE utf8mb4_general_ci,
  `ready` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `numbers`
--

DROP TABLE IF EXISTS `numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `numbers` (
  `classname` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `unit` int(11) DEFAULT NULL,
  `numbers` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `pw` char(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `job` char(10) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-28 17:43:41
