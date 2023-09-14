CREATE TABLE localization
(
    localization_id         SERIAL      NOT NULL,
    province_name           INT         NOT NULL,
    city_name               INT         NOT NULL,
    PRIMARY KEY (localization_id)
);