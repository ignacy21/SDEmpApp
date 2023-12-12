package SDEmpApp.infrastructure.database.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Skill;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.entities.SkillEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T13:38:54+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class JobAdvertisementEntityMapperImpl implements JobAdvertisementEntityMapper {

    @Override
    public JobAdvertisementEntity mapToEntity(JobAdvertisement jobAdvertisement) {
        if ( jobAdvertisement == null ) {
            return null;
        }

        JobAdvertisementEntity.JobAdvertisementEntityBuilder jobAdvertisementEntity = JobAdvertisementEntity.builder();

        jobAdvertisementEntity.jobAdvertisementId( jobAdvertisement.getJobAdvertisementId() );
        jobAdvertisementEntity.localization( jobAdvertisement.getLocalization() );
        jobAdvertisementEntity.languages( jobAdvertisement.getLanguages() );
        jobAdvertisementEntity.duties( jobAdvertisement.getDuties() );
        jobAdvertisementEntity.formOfWork( jobAdvertisement.getFormOfWork() );
        jobAdvertisementEntity.skillsNeeded( skillListToSkillEntityList( jobAdvertisement.getSkillsNeeded() ) );
        jobAdvertisementEntity.company( companyToCompanyEntity( jobAdvertisement.getCompany() ) );

        return jobAdvertisementEntity.build();
    }

    @Override
    public JobAdvertisement mapFromEntity(JobAdvertisementEntity jobAdvertisementEntity) {
        if ( jobAdvertisementEntity == null ) {
            return null;
        }

        JobAdvertisement.JobAdvertisementBuilder jobAdvertisement = JobAdvertisement.builder();

        jobAdvertisement.jobAdvertisementId( jobAdvertisementEntity.getJobAdvertisementId() );
        jobAdvertisement.localization( jobAdvertisementEntity.getLocalization() );
        jobAdvertisement.languages( jobAdvertisementEntity.getLanguages() );
        jobAdvertisement.skillsNeeded( skillEntityListToSkillList( jobAdvertisementEntity.getSkillsNeeded() ) );
        jobAdvertisement.duties( jobAdvertisementEntity.getDuties() );
        jobAdvertisement.formOfWork( jobAdvertisementEntity.getFormOfWork() );
        jobAdvertisement.company( companyEntityToCompany( jobAdvertisementEntity.getCompany() ) );

        return jobAdvertisement.build();
    }

    protected SkillEntity skillToSkillEntity(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillEntity.SkillEntityBuilder skillEntity = SkillEntity.builder();

        skillEntity.skillId( skill.getSkillId() );
        skillEntity.name( skill.getName() );

        return skillEntity.build();
    }

    protected List<SkillEntity> skillListToSkillEntityList(List<Skill> list) {
        if ( list == null ) {
            return null;
        }

        List<SkillEntity> list1 = new ArrayList<SkillEntity>( list.size() );
        for ( Skill skill : list ) {
            list1.add( skillToSkillEntity( skill ) );
        }

        return list1;
    }

    protected List<JobAdvertisementEntity> jobAdvertisementListToJobAdvertisementEntityList(List<JobAdvertisement> list) {
        if ( list == null ) {
            return null;
        }

        List<JobAdvertisementEntity> list1 = new ArrayList<JobAdvertisementEntity>( list.size() );
        for ( JobAdvertisement jobAdvertisement : list ) {
            list1.add( mapToEntity( jobAdvertisement ) );
        }

        return list1;
    }

    protected CompanyEntity companyToCompanyEntity(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyEntity.CompanyEntityBuilder companyEntity = CompanyEntity.builder();

        companyEntity.companyId( company.getCompanyId() );
        companyEntity.name( company.getName() );
        companyEntity.description( company.getDescription() );
        companyEntity.jobAdvertisementEntities( jobAdvertisementListToJobAdvertisementEntityList( company.getJobAdvertisementEntities() ) );

        return companyEntity.build();
    }

    protected Skill skillEntityToSkill(SkillEntity skillEntity) {
        if ( skillEntity == null ) {
            return null;
        }

        Skill.SkillBuilder skill = Skill.builder();

        skill.skillId( skillEntity.getSkillId() );
        skill.name( skillEntity.getName() );

        return skill.build();
    }

    protected List<Skill> skillEntityListToSkillList(List<SkillEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Skill> list1 = new ArrayList<Skill>( list.size() );
        for ( SkillEntity skillEntity : list ) {
            list1.add( skillEntityToSkill( skillEntity ) );
        }

        return list1;
    }

    protected List<JobAdvertisement> jobAdvertisementEntityListToJobAdvertisementList(List<JobAdvertisementEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<JobAdvertisement> list1 = new ArrayList<JobAdvertisement>( list.size() );
        for ( JobAdvertisementEntity jobAdvertisementEntity : list ) {
            list1.add( mapFromEntity( jobAdvertisementEntity ) );
        }

        return list1;
    }

    protected Company companyEntityToCompany(CompanyEntity companyEntity) {
        if ( companyEntity == null ) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.companyId( companyEntity.getCompanyId() );
        company.name( companyEntity.getName() );
        company.description( companyEntity.getDescription() );
        company.jobAdvertisementEntities( jobAdvertisementEntityListToJobAdvertisementList( companyEntity.getJobAdvertisementEntities() ) );

        return company.build();
    }
}
