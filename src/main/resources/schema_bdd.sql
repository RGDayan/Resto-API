-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.10.2-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour db_restaurant
DROP DATABASE IF EXISTS `db_restaurant`;
CREATE DATABASE IF NOT EXISTS `db_restaurant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `db_restaurant`;

-- Listage de la structure de la table db_restaurant. boisson
DROP TABLE IF EXISTS `boisson`;
CREATE TABLE IF NOT EXISTS `boisson` (
  `id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL DEFAULT 'soft',
  `degre` float DEFAULT NULL,
  `produit_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produit_id` (`produit_id`),
  CONSTRAINT `FK_PRODUIT_ID_BOISSON` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table fille contenant les produits étant des boissons';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. carte
DROP TABLE IF EXISTS `carte`;
CREATE TABLE IF NOT EXISTS `carte` (
  `id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='Table correspondant aux différentes cartes disponibles dans le restaurant\r\nDéfinit par un type pouvant être midi, soir, boissons et plus...';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. carte_produit
DROP TABLE IF EXISTS `carte_produit`;
CREATE TABLE IF NOT EXISTS `carte_produit` (
  `carte_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  PRIMARY KEY (`carte_id`,`produit_id`),
  KEY `carte_id` (`carte_id`),
  KEY `produit_id` (`produit_id`),
  CONSTRAINT `FK_CARTE_ID_CARTE_PRODUIT` FOREIGN KEY (`carte_id`) REFERENCES `carte` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRODUIT_ID_CARTE_PRODUIT` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table relationnelle entre les cartes et les produits.\r\nRenseigne quel produit est disponible sur quel carte.\r\nRelation n:n';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. commande
DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL,
  `montant` double NOT NULL DEFAULT 0,
  `num_table` int(11) NOT NULL DEFAULT 0,
  `nb_personnes` int(11) NOT NULL DEFAULT 0,
  `statut_paiement` tinyint(4) NOT NULL DEFAULT 0,
  `service_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `FK_SERVICE_ID_COMMANDE` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table contenant les commandes d''un service du restaurant.';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. commande_produit
DROP TABLE IF EXISTS `commande_produit`;
CREATE TABLE IF NOT EXISTS `commande_produit` (
  `commande_id` int(11) NOT NULL,
  `produit_id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL DEFAULT 1,
  `statut` varchar(50) NOT NULL DEFAULT 'en attente',
  `heure_demande` time DEFAULT NULL,
  PRIMARY KEY (`commande_id`,`produit_id`),
  KEY `produit_id` (`produit_id`),
  KEY `commande_id` (`commande_id`),
  CONSTRAINT `FK_COMMANDE_ID_COMMANDE_PRODUIT` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRODUIT_ID_COMMANDE_PRODUIT` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table relationnelle entre les commandes et les produits.\r\nRenseigne combien de produits ont été ajoutés à une commande et l''état de la commande dans le temps (statut + heure_demande)\r\nRelation n:n';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. dessert
DROP TABLE IF EXISTS `dessert`;
CREATE TABLE IF NOT EXISTS `dessert` (
  `id` int(11) NOT NULL,
  `est_chaud` tinyint(4) NOT NULL DEFAULT 0,
  `est_flambee` tinyint(4) NOT NULL DEFAULT 0,
  `produit_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produit_id` (`produit_id`),
  CONSTRAINT `FK_PRODUIT_ID_DESSERT` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table fille contenant les produits étant des dessert';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. entree
DROP TABLE IF EXISTS `entree`;
CREATE TABLE IF NOT EXISTS `entree` (
  `id` int(11) NOT NULL,
  `est_chaud` tinyint(4) NOT NULL DEFAULT 0,
  `produit_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produit_id` (`produit_id`),
  CONSTRAINT `FK_PRODUIT_ID_ENTREE` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table enfant contenant les produits étant des entrées.';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. plat
DROP TABLE IF EXISTS `plat`;
CREATE TABLE IF NOT EXISTS `plat` (
  `id` int(11) NOT NULL,
  `cuisson` varchar(50) DEFAULT NULL,
  `produit_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRODUIT_ID_PLAT` (`produit_id`),
  CONSTRAINT `FK_PRODUIT_ID_PLAT` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table fille contenant les produits étant des plats principaux';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. produit
DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL,
  `libelle` varchar(50) NOT NULL DEFAULT '',
  `description` varchar(500) DEFAULT NULL,
  `prix` double NOT NULL DEFAULT 0,
  `est_actif` tinyint(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `libelle` (`libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Table contenant les produits disponibles sur les différentes cartes du restaurant';

-- Les données exportées n'étaient pas sélectionnées.

-- Listage de la structure de la table db_restaurant. service
DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `id` int(11) NOT NULL,
  `date_ouverture` datetime NOT NULL,
  `date_fermeture` datetime DEFAULT NULL,
  `chiffre_affaire` double DEFAULT NULL,
  `nb_commandes` int(11) DEFAULT 0,
  `statut` tinyint(4) NOT NULL DEFAULT 1,
  `carte_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_service` (`carte_id`) USING BTREE,
  CONSTRAINT `FK_CARTE_ID_ON_SERVICE` FOREIGN KEY (`carte_id`) REFERENCES `carte` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci COMMENT='Table contenant les différents services que le restaurant a ouvert puis fermé.\r\n';

-- Les données exportées n'étaient pas sélectionnées.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
