-- create database java_web_test;
-- use java_web_test;

DROP table if exists `product_type`;
CREATE TABLE `product_type` (
  `product_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`product_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP table if exists `product`;
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
DROP table if exists `property`;
CREATE TABLE `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(255) NOT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `property_type` varchar(255) NOT NULL,
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
  `created_date` timestamp NULL,
  `updated_date` timestamp NULL,
  `created_by` varchar(255) NULL,
  `updated_by` varchar(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-------------------------------------------------------

DROP table if exists `BUILDING_GROUP`;
CREATE TABLE `BUILDING_GROUP` (
  `building_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `building_group` varchar(250) NOT NULL,
  `created_date` timestamp NULL,
  `updated_date` timestamp NULL,
  `created_by` varchar(255) NULL,
  `updated_by` varchar(255) NULL,
  PRIMARY KEY (`building_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP table if exists `BUILDING_SUB_GROUP`;
CREATE TABLE `BUILDING_SUB_GROUP` (
  `building_sub_group_id` int(11) NOT NULL AUTO_INCREMENT,
  `building_sub_group` varchar(250) NOT NULL,
  `building_group_id` int(11) NOT NULL,
  `created_date` timestamp NULL,
  `updated_date` timestamp NULL,
  `created_by` varchar(255) NULL,
  `updated_by` varchar(255) NULL,
  PRIMARY KEY (`building_sub_group_id`),
  INDEX par_ind (`building_group_id`),  
  CONSTRAINT fk_customer FOREIGN KEY (`building_group_id`)  
  REFERENCES BUILDING_GROUP(`building_group_id`)  
  ON DELETE CASCADE  
  ON UPDATE CASCADE  
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

DROP table if exists `BUILDING_TYPE`;
CREATE TABLE `BUILDING_TYPE` (
  `building_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `building_type` varchar(250) NOT NULL,
  `building_type_note` varchar(250) DEFAULT NULL,
  `building_sub_group_id` int(11) NOT NULL,
  `created_date` timestamp NULL,
  `updated_date` timestamp NULL,
  `created_by` varchar(255) NULL,
  `updated_by` varchar(255) NULL,
  PRIMARY KEY (`building_type_id`),
  INDEX par_ind (`building_sub_group_id`),  
  CONSTRAINT fk_customer FOREIGN KEY (`building_sub_group_id`)  
  REFERENCES BUILDING_SUB_GROUP(`building_sub_group_id`)  
  ON DELETE CASCADE  
  ON UPDATE CASCADE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

------------------------------------------------------------------
ALTER TABLE `property` AUTO_INCREMENT = 100;
ALTER TABLE `property` AUTO_INCREMENT = 100;
ALTER TABLE `property` AUTO_INCREMENT = 100;
ALTER TABLE `property` AUTO_INCREMENT = 100;
ALTER TABLE `property` AUTO_INCREMENT = 100;
ALTER TABLE `property` AUTO_INCREMENT = 100;

INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (1, 'Administration, Civic', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (2, 'Agricultural', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (3, 'Banks', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (4, 'Educational', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (5, 'Entertainment', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (6, 'Hospitals, Health', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (7, 'Hotels, Motels, Clubs', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (8, 'Industrial', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (9, 'Offices', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (10, 'Parking', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (11, 'Recereational', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (12, 'Religious', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (13, 'Residential', '2021-11-05 00:00:00', 'admin');
INSERT INTO BUILDING_GROUP (building_group_id, building_group, created_date, created_by) VALUES (14, 'Retail', '2021-11-05 00:00:00', 'admin');


INSERT INTO BUILDING_SUB_GROUP (building_sub_group_id, buildingGroup, createdDate, createdBy) VALUES (1, 'Administration, Civic', '2021-11-05 00:00:00', 'admin');
commit;