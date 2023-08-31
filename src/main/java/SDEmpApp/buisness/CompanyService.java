package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.buisness.management.Keys;
import SDEmpApp.buisness.management.ReadAndPrepareFileService;
import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final ReadAndPrepareFileService fileService;
    private final CompanyDAO companyDAO;

    public List<Company> createCompanies() {
        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.COMPANY);

        List<Company> companyList = inputData.stream()
                .map(line -> line.split(";"))
                .map(CompanyService::putTogetherCompany)
                .toList();
        companyList.forEach(companyDAO::createCompany);
        return companyList;
    }



    private static Company putTogetherCompany(String[] companyData) {
        return Company.builder()
                .name(companyData[0])
                .localization(companyData[1])
                .description(companyData[2])
                .build();
    }
}
