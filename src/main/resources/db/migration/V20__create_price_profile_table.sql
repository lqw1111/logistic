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


INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (1, 115, 111.00, 48.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (2, 115, 175.00, 50.00, 0.500, 1, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (3, 163, 80.00, 36.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (4, 163, 120.00, 23.00, 0.500, 1, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (5, 279, 70.00, 35.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (6, 279, 130.00, 37.00, 0.500, 1, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (7, 350, 85.50, 48.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (8, 350, 146.00, 38.00, 0.500, 1, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (9, 338, 87.00, 42.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (10, 338, 159.00, 42.00, 0.500, 1, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (11, 272, 88.00, 38.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (12, 272, 190.00, 42.00, 0.500, 1, 2.001, 20.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (13, 242, 133.00, 45.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (14, 92, 104.00, 51.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (15, 92, 147.00, 45.00, 0.500, 1, 2.001, 20.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (16, 45, 154.00, 70.00, 0.500, 1, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (17, 45, 188.00, 46.00, 0.500, 1, 2.001, 20.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (18, 279, 140.00, 50.00, 0.500, 2, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (19, 279, 260.00, 52.00, 0.500, 2, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (20, 350, 128.00, 54.00, 0.500, 2, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (21, 350, 248.00, 60.00, 0.500, 2, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (22, 338, 132.00, 55.00, 0.500, 2, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (23, 338, 226.00, 50.00, 0.500, 2, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (24, 272, 146.00, 49.00, 0.500, 2, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (25, 272, 270.00, 56.00, 0.500, 2, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (26, 242, 146.00, 63.00, 0.500, 2, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (27, 242, 260.00, 56.00, 0.500, 2, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (28, 115, 188.00, 90.00, 0.500, 3, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (29, 163, 125.00, 35.00, 0.500, 3, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (30, 279, 115.00, 48.00, 0.500, 3, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (31, 279, 120.00, 50.00, 0.500, 3, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (32, 350, 140.00, 55.00, 0.500, 3, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (33, 350, 150.00, 55.00, 0.500, 3, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (34, 338, 123.00, 49.00, 0.500, 3, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (35, 338, 125.00, 50.00, 0.500, 3, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (36, 272, 141.00, 57.00, 0.500, 3, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (37, 272, 162.00, 60.00, 0.500, 3, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (38, 242, 137.00, 60.00, 0.500, 3, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (39, 242, 145.00, 58.00, 0.500, 3, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (40, 92, 187.00, 75.00, 0.500, 3, 0.001, 22.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (41, 115, 195.00, 95.00, 0.500, 4, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (42, 279, 124.00, 55.00, 0.500, 4, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (43, 279, 150.00, 67.00, 0.500, 4, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (44, 350, 154.00, 63.00, 0.500, 4, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (45, 350, 166.00, 68.00, 0.500, 4, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (46, 338, 130.00, 53.00, 0.500, 4, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (47, 338, 136.00, 56.00, 0.500, 4, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (48, 272, 156.00, 66.00, 0.500, 4, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (49, 272, 181.00, 67.00, 0.500, 4, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (50, 242, 132.00, 57.00, 0.500, 4, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (51, 242, 160.00, 64.00, 0.500, 4, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (52, 92, 180.00, 68.00, 0.500, 4, 0.001, 0.250);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (53, 92, 195.00, 79.00, 0.500, 4, 0.251, 20.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (54, 115, 180.00, 5.00, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (55, 163, 117.00, 1.20, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (56, 279, 133.00, 2.40, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (57, 350, 189.00, 2.30, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (58, 338, 162.00, 2.80, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (59, 272, 200.00, 2.80, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (60, 242, 153.00, 2.60, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (61, 92, 125.00, 3.50, 0.050, 5, 0.001, 20.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (62, 45, 160.00, 3.50, 0.050, 5, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (63, 115, 240.00, 98.00, 0.500, 6, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (64, 163, 125.00, 45.00, 0.500, 6, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (65, 279, 146.00, 78.00, 0.500, 6, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (66, 279, 108.00, 55.00, 0.500, 6, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (67, 350, 137.00, 63.00, 0.500, 6, 0.001, 2.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (68, 350, 128.00, 57.00, 0.500, 6, 2.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (69, 338, 158.00, 70.00, 0.500, 6, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (70, 272, 180.00, 74.00, 0.500, 6, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (71, 242, 153.00, 68.00, 0.500, 6, 0.001, 30.000);
INSERT INTO price_profile (id, country_id, first_weight_price, continue_weight_price, minimum_weight, route_id, weight_range_low, weight_range_high) VALUES (72, 92, 91.50, 26.00, 0.500, 6, 0.001, 22.000);