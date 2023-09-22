package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyDAO companyDAO;
    private final JobAdvertisementDAO jobAdvertisementDAO;

    public void createCompany(Company company) {
        companyDAO.createCompany(company);
    }

    public Company findIfCanLogin(String email, String password) {
        return companyDAO.findCompanyByEmailAndPassword(email, password);
    }
//
//    public List<List<Company>> findCompanies() {
//        List<List<Company>> resultList = new ArrayList<>();
//        List<String> companiesSearches = findCompaniesSearches();
//        for (String companySearch : companiesSearches) {
//            Set<Company> searchCompanies = new HashSet<>();
//            Map<FilteringKeyLetters, String> neededDataToFindCompany = new HashMap<>();
//
//            String[] KeysAndValuesSeparate = companySearch.split(";");
//            for (String oneFindPattern : KeysAndValuesSeparate) {
//                String[] splitKeyAndSearch = oneFindPattern.split(":");
//                neededDataToFindCompany.put(FilteringKeyLetters.valueOf(splitKeyAndSearch[0]), splitKeyAndSearch[1]);
//            }
//            if (neededDataToFindCompany.size() == 1 && neededDataToFindCompany.containsKey(FilteringKeyLetters.N)) {
//                resultList.add(companyDAO.findCompanyByName(neededDataToFindCompany.get(FilteringKeyLetters.N)));
//            } else {
//                for (Map.Entry<FilteringKeyLetters, String> filteringKeyLettersListEntry : neededDataToFindCompany.entrySet()) {
//                    FilteringKeyLetters key = filteringKeyLettersListEntry.getKey();
//                    String value = filteringKeyLettersListEntry.getValue();
//
//                    if (key.equals(FilteringKeyLetters.Loc)) {
//                        Localization localization = Localization.builder()
//                                .provinceName(value)
//                                .cityName(value)
//                                .build();
//                        searchCompanies.addAll(jobAdvertisementDAO.findCompanyByLocalization(localization));
//                    }
//                    if (key.equals(FilteringKeyLetters.Sk)) {
//                        List<Skill> skillList = Arrays.stream(value.split(","))
//                                .map(skill -> Skill.builder().name(skill).build())
//                                .toList();
//                        searchCompanies.addAll(jobAdvertisementDAO.findCompanyBySkillsNeeded(skillList));
//                    }
//                    if (key.equals(FilteringKeyLetters.W)) {
//                        searchCompanies.addAll(jobAdvertisementDAO.findCompanyByFormOfWork(value));
//                    }
//                }
//            }
//            resultList.add(searchCompanies.stream().toList());
//        }
//        return resultList;
//    }
//
//    public List<String> findCompaniesSearches() {
//        return fileService.getData(Keys.MainCommand.FIND, Keys.SecondCommand.COMPANY);
//
//    }
//
//
//    private static Company putTogetherCompany(String[] companyData) {
//        return Company.builder()
//                .name(companyData[0])
//                .localization(Localization.builder()
//                        .provinceName(companyData[0])
//                        .cityName(companyData[0])
//                        .build())
//                .description(companyData[2])
//                .build();
//    }
}
