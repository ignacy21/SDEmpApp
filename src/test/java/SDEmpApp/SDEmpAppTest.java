package SDEmpApp;

import SDEmpApp.buisness.CompanyService;
import SDEmpApp.infrastructure.configuration.ApplicationConfiguration;
import SDEmpApp.infrastructure.database.repository.CompanyRepository;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SDEmpAppTest {

    @Container
    static PostgreSQLContainer<?> POSTGRESQL_CONTAINER = new PostgreSQLContainer<>("postgres:alpine");

    @DynamicPropertySource
    @SuppressWarnings("unused")
    static void setPostgresqlContainerProperties(DynamicPropertyRegistry registry) {
        registry.add("jdbc.url", POSTGRESQL_CONTAINER::getJdbcUrl);
        registry.add("jdbc.user", POSTGRESQL_CONTAINER::getUsername);
        registry.add("jdbc.pass", POSTGRESQL_CONTAINER::getPassword);
    }

    private CompanyService companyService;


    @Test
    @Order(1)
    void createCompany() {
        log.info("### 1 -- CREATE COMPANY");
        companyService.createCompanies();
    }



}
