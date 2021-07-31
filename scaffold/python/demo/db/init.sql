GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;

CREATE USER 'cosmo_bdp'@'%' IDENTIFIED BY '123456';

CREATE DATABASE IF NOT EXISTS cosmo_bdp DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;
GRANT ALL ON cosmo_bdp.* TO 'cosmo_bdp'@'%' WITH GRANT OPTION;

CREATE TABLE cosmo_bdp.package_info(
    id int(5) NOT NULL AUTO_INCREMENT,
    package_name varchar(100) NOT NULL,  
    package_version varchar(20),
    os_version varchar(20),
    compression_suffix varchar(20),
    PRIMARY KEY (`id`)
)ENGINE=InnoDB;

INSERT INTO cosmo_bdp.package_info VALUES (1,'prometheus','2.27.1','linux-amd64','tar.gz');
INSERT INTO cosmo_bdp.package_info VALUES (2,'grafana','7.5.4','linux-amd64','tar.gz');