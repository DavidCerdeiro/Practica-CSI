-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-10-2023 a las 18:17:10
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `traidor`
--
CREATE DATABASE IF NOT EXISTS `traidor` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `traidor`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stadium`
--

CREATE TABLE `stadium` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `stadium`
--

INSERT INTO `stadium` (`id`, `name`) VALUES
(2, 'Estadio de las Hadas futboleras'),
(1, 'Estadio Olímpico de Shrek'),
(3, 'Ogro Stadium');

--
-- Disparadores `stadium`
--
DELIMITER $$
CREATE TRIGGER `stadium_bi` BEFORE INSERT ON `stadium` FOR EACH ROW begin
if NEW.name = '' then
signal sqlstate '45000' set
message_text = 'name no puede ser cadena vacía.';
end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `stadium_bu` BEFORE UPDATE ON `stadium` FOR EACH ROW begin
if NEW.name = '' then
signal sqlstate '45000' set
message_text = 'name no puede ser cadena vacía.';
end if;
end
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `stadium`
--
ALTER TABLE `stadium`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `stadium`
--
ALTER TABLE `stadium`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
