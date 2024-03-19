COMPANY:
    N   - name
    Loc - localization
    Sk  - skills
    W   - form of work (HYBRID, IN_PLACE, REMOTE, FIT)

CANDIDATE:
    N   - name
    S   - status (STUDENT, NON_STUDENT)
    Loc - localization
    Lan - language
    Ex  - experience
    Sk  - skills
    W   - form of work (HYBRID, IN_PLACE, REMOTE, FIT)
    E   - is_working (EMPLOYED, UN_EMPLOYED)

JobSeeker

[//]: # (-- username findByNameAndSurname)
[//]: # (-- is _student)
[//]: # (-- languages findByLanguages)
[//]: # (-- languages findBySpecifiedLanguages)
[//]: # (-- skills findBySkills)
[//]: # (-- skills findBySpecifiedSkills)
-- b2b_normal_fit findByB2bNormalFit
-- form_of_work findByFormOfWork
-- experience findByExperience
-- is_employed
-- looking_for_job

all in one (findByLocalization)
-- localization findByProvince
-- localization findByCity
-- localization findByProvinceAndCity

JobAdvertisement

[//]: # (-- languages findByLanguages)
[//]: # (-- languages findBySpecifiedLanguages)
[//]: # (-- skills findBySkills)
[//]: # (-- skills findBySpecifiedSkills)
[//]: # (-- form_of_work findByFormOfWork)

Company

[//]: # (-- name findByName)
[//]: # (-- localization findByLocalization)