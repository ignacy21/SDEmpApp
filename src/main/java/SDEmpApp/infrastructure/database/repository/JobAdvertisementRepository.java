package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.mapper.JobAdvertisementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;

    @Override
    public void createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        JobAdvertisementEntity jobAdvertisementEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);
        jobAdvertisementJpaRepository.saveAndFlush(jobAdvertisementEntity);
    }

    @Override
    public List<Company> findCompanyByFormOfWork(String name) {
        return jobAdvertisementJpaRepository.findByFormOfWork(name).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .map(JobAdvertisement::getCompany)
                .toList();
    }

    public List<Company> findCompanyBySkillsNeeded(List<String> skillList) {
        return jobAdvertisementJpaRepository.findBySkillsNeeded(skillList).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .map(JobAdvertisement::getCompany)
                .toList();
    }

    @Override
    public List<Company> findCompanyByLocalization(Localization localization) {
        return jobAdvertisementJpaRepository.findByLocalization(localization).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .map(JobAdvertisement::getCompany)
                .toList();
    }
}
