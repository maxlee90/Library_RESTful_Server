package library;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class webConfig {
  @PersistenceContext private EntityManager entityManager;

  @Bean
  public FilterRegistrationBean characterEncodingFilter() {
    CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);

    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(characterEncodingFilter);
    registrationBean.setOrder(4);

    return registrationBean;
  }
}
