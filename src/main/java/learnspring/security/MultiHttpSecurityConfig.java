package learnspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import learnspring.security.services.LoginAuthenticationProvider;

@EnableWebSecurity
public class MultiHttpSecurityConfig{
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Deprecated
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
        return manager;
    }

    @Configuration
    @Order(1)                                                        
	public static class StaticWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
        
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .antMatchers("/images/**","/css/**","/js/**","/registration")
                .permitAll()
            ;
        }
    }

    @Configuration
    @Order(2)                                                        
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{

        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests(authorize->authorize.antMatchers("/api/**").authenticated())
                .httpBasic()
                    .and()
                .csrf()
                    .disable()
            ;
        }
    }

    @Configuration                                                   
	public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        
        @Bean
        public AuthenticationProvider loginAuthenticationProvider(){
            return new LoginAuthenticationProvider();
        }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
                .authenticationProvider(loginAuthenticationProvider())
				.authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin()
                    .loginPage("/login")
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/")
            ;
		}
	}
}
