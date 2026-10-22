package employees;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(EmployeesProperties.class)
@ImportHttpServices(group = "employees-backend", types = EmployeesClient.class)
@Slf4j
public class ClientConfig {
    @Bean
    public RestClientHttpServiceGroupConfigurer groupConfigurer(EmployeesProperties employeesProperties) {
        log.info("Employees backend url: {}", employeesProperties.getBackendUrl());
        return groups ->
                groups.filterByName("employees-backend").forEachClient((group, builder) ->
                        builder.baseUrl(employeesProperties.getBackendUrl()));

    }

}
