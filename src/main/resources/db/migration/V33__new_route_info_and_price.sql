DROP TABLE IF EXISTS route_info;
CREATE TABLE route_info
(
    id int NOT NULL,
    name varchar(20) NOT NULL,
    description longtext NULL
);

INSERT INTO route_info (id, name, description) VALUES (1, '一闪航空普货(包税)-QC', '平均时效 8 - 12 天');
INSERT INTO route_info (id, name, description) VALUES (2, '一闪航空特货(包税)-QC', '平均时效 15 - 25 天');
INSERT INTO route_info (id, name, description) VALUES (3, '一闪专线海运(包税)-QC', '平均时效 35 - 40 天');
INSERT INTO route_info (id, name, description) VALUES (4, '一闪敏感直邮(阳光清关)-QC', '平均时效 4 - 8 天');

DROP TABLE IF EXISTS price_profile;
CREATE TABLE price_profile
(
    id int NOT NULL,
    country_id int NOT NULL,
    first_weight_price decimal(10,2) NOT NULL COMMENT '人民币',
    continue_weight_price decimal(10,2) COMMENT '人民币',
    minimum_weight decimal(10,3) NOT NULL COMMENT 'KG',
    route_id int NOT NULL,
    weight_range_low decimal(10,3) NOT NULL COMMENT 'KG',
    weight_range_high decimal(10,3) NOT NULL COMMENT 'KG'
);

INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (1, 45, 60.00, 50.00, 1, 1, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (2, 45, 75.00, 65.00, 1, 2, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (3, 45, 30.00, 20.00, 1, 3, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (4, 45, 320.00, 110.00, 1, 4, 0.001, 30.000);
