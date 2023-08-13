package SDEmpApp.buisness;

import SDEmpApp.buisness.management.Keys;
import SDEmpApp.buisness.management.ReadAndPrepareFileService;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;

import java.util.List;

public class JobAdvertisementService {

    private ReadAndPrepareFileService fileService;

    public List<JobAdvertisementEntity> prepareJobAdvertisementToCreateMap() {
        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.JOB_ADVERTISEMENT);
        return inputData.stream()
                .map(line -> line.split(";"))
                .map(this::createJobAdvertisement)
                .toList();
    }

    private JobAdvertisementEntity createJobAdvertisement(String[] strings) {
        return JobAdvertisementEntity.builder()
                .localization(strings[0])
                .languages(strings[1])
                .skillsNeeded(strings[2])
                .duties(strings[3])
                .formOfWork(strings[4])
//                .company()
//                TODO
                .build();
    }
}
