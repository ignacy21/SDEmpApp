CREATE TABLE job_seeker
(
    job_seeker_id   SERIAL                                                                      NOT NULL,
    name            VARCHAR(32)                                                                 NOT NULL,
    surname         VARCHAR(32)                                                                 NOT NULL,
    username        VARCHAR(100) GENERATED ALWAYS AS (name || ';' || surname) STORED,
    is_student      BOOLEAN                                                                     NOT NULL,
    phone           VARCHAR(32),
    email           VARCHAR(32)                                                                 NOT NULL,
    linkedin        VARCHAR(128),
    git             VARCHAR(128),
    cv              TEXT                                                                        NOT NULL,
    languages       TEXT                                                                        NOT NULL,
    skills          TEXT                                                                        NOT NULL,
    b2b_normal_fit  VARCHAR(32) CHECK (b2b_normal_fit IN ('B2B', 'NORMAL', 'FREELANCE', 'FIT')) NOT NULL,
    form_of_work    VARCHAR(32) CHECK (form_of_work IN ('HYBRID', 'IN_PLACE', 'REMOTE', 'FIT')) NOT NULL,
    experience      VARCHAR(32) CHECK (experience IN ('0', '0 >= 1', '1 > 2', '2 > 5', '5 > ')) NOT NULL,
    about_me        TEXT,
    is_employed     BOOLEAN                                                                     NOT NULL,
    looking_for_job BOOLEAN                                                                     NOT NULL,
    PRIMARY KEY (job_seeker_id),
    CONSTRAINT unique_job_seeker_phone
        UNIQUE (phone),
    CONSTRAINT unique_job_seeker_email
        UNIQUE (email)
);