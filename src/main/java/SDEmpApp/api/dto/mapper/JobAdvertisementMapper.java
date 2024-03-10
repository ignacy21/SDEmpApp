package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementMapper {

    JobAdvertisement mapFromDTO(JobAdvertisementDTO jobAdvertisement);
    default JobAdvertisementDTO mapToDTO(JobAdvertisement jobAdvertisement, Company company) {
        Localization localization = jobAdvertisement.getLocalization();
        return JobAdvertisementDTO.builder()
                .province(localization.getProvinceName())
                .city(localization.getCityName())
                .languages(jobAdvertisement.getLanguages())
                .duties(jobAdvertisement.getDuties())
                .formOfWork(jobAdvertisement.getFormOfWork())
                .skillsNeeded(jobAdvertisement.getSkillsNeeded())
                .companyId(jobAdvertisement.getCompany().getCompanyId().toString())
                .build();
    }
}
