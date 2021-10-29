package by.beglyakdehterenok.store.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final DataSource dataSource;

//    private final AccessDeniedHandler accessDeniedHandler;

//    @Autowired
//    public WebSecurityConfig(DataSource dataSource /*AccessDeniedHandler accessDeniedHandler*/) {
//        this.dataSource = dataSource;
////        this.accessDeniedHandler = accessDeniedHandler;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/account")
//                    .authenticated()
//                .anyRequest()
//                    .permitAll()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/account");
////                .and()
////                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/resources");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("select login,password,role from accounts where login=?")
//                .authoritiesByUsernameQuery("select a.login,a.role from accounts a where login=?");
//    }
}
