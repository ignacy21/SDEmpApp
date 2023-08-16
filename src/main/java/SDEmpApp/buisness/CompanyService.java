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
    private final JobAdvertisementService jobAdvertisementService;

    public List<Company> createCompanies() {
        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.COMPANY);

        List<Company> list = inputData.stream()
                .map(line -> line.split(";"))
                .map(CompanyService::putTogetherCompany)
                .toList();
        list.forEach(companyDAO::createCompany);
        return createCompanies();
    }



    private static Company putTogetherCompany(String[] companyData) {
        return Company.builder()
                .name(companyData[0])
                .localization(companyData[1])
                .description(companyData[2])
                .build();
    }
}
