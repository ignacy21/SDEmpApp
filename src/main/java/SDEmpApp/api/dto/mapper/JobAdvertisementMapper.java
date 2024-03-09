package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobAdvertisementMapper {

    default JobAdvertisement mapFromDTO(JobAdvertisementDTO jobAdvertisement) {
        return JobAdvertisement.builder()
                .localization(Localization.builder()
                        .provinceName(jobAdvertisement.getProvince())
                        .cityName(jobAdvertisement.getCity())
                        .build())
                .languages(jobAdvertisement.getLanguages())
                .duties(jobAdvertisement.getDuties())
                .formOfWork(jobAdvertisement.getFormOfWork())
                .skillsNeeded(jobAdvertisement.getSkillsNeeded())
                .build();
    }
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
