package ar.edu.itba.webapp.config;

import ar.edu.itba.webapp.auth.JwtAuthenticationFilter;
import ar.edu.itba.webapp.auth.JwtAuthorizationFilter;
import ar.edu.itba.webapp.auth.RestAuthenticationEntryPoint;
import ar.edu.itba.webapp.auth.MySavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@ComponentScan("ar.edu.itba.webapp.auth")
public class WebAuthConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler successHandler;

    private SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .invalidSessionUrl("/login")
            .and().authorizeRequests()
//                .antMatchers("/register", "/create", "/login").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
//            .and().formLogin()
//                .successHandler(successHandler)
//                .failureHandler(failureHandler)
////                .usernameParameter("j_username")
////                .passwordParameter("j_password")
////                .defaultSuccessUrl("/", false)
////                .loginPage("/login")
////            .and().rememberMe()
////                .rememberMeParameter("j_rememberme")
////                .userDetailsService(userDetailsService)
////                .key("26b3e4725d3e8af6542a7e074a091618")
////                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
//            .and().logout()
////                .logoutUrl("/logout")
////                .logoutSuccessUrl("/login")
            .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedPage("/403")
            .and().csrf()
                .disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**", "/favicon.ico", "/403", "/404");
    }
}