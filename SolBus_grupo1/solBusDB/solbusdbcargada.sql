-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2024 a las 15:39:36
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `solbusdbcargada`
--
CREATE DATABASE IF NOT EXISTS `solbusdbcargada` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `solbusdbcargada`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivo`
--

DROP TABLE IF EXISTS `colectivo`;
CREATE TABLE `colectivo` (
  `id_Colectivo` int(11) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `capacidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `colectivo`
--

INSERT INTO `colectivo` (`id_Colectivo`, `matricula`, `estado`, `marca`, `modelo`, `capacidad`) VALUES
(1, 'ACA420', 1, 'Carmelita descalza', 'Acme', 6),
(2, 'ICKKCK', 1, 'Poneme un modelo', 'manteca Z', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

DROP TABLE IF EXISTS `horario`;
CREATE TABLE `horario` (
  `id_Horario` int(11) NOT NULL,
  `id_Ruta` int(11) NOT NULL,
  `horaSalida` time NOT NULL,
  `horaLlegada` time NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id_Horario`, `id_Ruta`, `horaSalida`, `horaLlegada`, `estado`) VALUES
(1, 1, '11:00:00', '11:00:00', 1),
(2, 1, '12:00:00', '16:00:00', 1),
(3, 2, '13:00:00', '14:00:00', 1),
(4, 1, '18:00:00', '22:00:00', 1),
(5, 2, '17:30:00', '19:30:00', 1),
(7, 3, '14:23:00', '14:24:00', 0),
(8, 1, '11:00:00', '17:40:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasaje`
--

DROP TABLE IF EXISTS `pasaje`;
CREATE TABLE `pasaje` (
  `id_Pasaje` int(11) NOT NULL,
  `id_Pasajero` int(11) NOT NULL,
  `id_Colectivo` int(11) NOT NULL,
  `id_Ruta` int(11) NOT NULL,
  `fechaViaje` date NOT NULL,
  `horaViaje` time NOT NULL,
  `asiento` int(11) NOT NULL,
  `precio` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasaje`
--

INSERT INTO `pasaje` (`id_Pasaje`, `id_Pasajero`, `id_Colectivo`, `id_Ruta`, `fechaViaje`, `horaViaje`, `asiento`, `precio`) VALUES
(2, 2, 2, 2, '2024-06-05', '01:05:00', 2, 1550),
(3, 3, 1, 3, '2024-06-06', '02:10:00', 3, 1600),
(4, 4, 2, 4, '2024-06-07', '03:15:00', 4, 1650),
(5, 5, 1, 1, '2024-06-08', '04:20:00', 5, 1700),
(6, 6, 2, 2, '2024-06-04', '05:25:00', 6, 1750),
(7, 7, 1, 3, '2024-06-05', '06:30:00', 7, 1800),
(8, 8, 2, 4, '2024-06-06', '07:35:00', 8, 1850),
(9, 9, 1, 1, '2024-06-07', '08:40:00', 9, 1900),
(10, 10, 2, 2, '2024-06-08', '09:45:00', 10, 1950);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

DROP TABLE IF EXISTS `pasajero`;
CREATE TABLE `pasajero` (
  `id_Pasajero` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `dni` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pasajero`
--

INSERT INTO `pasajero` (`id_Pasajero`, `nombre`, `apellido`, `dni`, `estado`, `correo`, `telefono`) VALUES
(1, 'Tadeo', 'Gomez', 12345601, 1, 'tadeo.gomez@example.com', 1234567890),
(2, 'Ailen', 'Rodriguez', 12345602, 1, 'ailen.rodriguez@example.com', 1234567891),
(3, 'Florencia', 'Martinez', 12345603, 1, 'florencia.martinez@example.com', 1234567892),
(4, 'Adan', 'Perez', 12345604, 1, 'adan.perez@example.com', 1234567893),
(5, 'Sofia', 'Lopez', 12345605, 1, 'sofia.lopez@example.com', 1234567894),
(6, 'Mateo', 'Sanchez', 12345606, 1, 'mateo.sanchez@example.com', 1234567895),
(7, 'Camila', 'Diaz', 12345607, 1, 'camila.diaz@example.com', 1234567896),
(8, 'Lautaro', 'Fernandez', 12345608, 1, 'lautaro.fernandez@example.com', 1234567897),
(9, 'Valentina', 'Gonzalez', 12345609, 1, 'valentina.gonzalez@example.com', 1234567898),
(10, 'Joaquin', 'Garcia', 12345610, 1, 'joaquin.garcia@example.com', 1234567899);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

DROP TABLE IF EXISTS `ruta`;
CREATE TABLE `ruta` (
  `id_Ruta` int(11) NOT NULL,
  `origen` varchar(30) NOT NULL,
  `destino` varchar(30) NOT NULL,
  `duracionEstimada` time NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`id_Ruta`, `origen`, `destino`, `duracionEstimada`, `estado`) VALUES
(1, 'Merlo', 'San Luis', '03:40:00', 1),
(2, 'San Luis', 'La Punta', '01:10:00', 1),
(3, 'Villa Mercedes', 'San Luis', '01:50:00', 1),
(4, 'Merlo', 'Villa Mercedes', '03:30:00', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  ADD PRIMARY KEY (`id_Colectivo`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id_Horario`),
  ADD KEY `id_Ruta` (`id_Ruta`);

--
-- Indices de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  ADD PRIMARY KEY (`id_Pasaje`),
  ADD KEY `id_Colectivo` (`id_Colectivo`),
  ADD KEY `id_Pasajero` (`id_Pasajero`),
  ADD KEY `id_Ruta` (`id_Ruta`);

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`id_Pasajero`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `telefono` (`telefono`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`id_Ruta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `colectivo`
--
ALTER TABLE `colectivo`
  MODIFY `id_Colectivo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_Horario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  MODIFY `id_Pasaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  MODIFY `id_Pasajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `id_Ruta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`id_Ruta`) REFERENCES `ruta` (`id_Ruta`);

--
-- Filtros para la tabla `pasaje`
--
ALTER TABLE `pasaje`
  ADD CONSTRAINT `pasaje_ibfk_1` FOREIGN KEY (`id_Colectivo`) REFERENCES `colectivo` (`id_Colectivo`),
  ADD CONSTRAINT `pasaje_ibfk_2` FOREIGN KEY (`id_Pasajero`) REFERENCES `pasajero` (`id_Pasajero`),
  ADD CONSTRAINT `pasaje_ibfk_3` FOREIGN KEY (`id_Ruta`) REFERENCES `ruta` (`id_Ruta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
