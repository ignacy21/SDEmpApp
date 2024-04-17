CREATE TABLE user_role
(
    user_id_fk INT NOT NULL,
    role_id_fk INT NOT NULL,
    PRIMARY KEY (user_id_fk, role_id_fk),
    CONSTRAINT fk_SDEmpApp_user_role_user
        FOREIGN KEY (user_id_fk)
            REFERENCES SDEmpApp_user (user_id),
    CONSTRAINT fk_SDEmpApp_user_role_role
        FOREIGN KEY (role_id_fk)
            REFERENCES SDEmpApp_role (role_id)
);