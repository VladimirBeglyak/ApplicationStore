package by.beglyakdehterenok.store.config;

import by.beglyakdehterenok.store.entity.Permission;
import by.beglyakdehterenok.store.entity.Role;
import by.beglyakdehterenok.store.exception.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, MyAccessDeniedHandler myAccessDeniedHandler) {
        this.userDetailsService = userDetailsService;
        this.myAccessDeniedHandler = myAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                    .antMatchers("/account/all/**","/catalog/all/**","/catalog/save/**","/catalog/add","/account/private").hasAuthority(Permission.ACCOUNT_WRITE.getPermission())
                    .antMatchers("/account/private").hasAnyAuthority(Permission.ACCOUNT_READ.getPermission(),Permission.ACCOUNT_WRITE.getPermission())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/auth/success")
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/shop");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("webapp/**", "config/translations_ru_RU.properties", "config/translations.properties", "config/translations_en_US.properties");
    }


}
