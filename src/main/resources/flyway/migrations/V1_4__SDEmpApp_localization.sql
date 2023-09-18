CREATE TABLE localization
(
    localization_id         SERIAL              NOT NULL,
    province_name           VARCHAR(50)         NOT NULL,
    city_name               VARCHAR(50)         NOT NULL,
    PRIMARY KEY (localization_id)
);