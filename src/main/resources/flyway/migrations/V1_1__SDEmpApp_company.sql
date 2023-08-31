CREATE TABLE company
(
    company_id              SERIAL                                                                              NOT NULL,
    name                    VARCHAR(32)                                                                         NOT NULL,
    localization            VARCHAR(32)                                                                         NOT NULL,
    description             TEXT                                                                                NOT NULL,
    PRIMARY KEY (company_id)
);