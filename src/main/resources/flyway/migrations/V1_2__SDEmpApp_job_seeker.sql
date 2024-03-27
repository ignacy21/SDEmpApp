CREATE TABLE job_seeker
(
    job_seeker_id       SERIAL                                                                      NOT NULL,
    name                VARCHAR(32)                                                                 NOT NULL,
    surname             VARCHAR(32)                                                                 NOT NULL,
    username            VARCHAR(100) GENERATED ALWAYS AS (name || ';' || surname) STORED,
    is_student          BOOLEAN                                                                     NOT NULL,
    phone               VARCHAR(32),
    email               VARCHAR(32)                                                                 NOT NULL,
    linkedin            VARCHAR(128),
    git                 VARCHAR(128),
    cv                  TEXT                                                                        NOT NULL,
    languages           TEXT                                                                        NOT NULL,
    skills              TEXT                                                                        NOT NULL,
    forms_of_employment TEXT                                                                        NOT NULL,
    forms_of_work       TEXT                                                                        NOT NULL,
    experience          VARCHAR(32) CHECK (experience IN ('0', '0 >= 1', '1 > 2', '2 > 5', '5 > ')) NOT NULL,
    about_me            TEXT,
    is_employed         BOOLEAN                                                                     NOT NULL,
    looking_for_job     BOOLEAN                                                                     NOT NULL,
    localization_id     INT                                                                         NOT NULL,
    PRIMARY KEY (job_seeker_id),
    CONSTRAINT fk_job_seeker_localization
        FOREIGN KEY (localization_id)
            REFERENCES localization (localization_id),
    CONSTRAINT unique_job_seeker_phone
        UNIQUE (phone),
    CONSTRAINT unique_job_seeker_email
        UNIQUE (email)
);