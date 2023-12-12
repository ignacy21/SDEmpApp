CREATE TABLE job_advertisement
(
    job_advertisement_id SERIAL NOT NULL,
    localization         INT    NOT NULL,
    languages            TEXT   NOT NULL,
    duties               TEXT   NOT NULL,
    form_of_work         VARCHAR(32) CHECK (form_of_work IN ('HYBRID', 'IN_PLACE', 'REMOTE', 'FIT')),
    company_id           INT    NOT NULL,
    skill_level             VARCHAR(32) CHECK (form_of_work IN ('INTERNSHIP', 'JUNIOR', 'MID', 'SENIOR')),
    PRIMARY KEY (job_advertisement_id),
    CONSTRAINT fk_job_advertisement_company
        FOREIGN KEY (company_id)
            REFERENCES company (company_id),
    CONSTRAINT fk_job_advertisement_localization
        FOREIGN KEY (localization_id)
            REFERENCES localization (localization_id)


);