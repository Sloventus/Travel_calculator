
CREATE TABLE classifiers (
  id BIGINT NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_classifiers_title ON classifiers(title);



CREATE TABLE classifier_values (
  id BIGINT NOT NULL AUTO_INCREMENT,
  classifier_id BIGINT NOT NULL,
  ic VARCHAR(200) NOT NULL,
  description VARCHAR(500) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE classifier_values
ADD FOREIGN KEY (classifier_id) REFERENCES classifiers(id);

CREATE UNIQUE INDEX ix_classifier_values_ic
ON classifier_values(ic);



CREATE TABLE IF NOT EXISTS `country_default_day_rate` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `country_ic` VARCHAR(200) NOT NULL,
  `default_day_rate` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE UNIQUE INDEX `ix_country_default_day_rate_country_ic`
ON `country_default_day_rate` (`country_ic`);


CREATE TABLE IF NOT EXISTS age_coefficient (
  id BIGINT NOT NULL AUTO_INCREMENT,
  age_from int NOT NULL,
  age_to int NOT NULL,
  coefficient DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
);


create table if not exists MEDICAL_RISK_LIMIT_LEVEL
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    MEDICAL_RISK_LIMIT_LEVEL_IC VARCHAR(200) NOT NULL,
    COEFFICIENT DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (id)
);


create table if not exists person_data
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    person_code VARCHAR(200) NOT NULL,
    birth_date DATE NOT NULL,
    PRIMARY KEY (id)
);