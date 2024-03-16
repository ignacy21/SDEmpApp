package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;
    CompanyEntityMapper companyEntityMapper;

    @Override
    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);

        JobAdvertisementEntity jobAdvertEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);

        jobAdvertEntity.setCompany(jobAdvertEntity.getCompany());

        JobAdvertisementEntity jobAdvertEntityCreated = jobAdvertisementJpaRepository.saveAndFlush(jobAdvertEntity);
        jobAdvertisement.setJobAdvertisementId(jobAdvertEntityCreated.getJobAdvertisementId());
        return jobAdvertisement;
    }

    @Override
    public List<JobAdvertisement> findByFormOfWork(String formOfWork) {
        List<JobAdvertisementEntity> byFormOfWork = jobAdvertisementJpaRepository.findByFormOfWork(formOfWork);
        return byFormOfWork.stream().map(jobAdvertisementEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobAdvertisement> findBySkill(String skillName) {
        List<JobAdvertisementEntity> bySkill = jobAdvertisementJpaRepository.findBySkillsNeededContaining(skillName);
        return bySkill.stream().map(jobAdvertisementEntityMapper::mapFromEntity).toList();
    }
}
