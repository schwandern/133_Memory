-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : dim. 07 mai 2023 à 14:51
-- Version du serveur : 10.3.38-MariaDB-cll-lve
-- Version de PHP : 8.1.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `reyx_133_G4_Admin`
--

-- --------------------------------------------------------

--
-- Structure de la table `t_user`
--

CREATE TABLE `t_user` (
  `PK_User` int(11) NOT NULL,
  `user` varchar(45) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `admin` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `t_user`
--

INSERT INTO `t_user` (`PK_User`, `user`, `password`, `admin`) VALUES
(36, 'silvin', '$2a$10$dmQsu6ovBil0BOTGLeQYRek4sGzPfUjyshVPozWVEZdrat34ireP2', 0),
(52, 'xav', '$2a$10$QAYIFHc.5VTE9Kgw0iPGj.J.W3GcsF9ZpkQM7cSsH6fPw04EkNi4a', 0),
(53, '123soleil@soleil.ch', '$2a$10$HgmcdEJTG6T6td8pmPSgUOmaGIQbqiYf4p3TnFB2O04DQ8hLEFNlq', 0),
(54, 'silvin02', '$2a$10$TVmV11yZkxN1oBTYcWrOBuxEAMdpyYs.T3KhsaIYos0LXXRy1aPg.', 0),
(60, 'reyx', '$2a$10$ticSdZUMpix7ctkbTbVkbesupinZjKrox9IM6m.9z/HvSdSnSOmO.', 0),
(61, 'Jacques Uzi', '$2a$10$lg/o010uXrS7XtG7Hc/zfut1YwnJrmJAn4ekDo9yPNDe8GQnYxHRO', 0),
(62, 'xavierboss', '$2a$10$T.JzDitaS2O8H.WE5Mhss.NumlsbYm.ITA.Eu.EpfP8J4nR0SmlWO', 0),
(63, 'laFranceAuxFrancais', '$2a$10$OQVQ/ufiwirW4mIL2uFutupXcapEWHYL542mjDXp.Rn.wkJhJOHcm', 0),
(67, 'Eva Lavétoncul', '$2a$10$OKIOrYn2k1c6OGxOQYNIWOkorbLjiSraYvgXaG42T7pBadvkTWlD6', 0),
(68, 'Gerard Menfaim', '$2a$10$7ZmESTQAKD4zss2dgi3sluVuOLpPIi3LTr/sHnf30K5W.bobe70De', 0),
(69, 'Jacques Celere', '$2a$10$yzObcglw4ZszNEfrkKvgVeEwCMa.2QQXbYDkz0Dhvj5JdhuL06Oj.', 0),
(70, 'Jean Bonbeur', '$2a$10$f0G7C8uWvP5ARZz57NGbU..Az06E5SBtTYg/S92KsJ3BvkqKf.Jz2', 0),
(75, 'Neo suchet', '$2a$10$0ORIpnM3hGtBeqIJtosrle4sd.ELf8hEJlGU8ysVSJEX4nJWeI0F.', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`PK_User`),
  ADD UNIQUE KEY `user` (`user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `t_user`
--
ALTER TABLE `t_user`
  MODIFY `PK_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
