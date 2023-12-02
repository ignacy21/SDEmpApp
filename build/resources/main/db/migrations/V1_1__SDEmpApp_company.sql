CREATE TABLE company
(
    company_id              SERIAL              NOT NULL,
    name                    VARCHAR(32)         NOT NULL,
    localization_id         INT                 NOT NULL,
    description             TEXT                NOT NULL,
    PRIMARY KEY (company_id),
    CONSTRAINT fk_company_localization
            FOREIGN KEY (localization_id)
                REFERENCES localization (localization_id)
);