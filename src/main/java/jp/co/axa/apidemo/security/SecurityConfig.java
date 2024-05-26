package jp.co.axa.apidemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

/**
 * Configuration class for security settings.
 *
 * This class extends the {@link WebSecurityConfigurerAdapter} class and provides
 * methods to configure the security settings for the application.
 *
 * It is annotated with {@link Configuration} to indicate that it is a configuration class
 * and {@link EnableWebSecurity} to enable Spring Security.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Returns an instance of UserDetailsManager.
     *
     * @param dataSource The data source used to create the UserDetailsManager
     * @return An instance of UserDetailsManager
     */
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     * Configures the security settings for the application.
     *
     * This method is called when initializing the security settings for the application. It
     * configures the authorization rules for various HTTP methods and paths. The configuration
     * specifies the roles required to access each path.
     *
     * The method uses the provided {@link HttpSecurity} object to define the configuration. It
     * starts by calling the {@link HttpSecurity#authorizeRequests()} method to specify that
     * authorization rules will be applied to requests. It then uses the
     * {@link HttpSecurity#regexMatchers(HttpMethod, String)} method to specify which HTTP method
     * and path the rule applies to. For each rule, it uses the
     * {@link HttpSecurity.ExpressionInterceptUrlRegistry#hasRole(String)}
     * method to specify the required role.
     *
     * After defining the authorization rules, the method calls the {@link HttpSecurity#anyRequest()}
     * method to specify that any other request not matched by the previous rules must be
     * authenticated. It then calls {@link HttpSecurity#httpBasic()} to enable HTTP Basic
     * authentication. Finally, it disables CSRF protection by calling {@link HttpSecurity#csrf()}.
     *
     * @param http The {@link HttpSecurity} object used to configure the security settings
     * @throws Exception If an error occurs while configuring the security settings
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .regexMatchers(HttpMethod.GET, "/api/v1/employees").hasRole("EMPLOYEE")
                .regexMatchers(HttpMethod.GET, "/api/v1/employees/.*").hasRole("EMPLOYEE")
                .regexMatchers(HttpMethod.POST, "/api/v1/employees").hasRole("MANAGER")
                .regexMatchers(HttpMethod.PUT, "/api/v1/employees/.*").hasRole("MANAGER")
                .regexMatchers(HttpMethod.DELETE, "/api/v1/employees/.*").hasRole("ADMIN")
                .antMatchers("/h2-console/.*").permitAll() // Allow all requests to H2 console for development purposes
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable(); // Add this line to disable X-Frame-Options for development purposes
    }
}
