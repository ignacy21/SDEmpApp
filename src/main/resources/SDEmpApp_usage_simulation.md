CREATE -> COMPANY -> IGNACY'S COMPANY;Warsaw;The best company ever!!!

[//]: # (CREATE -> CANDIDATE -> ignacy;ignacowski;STUDENT;777 777 777;ignacy.ignacowski@gmail.com;linkedin_link;git_link;cv;every language;:&#41;;FIT;FIT;every;humble student;UNEMPLOYED)
CREATE -> CANDIDATE -> 1
CREATE -> CANDIDATE -> 2
CREATE -> CANDIDATE -> 3

CRETE -> JOB_ADVERTISEMENT -> Warsaw;English, Hungarian;Java, Hibernate, SQL, Spring Boot;working with relational databases;HYBRID;0


FIND -> COMPANY -> loc_1;language_1;skills;form of work
FIND -> COMPANY -> N:company_0
FIND -> COMPANY -> L:loc_1
FIND -> COMPANY -> L:loc_1;S:java, python;W:HYBRID

FIND -> CANDIDATE -> S:student;Lan:english;Sk:Java;W:HYBRID;Ex:0 >= 1

[//]: # ([FIND, [COMPANY {loc_1, language_1, skills, form of work}]])

