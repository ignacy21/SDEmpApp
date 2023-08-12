package SDEmpApp.buisness.management;

import SDEmpApp.domain.Company;

import java.util.List;
import java.util.Map;

public class FileDataPreparationService {

    public List<Company> prepareCompaniesToCreateMap() {
        Map<String, List<String>> inputData = ReadAndMapFile.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.COMPANY);

        return null;
    }
}
