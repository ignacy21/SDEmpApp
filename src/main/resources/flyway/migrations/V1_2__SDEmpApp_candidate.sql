CREATE TABLE candidate
(
    candidate_id            SERIAL                                                                          NOT NULL,
    name                    VARCHAR(32)                                                                     NOT NULL,
    surname                 VARCHAR(32)                                                                     NOT NULL,
    student_non_student     VARCHAR(32) CHECK (student_non_student IN ('STUDENT', 'NON STUDENT'))           NOT NULL,
    phone                   VARCHAR(32),
    email                   VARCHAR(32)                                                                     NOT NULL,
    linkedin                VARCHAR(32),
    git                     VARCHAR(32),
    cv                      TEXT                                                                            NOT NULL,
    languages               TEXT                                                                            NOT NULL,
    skills                  TEXT                                                                            NOT NULL,
    b2b_normal_fit          VARCHAR(32) CHECK (b2b_normal_fit IN ('B2B', 'NORMAL', 'FIT'))                  NOT NULL,
    form_of_work            VARCHAR(32) CHECK (form_of_work IN ('HYBRID', 'IN_PLACE', 'REMOTE', 'FIT'))     NOT NULL,
    experience              VARCHAR(32) CHECK (experience IN ('0', '0 >= 1', '1 > 2', '2 > 5', '5 > '))     NOT NULL,
    about_me                TEXT,
    employed_unemployed     VARCHAR(32) CHECK (employed_unemployed IN ('EMPLOYED', 'UNEMPLOYED'))           NOT NULL,
    PRIMARY KEY (candidate_id)
);