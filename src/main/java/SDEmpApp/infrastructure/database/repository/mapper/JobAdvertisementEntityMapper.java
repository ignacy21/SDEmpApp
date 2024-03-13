package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementEntityMapper {

    default JobAdvertisementEntity mapToEntity(JobAdvertisement jobAdvertisement) {
        if (jobAdvertisement == null) {
            return null;
        }

        JobAdvertisementEntity.JobAdvertisementEntityBuilder jobAdvertisementEntity = JobAdvertisementEntity.builder();

        jobAdvertisementEntity.jobAdvertisementId(jobAdvertisement.getJobAdvertisementId());
        jobAdvertisementEntity.languages(jobAdvertisement.getLanguages());
        jobAdvertisementEntity.skillsNeeded(jobAdvertisement.getSkillsNeeded());
        jobAdvertisementEntity.duties(jobAdvertisement.getDuties());
        jobAdvertisementEntity.formOfWork(jobAdvertisement.getFormOfWork());
        jobAdvertisementEntity.localizationId(jobAdvertisement.getLocalization().getLocalizationId());

        return jobAdvertisementEntity.build();
    }


    default JobAdvertisement mapFromEntity(JobAdvertisementEntity jobAdvertisementEntity) {
        if (jobAdvertisementEntity == null) {
            return null;
        }

        JobAdvertisement.JobAdvertisementBuilder jobAdvertisement = JobAdvertisement.builder();

        jobAdvertisement.jobAdvertisementId(jobAdvertisementEntity.getJobAdvertisementId());
        jobAdvertisement.languages(jobAdvertisementEntity.getLanguages());
        jobAdvertisement.skillsNeeded(jobAdvertisementEntity.getSkillsNeeded());
        jobAdvertisement.duties(jobAdvertisementEntity.getDuties());
        jobAdvertisement.formOfWork(jobAdvertisementEntity.getFormOfWork());
        var companyId = jobAdvertisementEntity.getCompany().getCompanyId();
        jobAdvertisement.company(Company.builder()
                .companyId(companyId)
                .build());
        jobAdvertisement.localization(Localization.builder()
                        .localizationId(jobAdvertisementEntity.getLocalizationId())
                .build());

        return jobAdvertisement.build();
    }
}
