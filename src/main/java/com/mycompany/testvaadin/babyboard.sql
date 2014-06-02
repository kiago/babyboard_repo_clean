-- phpMyAdmin SQL Dump
-- version 3.3.9.2
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Lun 19 Mai 2014 à 14:56
-- Version du serveur: 5.5.9
-- Version de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `babyboard`
--

-- --------------------------------------------------------

--
-- Structure de la table `babies`
--

CREATE TABLE `babies` (
  `idBaby` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `age` date NOT NULL,
  `sex` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  PRIMARY KEY (`idBaby`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `babies`
--

INSERT INTO `babies` VALUES(1, 'Gaetan', '2002-02-14', 1, 'Rouaix');
INSERT INTO `babies` VALUES(2, 'Toscan Junior', '2017-00-00', 0, 'Vertanessian');

-- --------------------------------------------------------

--
-- Structure de la table `jonction`
--

CREATE TABLE `jonction` (
  `idJonction` int(10) NOT NULL,
  `idUser` int(10) NOT NULL,
  `idBaby` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `jonction`
--

INSERT INTO `jonction` VALUES(0, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `mainFacts`
--

CREATE TABLE `mainFacts` (
  `idFact` int(10) NOT NULL AUTO_INCREMENT,
  `idBaby` int(10) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`idFact`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `mainFacts`
--

INSERT INTO `mainFacts` VALUES(1, 0, 'Sieste', 'Le bébé fait la sieste depuis 14h');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `idUser` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `adress` varchar(200) NOT NULL,
  `zip` int(10) NOT NULL,
  `city` varchar(200) NOT NULL,
  `tel` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` VALUES(1, 'Geoffroy', 'Rouaix', 'toto', '57 rue michel ange', 75016, 'Paris', 618275025, 'Rouaix');