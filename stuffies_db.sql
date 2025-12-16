-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-12-2025 a las 12:47:23
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
-- Base de datos: `stuffies_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `cliente_direccion` varchar(255) DEFAULT NULL,
  `cliente_email` varchar(255) DEFAULT NULL,
  `cliente_nombre` varchar(255) DEFAULT NULL,
  `cliente_telefono` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `medio_pago` varchar(255) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `orders`
--

INSERT INTO `orders` (`id`, `cliente_direccion`, `cliente_email`, `cliente_nombre`, `cliente_telefono`, `estado`, `medio_pago`, `total`, `user_id`, `fecha_creacion`) VALUES
(1, 'aaaaaa, aaaaa', '', 'aaaaa', '+56982890551', 'PAGADO', 'WEB', 21980, NULL, NULL),
(2, 'aaaaa, sadasd', '', 'aaaa', '+56982890551', 'PAGADO', 'WEB', 55990, 1, NULL),
(3, '123123, asaasaa', '', 'aaaaa', '+56982890551', 'PAGADO', 'WEB', 10990, 1, NULL),
(4, '1asdas, asdasdas', '', 'aaaa', '+56982890551', 'PAGADO', 'WEB', 111980, 1, '2025-12-11 16:20:38.000000'),
(5, 'aaaaa, aaaaa', '', 'aaaaa', '+56982890551', 'PAGADO', 'WEB', 10990, 1, '2025-12-11 16:22:28.000000'),
(6, '123123, asdasasd', NULL, 'aaaa', '+56982890551', 'PAGADO', 'WEB', 55990, 1, '2025-12-11 16:24:26.000000'),
(7, 'a22312312, asdasdas', NULL, 'aaaa', '+56982890551', 'PAGADO', 'WEB', 55990, 1, '2025-12-11 16:25:31.000000'),
(8, 'aaaaa, aaaa', 'a@gmail.com', 'aaaa aaaa', '+56982890551', 'PAGADO', 'WEB', 10990, 1, '2025-12-11 16:30:40.000000'),
(9, 'hola123, la florida', 'hola@gmail.com', 'alfonso sutherland', '+56982890551', 'PAGADO', 'WEB', 111980, 2, '2025-12-11 16:34:33.000000'),
(10, 'aaaaA, aaaaaa', 'aaaaa@gmail.com', 'aaaa aaaa', '+56982890551', 'PAGADO', 'WEB', 77970, NULL, '2025-12-12 12:09:02.000000'),
(11, 'aaaaaa, aaaaaa', 'aaaa@gmail.com', 'aaaa aaaa', '+56982890551', 'PAGADO', 'WEB', 162960, NULL, '2025-12-12 12:50:39.000000'),
(12, 'aaaaaaa, aaaaaa', 'aaaa@gmail.com', 'aaaa aaaaa', '+56982890551', 'PAGADO', 'WEB', 25980, NULL, '2025-12-12 12:53:02.000000'),
(13, 'aaaaaa, aaa', 'aaaa@gmail.com', 'aaaaa aaaaa', '+56982890551', 'PAGADO', 'WEB', 109900, NULL, '2025-12-12 15:09:57.000000'),
(14, 'aaaaa, aaaaa', 'a@gmail.com', 'aaaa aaaa', '+56982890551', 'PAGADO', 'WEB', 111980, NULL, '2025-12-14 19:14:57.000000'),
(15, 'aaaaaa, aaaaa', 'a@gmail.com', 'aaaa aaaa', '+56982890551', 'PAGADO', 'WEB', 79980, NULL, '2025-12-14 19:16:18.000000'),
(16, 'Regimiento Cazadores 1222, aaaaa', 'aaaaaa@gmail.com', 'aaaa 1234', '+56982890551', 'PAGADO', 'WEB', 55990, 11, '2025-12-15 17:41:07.000000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order_items`
--

CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `talla` varchar(255) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `order_items`
--

INSERT INTO `order_items` (`id`, `cantidad`, `color`, `imagen`, `precio`, `talla`, `order_id`, `product_id`) VALUES
(1, 2, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600', 10990, 'M', 1, 3),
(2, 1, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600', 55990, 'S', 2, 3),
(3, 1, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_221c9cfc-6049-4eb1-b7ec-3b19bd755c48.png?v=1748653006&width=600', 10990, 'M', 3, 2),
(4, 2, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600', 55990, 'S', 4, 3),
(5, 1, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_221c9cfc-6049-4eb1-b7ec-3b19bd755c48.png?v=1748653006&width=600', 10990, 'M', 5, 2),
(6, 1, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600', 55990, 'S', 6, 3),
(7, 1, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600', 55990, 'S', 7, 3),
(8, 1, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_221c9cfc-6049-4eb1-b7ec-3b19bd755c48.png?v=1748653006&width=600', 10990, 'M', 8, 2),
(9, 2, 'Único', 'https://stuffiesconcept.com/cdn/shop/files/2_1c0d6df0-c713-49a3-b2bd-b07d19c392ee.png?v=1756936574&width=600', 55990, 'S', 9, 3),
(10, 1, NULL, NULL, 10990, 'M', 10, 2),
(11, 1, NULL, NULL, 10990, 'XL', 10, 2),
(12, 1, NULL, NULL, 55990, 'S', 10, 3),
(13, 1, NULL, NULL, 39990, 'S', 11, 1),
(14, 1, NULL, NULL, 10990, 'M', 11, 2),
(15, 2, NULL, NULL, 55990, 'XL', 11, 3),
(16, 1, NULL, NULL, 15990, 'Única', 12, 5),
(17, 1, NULL, NULL, 9990, 'Única', 12, 11),
(18, 10, NULL, NULL, 10990, 'XL', 13, 2),
(19, 1, NULL, NULL, 55990, 'S', 14, 3),
(20, 1, NULL, NULL, 55990, 'XL', 14, 3),
(21, 1, NULL, NULL, 39990, 'S', 15, 1),
(22, 1, NULL, NULL, 39990, 'XL', 15, 1),
(23, 1, NULL, NULL, 55990, 'S', 16, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `activo` bit(1) NOT NULL,
  `categoria` varchar(100) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  `nombre` varchar(150) NOT NULL,
  `precio` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `tallas` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `activo`, `categoria`, `descripcion`, `image_url`, `nombre`, `precio`, `stock`, `tallas`) VALUES
(1, b'1', 'polerones', 'Poleron Boxy Fit White Dice V2.', 'https://stuffiesconcept.com/cdn/shop/files/WhiteDice1.png?v=1753404231&width=600', 'Hoodie Boxy Fit White Dice V2', 39990, 197, 'S,M,L,XL'),
(2, b'1', 'poleras', 'La Star Player T-Shirt nace de la unión entre la nostalgia del fútbol clásico y la energía del streetwear actual.', 'https://stuffiesconcept.com/cdn/shop/files/1_594f01e1-55e5-4516-b0af-d2befc1aa113.png?v=1748653006&width=600', 'Star Player Blue Team T-Shirt', 10990, 109, 'M,L,XL'),
(3, b'1', 'polerones', 'Hoodie con cierre frontal y bolsillos.', 'https://stuffiesconcept.com/cdn/shop/files/1_8ee3f1b2-2f8a-45ba-bb78-a2f4ba49c4d5.png?v=1756936574&width=600', 'Stella Chroma Zip Hoodie', 55990, 220, 'S,M,L,XL'),
(4, b'1', 'poleras', 'Camiseta blanca corte boxy-slim.', 'https://stuffiesconcept.com/cdn/shop/files/3_0f38dc89-f9f8-4998-be22-b2e0122e8816.png?v=1756936601&width=600', 'Stella Boxy-Slim White Tee', 22990, 225, 'S,M,L,XL'),
(5, b'1', 'poleras', 'Polera boxy-slim fit negra', 'https://stuffiesconcept.com/cdn/shop/files/5.png?v=1756936590&width=493', 'Stella Boxy-Slim Black Tee', 15990, 149, NULL),
(6, b'1', 'polerones', 'Poleron Boxy Fit White Dice V2.', 'https://stuffiesconcept.com/cdn/shop/files/RedDice1.png?v=1753404319&width=600', 'Hoodie Boxy Fit Black Dice V2', 32990, 225, 'S,M,L,XL'),
(7, b'1', 'poleras', 'La Star Player T-Shirt nace de la unión entre la nostalgia del fútbol clásico y la energía del streetwear actual..', 'https://stuffiesconcept.com/cdn/shop/files/3_f5bf3ad8-c122-436f-8eee-1483a3f383da.png?v=1748652948&width=600', 'Star Player Black Team T-Shirt', 37990, 225, 'S,M,L,XL'),
(8, b'1', 'polerones', 'Poleron Boxy Fit Brown Dice V2.', 'https://stuffiesconcept.com/cdn/shop/files/PinkDice1.png?v=1753404299&width=600', 'Hoodie Boxy Fit Brown Dice V2', 35990, 225, 'S,M,L,XL'),
(9, b'1', 'pantalones', 'Jeans negro con calce relaxed.', 'https://i.postimg.cc/85CnPzS6/920c48b5-ab8b-486d-8681-74fd494c0b6e.avif', 'Pantalón Jeans Negro', 22990, 360, '38,40,42,44,46,48,50,52,54'),
(10, b'1', 'pantalones', 'Jogger gris, cintura elasticada y puño.', 'https://img.kwcdn.com/product/fancy/50c868f6-9264-465b-8e4f-01332ba99b8d.jpg?imageView2/2/w/800/q/70/format/avif', 'Pantalón Jogger Gris', 19990, 363, NULL),
(11, b'1', 'gorros', 'Beanie de punto, unisex, ideal para invierno.', 'https://img.kwcdn.com/product/fancy/109264d1-93cb-4d8a-af2f-a2e0056f21dc.jpg?imageView2/2/w/800/q/70/format/avif', 'Gorro Beanie Clásico', 9990, 142, NULL),
(14, b'1', 'gorros', 'https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcRUmv-t9SqP1ka5jaPZFxQUMDp1QKdzA7V1z6UypXVI0n11zF7B3QpBY6eGhnyMEqYks4WgZvuuOXXg4nKsDM2jBlT3D09NdaYL4j7Gz_rJmwuM_LWIFMRkCHt-TIPV4HtXB0mEn11j1I8&s=19', 'https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcRUmv-t9SqP1ka5jaPZFxQUMDp1QKdzA7V1z6UypXVI0n11zF7B3QpBY6eGhnyMEqYks4WgZvuuOXXg4nKsDM2jBlT3D09NdaYL4j7Gz_rJmwuM_LWIFMRkCHt-TIPV4HtXB0mEn11j1I8&s=19', 'kanye', 111111, 6, 'Única');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_variants`
--

CREATE TABLE `product_variants` (
  `id` bigint(20) NOT NULL,
  `stock` int(11) NOT NULL,
  `talla` varchar(255) NOT NULL,
  `product_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `product_variants`
--

INSERT INTO `product_variants` (`id`, `stock`, `talla`, `product_id`) VALUES
(1, 48, 'S', 1),
(2, 50, 'M', 1),
(3, 50, 'L', 1),
(4, 49, 'XL', 1),
(5, 39, 'M', 2),
(6, 40, 'L', 2),
(7, 30, 'XL', 2),
(8, 38, 'S', 3),
(9, 60, 'M', 3),
(10, 60, 'L', 3),
(11, 62, 'XL', 3),
(12, 40, 'S', 4),
(13, 60, 'M', 4),
(14, 60, 'L', 4),
(15, 65, 'XL', 4),
(16, 149, 'Única', 5),
(17, 40, 'S', 6),
(18, 60, 'M', 6),
(19, 60, 'L', 6),
(20, 65, 'XL', 6),
(21, 40, 'S', 7),
(22, 60, 'M', 7),
(23, 60, 'L', 7),
(24, 65, 'XL', 7),
(25, 40, 'S', 8),
(26, 60, 'M', 8),
(27, 60, 'L', 8),
(28, 65, 'XL', 8),
(29, 40, '38', 9),
(30, 40, '40', 9),
(31, 40, '42', 9),
(32, 40, '44', 9),
(33, 40, '46', 9),
(34, 40, '48', 9),
(35, 40, '50', 9),
(36, 40, '52', 9),
(37, 40, '54', 9),
(58, 142, 'Única', 11),
(59, 40, '38', 10),
(60, 40, '40', 10),
(61, 40, '42', 10),
(62, 40, '44', 10),
(63, 40, '46', 10),
(64, 40, '48', 10),
(65, 40, '50', 10),
(66, 40, '52', 10),
(67, 43, '54', 10),
(77, 6, 'Única', 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `rut` varchar(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `apellido`, `direccion`, `email`, `nombre`, `password`, `role`, `rut`, `username`) VALUES
(1, 'Stuffies', "Sin direccion", 'admin@stuffies.com', 'Administrador', '$2a$10$GnoSWF.3RWq0hju//2iokOs07eFBceDS2l2WvBYgDWkmvDAPizIcy', 'ROLE_ADMIN', '19310279-4', 'adminstuffies'),
(2, 'aaaa', 'asdasddasdasd', 'a@gmail.com', 'aaaa', '1234', 'ROLE_CLIENTE', '193102794', '1234'),
(11, 'ss', 'holaa', 'aa@gmail.com', 'alfonso1', '$2a$10$Yk9yDF3YCHTqzdVBxvpBQe8kM0pK6W.ORLluntBJ0R4bZeDHYFklm', 'ROLE_CLIENTE', '19310279-4', 'cliente');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`);

--
-- Indices de la tabla `order_items`
--
ALTER TABLE `order_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  ADD KEY `FKocimc7dtr037rh4ls4l95nlfi` (`product_id`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `product_variants`
--
ALTER TABLE `product_variants`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKosqitn4s405cynmhb87lkvuau` (`product_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `order_items`
--
ALTER TABLE `order_items`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `products`
--
ALTER TABLE `products`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `product_variants`
--
ALTER TABLE `product_variants`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `order_items`
--
ALTER TABLE `order_items`
  ADD CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `FKocimc7dtr037rh4ls4l95nlfi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Filtros para la tabla `product_variants`
--
ALTER TABLE `product_variants`
  ADD CONSTRAINT `FKosqitn4s405cynmhb87lkvuau` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
