package com.tienda_m;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author alede
 */
@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");

        return slr;
    }

    @Bean
    public LocaleChangeInterceptor changeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");

        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(changeInterceptor());
    }

    //metodo es para evitar del uso de una clase controler que muestre una pagina en porticular
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

    //metodo para establecer la estructura de seguridad del sitio
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request) -> request.requestMatchers("/", "/login", "/js/**", "/webjars/**", "/registro/**").permitAll()
                        .requestMatchers("/categoria/listado", "/producto/listado").hasRole("VENDEDOR")
                        .requestMatchers("/categoria/nuevo", "categoria/modificar/**", "categoria/eliminar/**", "categoria/guardar").hasRole("ADMIN")
                        .requestMatchers("/producto/nuevo", "producto/modificar/**", "producto/eliminar/**", "producto/guardar", "/pruebas/**").hasRole("ADMIN")
                        .requestMatchers("/facturar/carrito").hasRole("USER"))
                .formLogin((form) -> form.loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return httpSecurity.build();
    }

    //no se usa en prod, solo pruebas
//    @Bean
//    public UserDetailsService users() {
//        UserDetails admin = User.builder().username("juan").password("{noop}123").roles("USER", "VENDEDOR", "ADMIN").build();
//        UserDetails vendedor = User.builder().username("rebeca").password("{noop}456").roles("USER", "VENDEDOR").build();
//        UserDetails user = User.builder().username("pedro").password("{noop}789").roles("USER").build();
//
//        return new InMemoryUserDetailsManager(admin, vendedor, user);
//    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }


}
