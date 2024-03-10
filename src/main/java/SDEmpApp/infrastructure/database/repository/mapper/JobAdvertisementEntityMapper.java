package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementEntityMapper {

    default JobAdvertisementEntity mapToEntity(JobAdvertisement jobAdvertisement) {
        if ( jobAdvertisement == null ) {
            return null;
        }

        JobAdvertisementEntity.JobAdvertisementEntityBuilder jobAdvertisementEntity = JobAdvertisementEntity.builder();

        jobAdvertisementEntity.jobAdvertisementId( jobAdvertisement.getJobAdvertisementId() );
        jobAdvertisementEntity.languages( jobAdvertisement.getLanguages() );
        jobAdvertisementEntity.skillsNeeded( jobAdvertisement.getSkillsNeeded() );
        jobAdvertisementEntity.duties( jobAdvertisement.getDuties() );
        jobAdvertisementEntity.formOfWork( jobAdvertisement.getFormOfWork() );
        jobAdvertisementEntity.localizationId(jobAdvertisement.getLocalization().getLocalizationId());

        return jobAdvertisementEntity.build();
    }


    JobAdvertisement mapFromEntity(JobAdvertisementEntity jobAdvertisementEntity);
}
