CREATE TABLE company
(
    company_id              SERIAL                                                                              NOT NULL,
    name                    VARCHAR(32)                                                                         NOT NULL,
    localization            VARCHAR(32)                                                                         NOT NULL,
    description             TEXT                                                                                NOT NULL,
    languages               TEXT                                                                                NOT NULL,
    form_of_work            VARCHAR(32) CHECK (form_of_work IN ('HYBRYD', 'IN_PLACE', 'REMOTE', 'FIT')),
    PRIMARY KEY (company_id)
);