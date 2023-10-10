package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobAdvertisementMapper {

    default JobAdvertisement mapFromDTO(JobAdvertisementDTO jobAdvertisement, Company company) {
        return JobAdvertisement.builder()
                .localization(jobAdvertisement.getProvince() + ", " + jobAdvertisement.getCity())
                .languages(jobAdvertisement.getLanguages())
                .duties(jobAdvertisement.getDuties())
                .formOfWork(jobAdvertisement.getFormOfWork())
                .skillsNeeded(jobAdvertisement.getSkillsNeeded())
                .company(company)
                .build();
    }
    default JobAdvertisementDTO mapToDTO(JobAdvertisement jobAdvertisement, Company company) {
        String[] provinceAndCity = jobAdvertisement.getLocalization().split(", ");
        return JobAdvertisementDTO.builder()
                .province(provinceAndCity[0])
                .city(provinceAndCity[1])
                .languages(jobAdvertisement.getLanguages())
                .duties(jobAdvertisement.getDuties())
                .formOfWork(jobAdvertisement.getFormOfWork())
                .skillsNeeded(jobAdvertisement.getSkillsNeeded())
                .companyId(jobAdvertisement.getCompany().getCompanyId().toString())
                .build();
    }
}
