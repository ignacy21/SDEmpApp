CREATE TABLE job_advertisement
(
    job_advertisement_id SERIAL                                                                             NOT NULL,
    localization_id      INT                                                                                NOT NULL,
    languages            TEXT                                                                               NOT NULL,
    skills               TEXT                                                                               NOT NULL,
    duties               TEXT                                                                               NOT NULL,
    form_of_work         VARCHAR(32) CHECK (form_of_work IN ('HYBRID', 'IN_PLACE', 'REMOTE', 'FIT'))        NOT NULL,
    experience_needed    VARCHAR(32) CHECK (experience_needed IN ('0', '0 >= 1', '1 > 2', '2 > 5', '5 > ')) NOT NULL,
    company_id           INT                                                                                NOT NULL,
    salary_from          NUMERIC(20, 2)                                                                     NOT NULL,
    salary_to            NUMERIC(20, 2),
    PRIMARY KEY (job_advertisement_id),
    CONSTRAINT fk_job_advertisement_company
        FOREIGN KEY (company_id)
            REFERENCES company (company_id),
    CONSTRAINT fk_job_advertisement_localization
        FOREIGN KEY (localization_id)
            REFERENCES localization (localization_id)


);