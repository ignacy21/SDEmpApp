CREATE TABLE company
(
    company_id              SERIAL              NOT NULL,
    name                    VARCHAR(32)         NOT NULL,
    localization_id         INT                 NOT NULL,
    description             TEXT                NOT NULL,
    email                   VARCHAR(32)         NOT NULL,
    password                VARCHAR(32)         NOT NULL,
    PRIMARY KEY (company_id),
    CONSTRAINT fk_company_localization
            FOREIGN KEY (localization_id)
                REFERENCES localization (localization_id),
    CONSTRAINT unique_name_email_password
            UNIQUE (name, email, password)
);