-- create database java_web_test;
-- use java_web_test;

drop table if exists `product_type`;
CREATE TABLE `product_type` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`product_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

drop table if exists `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_type_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `brand` varchar(100) DEFAULT NULL,
  `madein` varchar(100) DEFAULT NULL,
  `price` double(9,2) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `property`
--
drop table if exists `property`;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(255) NOT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `propertyType` varchar(255) NOT NULL,
  `forAndBehalfOfOwner` varchar(255) DEFAULT NULL,
  `nameOfAuthorizedPerson` varchar(255) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `purchasePrice` double(9,2) DEFAULT NULL,
  `stampDuty` double(9,2) DEFAULT NULL,
  `legalCosts` double(9,2) DEFAULT NULL,
  `additionalExpenses` double(9,2) DEFAULT NULL,
  `originalBuildingCost` double(9,2) DEFAULT NULL,
  `constructionStart` timestamp NULL,
  `constructionEnd` timestamp NULL,
  `dateOfContractExchange` timestamp NULL,
  `dateSettlement` timestamp NULL,
  `firstLeaseDate` timestamp NULL,
  `firstTaxableEndDate` timestamp NULL,
  `createdDate` timestamp NULL,
  `updatedDate` timestamp NULL,
  `createdBy` varchar(255) NULL,
  `updatedBy` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
