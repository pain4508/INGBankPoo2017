CREATE DATABASE  IF NOT EXISTS `INGBank` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `INGBank`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: INGBank
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Cliente`
--

DROP TABLE IF EXISTS `Cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cliente` (
  `IdCliente` int(12) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Direccion` varchar(300) NOT NULL,
  `IdSexo` int(11) NOT NULL,
  `Telefono` varchar(10) NOT NULL,
  `IdNacionalidad` int(11) NOT NULL,
  PRIMARY KEY (`IdCliente`),
  KEY `Fk_Cliente_Sexo_idx` (`IdSexo`),
  KEY `Fk_Cliente_Nacionalidad_idx` (`IdNacionalidad`),
  CONSTRAINT `Fk_Cliente_Nacionalidad` FOREIGN KEY (`IdNacionalidad`) REFERENCES `Nacionalidad` (`IdNacionalidad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_Cliente_Sexo` FOREIGN KEY (`IdSexo`) REFERENCES `Sexo` (`IdSexo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cliente`
--

LOCK TABLES `Cliente` WRITE;
/*!40000 ALTER TABLE `Cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cuenta`
--

DROP TABLE IF EXISTS `Cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cuenta` (
  `IdCuenta` int(12) NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `IdTipoCuenta` int(11) NOT NULL,
  `Saldo` decimal(10,0) NOT NULL,
  `Fecha_de_Creacion` date NOT NULL,
  `IdMovimiento` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  PRIMARY KEY (`IdCuenta`),
  KEY `IdCliente_idx` (`IdCliente`),
  KEY `IdTipoCuenta_idx` (`IdTipoCuenta`),
  KEY `Fk_Cuenta_Usuario_idx` (`IdUsuario`),
  KEY `Fk_Cuenta_Movimiento_idx` (`IdMovimiento`),
  CONSTRAINT `Fk_Cuenta_Cliente` FOREIGN KEY (`IdCliente`) REFERENCES `Cliente` (`IdCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_Cuenta_Movimiento` FOREIGN KEY (`IdMovimiento`) REFERENCES `Movimiento` (`IdMovimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_Cuenta_TipoCuenta` FOREIGN KEY (`IdTipoCuenta`) REFERENCES `TipoCuenta` (`IdTipoCuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_Cuenta_Usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `Usuario` (`IdUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cuenta`
--

LOCK TABLES `Cuenta` WRITE;
/*!40000 ALTER TABLE `Cuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movimiento`
--

DROP TABLE IF EXISTS `Movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movimiento` (
  `IdMovimiento` int(11) NOT NULL,
  `Movimiento` decimal(10,0) NOT NULL,
  `Fecha_Movimiento` date NOT NULL,
  PRIMARY KEY (`IdMovimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movimiento`
--

LOCK TABLES `Movimiento` WRITE;
/*!40000 ALTER TABLE `Movimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `Movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Nacionalidad`
--

DROP TABLE IF EXISTS `Nacionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nacionalidad` (
  `IdNacionalidad` int(11) NOT NULL,
  `Nacionalidadcol` varchar(45) NOT NULL,
  PRIMARY KEY (`IdNacionalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nacionalidad`
--

LOCK TABLES `Nacionalidad` WRITE;
/*!40000 ALTER TABLE `Nacionalidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Nacionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sexo`
--

DROP TABLE IF EXISTS `Sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sexo` (
  `IdSexo` int(11) NOT NULL AUTO_INCREMENT,
  `Sexo` varchar(10) NOT NULL,
  PRIMARY KEY (`IdSexo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sexo`
--

LOCK TABLES `Sexo` WRITE;
/*!40000 ALTER TABLE `Sexo` DISABLE KEYS */;
INSERT INTO `Sexo` VALUES (1,'Masculino'),(2,'Femenino');
/*!40000 ALTER TABLE `Sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoCuenta`
--

DROP TABLE IF EXISTS `TipoCuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoCuenta` (
  `IdTipoCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `TipoCuenta` varchar(50) NOT NULL,
  PRIMARY KEY (`IdTipoCuenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoCuenta`
--

LOCK TABLES `TipoCuenta` WRITE;
/*!40000 ALTER TABLE `TipoCuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `TipoCuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Usuario` varchar(45) NOT NULL,
  `Contrasena` varchar(100) NOT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-02 14:24:41
