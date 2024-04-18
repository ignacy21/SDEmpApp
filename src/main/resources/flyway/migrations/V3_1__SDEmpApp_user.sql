CREATE TABLE SDEmpApp_user
(
    user_id  SERIAL      NOT NULL,
    email    VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    active   BOOLEAN     NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (email)
);