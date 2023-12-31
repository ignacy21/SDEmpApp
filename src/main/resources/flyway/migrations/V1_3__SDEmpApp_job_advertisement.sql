CREATE TABLE job_advertisement
(
    job_advertisement_id    SERIAL                                                                              NOT NULL,
    localization            TEXT                                                                                NOT NULL,
    languages               TEXT                                                                                NOT NULL,
    skills                  TEXT                                                                                NOT NULL,
    duties                  TEXT                                                                                NOT NULL,
    form_of_work            VARCHAR(32) CHECK (form_of_work IN ('HYBRID', 'IN_PLACE', 'REMOTE', 'FIT'))         NOT NULL,
    company_id              INT                                                                                 NOT NULL,
    PRIMARY KEY (job_advertisement_id),
    CONSTRAINT fk_job_advertisement_company
        FOREIGN KEY (company_id)
            REFERENCES company (company_id)


);