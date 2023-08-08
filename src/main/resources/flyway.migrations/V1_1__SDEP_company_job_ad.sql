CREATE TABLE job_advertisement
(
    job_advertisement_id    SERIAL                                                                              NOT NULL,
    localization            VARCHAR(32)                                                                         NOT NULL,
    languages               TEXT                                                                                NOT NULL,
    skills_needed           TEXT                                                                                NOT NULL,
    duties                  TEXT                                                                                NOT NULL,
    form_of_work            VARCHAR(32) CHECK (form_of_work IN ('HYBRYD', 'IN_PLACE', 'REMOTE', 'FIT')),
    company_id              INT                                                                                 NOT NULL,
    PRIMARY KEY (job_advertisement_id),
    CONSTRAINT fk_job_advertisement_company
        FOREIGN KEY (company_id)
            REFERENCES company (company_id)


);