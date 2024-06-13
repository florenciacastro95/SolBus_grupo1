-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2024 a las 01:57:44
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `solbusdb`
--
CREATE DATABASE IF NOT EXISTS `solbusdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `solbusdb`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colectivo`
--

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
(1, 'AA123BB', 1, 'Mercedes-Benz', 'OF 1621', 40),
(2, 'CC456DD', 1, 'Scania', 'K310', 50),
(3, 'EE789FF', 1, 'Volvo', 'B9TL', 55),
(4, 'HH325II', 1, 'Marcopolo', 'Paradiso 100', 48);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

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
(9, 1, '08:00:00', '12:00:00', 1),
(10, 1, '13:00:00', '16:00:00', 1),
(11, 1, '14:00:00', '17:30:00', 1),
(12, 1, '16:00:00', '19:40:00', 1),
(13, 1, '20:00:00', '23:00:00', 1),
(14, 2, '10:00:00', '13:00:00', 1),
(15, 2, '14:00:00', '17:00:00', 1),
(16, 2, '20:00:00', '23:00:00', 1),
(17, 2, '23:30:00', '02:00:00', 1),
(18, 3, '09:00:00', '11:00:00', 1),
(19, 3, '13:00:00', '14:45:00', 1),
(20, 3, '17:00:00', '19:00:00', 1),
(21, 3, '20:00:00', '21:30:00', 1),
(22, 3, '23:00:00', '01:00:00', 1),
(23, 4, '10:00:00', '12:00:00', 1),
(24, 4, '14:00:00', '15:30:00', 1),
(25, 4, '17:00:00', '18:00:00', 1),
(26, 5, '09:00:00', '21:00:00', 1),
(27, 5, '10:00:00', '22:00:00', 1),
(28, 5, '17:00:00', '05:30:00', 1),
(29, 5, '22:00:00', '10:40:00', 1),
(30, 6, '09:00:00', '20:40:00', 1),
(31, 6, '11:00:00', '23:00:00', 1),
(32, 6, '13:00:00', '01:40:00', 1),
(33, 6, '18:00:00', '05:40:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasaje`
--

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
(1, 1, 1, 1, '2024-06-14', '08:00:00', 15, 7480),
(2, 3, 1, 1, '2024-06-12', '08:00:00', 14, 7480),
(3, 3, 1, 1, '2024-06-12', '08:00:00', 24, 7480),
(4, 4, 1, 1, '2024-06-15', '08:00:00', 10, 7480),
(5, 4, 1, 1, '2024-06-15', '08:00:00', 21, 7480),
(6, 2, 1, 1, '2024-06-15', '08:00:00', 23, 7480),
(7, 2, 1, 1, '2024-06-13', '08:00:00', 16, 7480),
(8, 2, 1, 1, '2024-06-13', '08:00:00', 10, 7480),
(9, 2, 1, 1, '2024-06-13', '08:00:00', 22, 7480),
(10, 2, 1, 1, '2024-06-18', '08:00:00', 6, 7480),
(11, 2, 1, 1, '2024-06-12', '08:00:00', 12, 7480),
(12, 2, 1, 1, '2024-06-12', '08:00:00', 1, 7480),
(13, 2, 1, 1, '2024-06-15', '08:00:00', 4, 7480),
(14, 2, 1, 1, '2024-06-20', '08:00:00', 11, 7480),
(16, 3, 1, 1, '2024-06-13', '08:00:00', 11, 7480);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajero`
--

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
(1, 'Florencia', 'Castro', 38749316, 1, 'fmcastro95@gmail.com', 123456789),
(2, 'Ailen', 'Amieva', 36227887, 1, 'amievalopezailen@gmail.com', 98765432),
(3, 'Tadeo', 'Wotoszyn', 44954721, 1, 'tadeo.woto@gmail.com', 234567890),
(4, 'Adan', 'Coronel', 37908002, 1, 'adanjhoel@gmail.com', 987654321),
(5, 'Juan Jose', 'Saez', 30342123, 1, 'jjsaez@ulp.edu.ar', 2147483647),
(8, 'Luis', 'Mercado', 12432567, 1, 'lmercado@ulp.edu.ar', 266432585),
(9, 'Pedrito', 'Sanchez', 23243212, 0, '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

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
(1, 'San Luis', 'Merlo', '03:40:00', 1),
(2, 'Merlo', 'San Luis', '03:40:00', 1),
(3, 'Villa Mercedes', 'San Luis', '01:40:00', 1),
(4, 'San Luis', 'Villa Mercedes', '01:40:00', 1),
(5, 'San Luis', 'Buenos Aires', '11:00:00', 1),
(6, 'Buenos Aires', 'San Luis', '11:00:00', 1);

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
  ADD UNIQUE KEY `uk_pasaje_asiento_unico_por_viaje` (`id_Colectivo`,`id_Ruta`,`fechaViaje`,`horaViaje`,`asiento`),
  ADD KEY `id_Colectivo` (`id_Colectivo`),
  ADD KEY `id_Pasajero` (`id_Pasajero`),
  ADD KEY `id_Ruta` (`id_Ruta`);

--
-- Indices de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  ADD PRIMARY KEY (`id_Pasajero`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD UNIQUE KEY `telefono` (`telefono`);

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
  MODIFY `id_Colectivo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_Horario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `pasaje`
--
ALTER TABLE `pasaje`
  MODIFY `id_Pasaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `pasajero`
--
ALTER TABLE `pasajero`
  MODIFY `id_Pasajero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `id_Ruta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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
