package _else;

import java.util.Random;

public class PrepareExampleDataForDatabase {

    public static void main(String[] args) {
//        createCompanies(100);
        createCandidate(500);
    }
    private static void createCompanies(int howMany) {
        for (int i = 0; i < howMany; i++) {
            System.out.printf("('company_%s', 'loc_%s', 'we are the best and top %s!!!'),%n", i, i, i);
        }
    }
//    candidate_id, name, surname, student_non_student, phone, email, linkedin, git, cv, languages, skills,
//    b2b_normal_fit, form_of_work, experience, about_me, employed_unemployed
    private static void createCandidate(int howMany) {

        for (int i = 0; i < howMany; i++) {
            StringBuilder telNumber = new StringBuilder();
            String languages = "[C++, C#, .NET]";
            String b2bNormalFit = "B2B";
            String formOfWork = "HYBRID";
            String employed_unemployed = "EMPLOYED";
            String studentNonStudent = "STUDENT";
            String experience = "0 >= 1";


            telNumber.append("+00 ");
            Random random = new Random();
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    telNumber.append(" ");
                }
                telNumber.append(random.nextInt(9));
            }
            if (i % 4 == 0) {
                languages = "[Java, JavaScript, REACT]";
                formOfWork = "FIT";
                experience = "1 > 2";
            } else if (i % 3 == 0) {
                languages = "[Python, Angular]";
                b2bNormalFit = "FIT";
                formOfWork = "REMOTE";
                experience = "2 > 5";
            } else if (i % 2 == 0) {
                languages = "everything";
                studentNonStudent = "NON STUDENT";
                b2bNormalFit = "NORMAL";
                formOfWork = "IN_PLACE";
                employed_unemployed = "UNEMPLOYED";
                experience = "5 > ";
            }
            System.out.printf("('name_%s', " +
                    "'surname_%s', " +
                    "'%s', " +
                    "'%s', " +
                    "'email_%s', " +
                    "'linkedin_link_%s', " +
                    "'git_link%s', " +
                    "'cv', " +
                    "'[some languages]', " +
                    "'%s,' " +
                    "'%s', " +
                    "'%s', " +
                    "'%s'" +
                    "'I am the top %s ranked human being!!!', " +
                    "'%s'),%n", i, i, studentNonStudent, telNumber, i, i, i, languages, b2bNormalFit, formOfWork, experience, i, employed_unemployed);
        }
    }



}
