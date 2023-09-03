package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.buisness.management.Keys;
import SDEmpApp.buisness.management.ReadAndPrepareFileService;
import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CompanyService {

    private final ReadAndPrepareFileService fileService;
    private final CompanyDAO companyDAO;
    private final JobAdvertisementDAO jobAdvertisementDAO;

    public List<Company> createCompanies() {
        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.COMPANY);

        List<Company> companyList = inputData.stream()
                .map(line -> line.split(";"))
                .map(CompanyService::putTogetherCompany)
                .toList();
        companyList.forEach(companyDAO::createCompany);
        return companyList;
    }

    public List<String> findCompaniesSearches() {
        return fileService.getData(Keys.MainCommand.FIND, Keys.SecondCommand.COMPANY);

    }

    public List<List<Company>> findCompanies(String pattern) {
        List<List<Company>> resultList = new ArrayList<>();
        List<String> companiesSearches = findCompaniesSearches();
        for (String companySearch : companiesSearches) {
            Set<Company> searchCompanies = new HashSet<>();
            Map<Keys.FilteringKeyLetters, List<String>> neededDataToFindCompany = new HashMap<>();

            String[] KeysSeparatedFromContent = companySearch.split(";");
            for (String s : KeysSeparatedFromContent) {
                String[] split1 = s.split(":");
                String s1 = split1[1];
                List<String> list = Arrays.stream(s1.split(",")).toList();
                neededDataToFindCompany.put(Keys.FilteringKeyLetters.valueOf(split1[0]), list);
            }
            if (neededDataToFindCompany.size() == 1) {
                resultList.add(List.of(companyDAO.findCompanyByName()));
            } else {
                for (Map.Entry<Keys.FilteringKeyLetters, List<String>> filteringKeyLettersListEntry : neededDataToFindCompany.entrySet()) {
                    Keys.FilteringKeyLetters key = filteringKeyLettersListEntry.getKey();
                    if (key.equals(Keys.FilteringKeyLetters.Loc)) {
                        searchCompanies.addAll(jobAdvertisementDAO.findCompanyByLocalization());
                    }
                    if (key.equals(Keys.FilteringKeyLetters.Sk)) {
                        searchCompanies.addAll(jobAdvertisementDAO.findCompanyBySkillsNeeded());
                    }
                    if (key.equals(Keys.FilteringKeyLetters.W)) {
                        searchCompanies.addAll(jobAdvertisementDAO.findCompanyByFormOfWork());
                    }
                }
            }
            resultList.add(searchCompanies.stream().toList());
        }
        return resultList;
    }


    private static Company putTogetherCompany(String[] companyData) {
        return Company.builder()
                .name(companyData[0])
                .localization(companyData[1])
                .description(companyData[2])
                .build();
    }
}
