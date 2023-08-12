package SDEmpApp.buisness.management;

public interface Keys {

    enum MainCommand {
        CREATE,
        FIND
    }
    enum SecondCommand {
        COMPANY,
        CANDIDATE,
        JOB_ADVERTISEMENT
    }

    enum FilteringKeyLetters {
        N,                      // name
        S,                      // status (STUDENT, NON_STUDENT)
        Loc,                    // localization
        Lan,                    // language
        Ex,                     // experience
        Sk,                     // skills
        W,                      // form of work (HYBRID, IN_PLACE, REMOTE, FIT)
        E,                      // is_working (EMPLOYED, UN_EMPLOYED)
    }

}
