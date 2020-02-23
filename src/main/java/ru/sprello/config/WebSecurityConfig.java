package ru.sprello.config;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.sprello.model.Role;
import ru.sprello.model.User;
import ru.sprello.repo.UserRepository;
import ru.sprello.security.UserPrincipalDetailsService;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * Класс конфигурации, описывающий систему безопаности приложения, а также OAuth2 авторизацию
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = Logger.getLogger(WebSecurityConfig.class);
    private final UserPrincipalDetailsService userPrincipalDetailsService;

    @Autowired
    public WebSecurityConfig(UserPrincipalDetailsService userPrincipalDetailsService) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    /**
     * Описывает конфигурацию доступа к различным маппингам приложения
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/").permitAll()
                    .mvcMatchers("/about").permitAll()
                    .mvcMatchers("/404").permitAll()
                    .mvcMatchers("/static/**").permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .logout().logoutSuccessUrl("/").permitAll()
                .and()
                    .csrf().disable();
    }

    /**
     * Обрабатывает авторизацию пользователя.
     * <p>
     * В случае если пользователь входит впервые - его данные записываются в базу данных.
     * <p>
     * Иначе пользователь успешно авторизуется, обновляется время последнего захода.
     */
    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String id = (String) map.get("sub");
            User user = userRepository.findById(id).orElseGet(() -> {
                User newUser = new User();

                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                newUser.setUserpic((String) map.get("picture"));
                newUser.setRoles(Collections.singleton(Role.USER));
                newUser.setBoards(Collections.emptySet());

                LOG.info("New user with id " + newUser.getId() + " created successfully.");
                return newUser;
            });

            user.setLastVisit(LocalDateTime.now());
            user = userRepository.save(user);
            LOG.info("User with id " + user.getId() + " logged in successfully.");
            return user;
        };
    }

    // Помощник авторизации
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.userPrincipalDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
}
