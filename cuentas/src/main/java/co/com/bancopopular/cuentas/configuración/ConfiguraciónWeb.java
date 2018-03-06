package co.com.bancopopular.cuentas.configuración;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.com.bancopopular.cuentas.servicios.seguridad.ServicioDetallesUsuarioImpl;

@Configuration
public class ConfiguraciónWeb extends WebSecurityConfigurerAdapter {

    @Autowired
    private ServicioDetallesUsuarioImpl servicioDetallesUsuario;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(servicioDetallesUsuario);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**/favicon.ico") .permitAll()
            .and().authorizeRequests().antMatchers("/usuario/listar").permitAll()
            .and().authorizeRequests().antMatchers("/usuarios/**").permitAll()
            .and().authorizeRequests().antMatchers("/webjars/**").permitAll()
            .and().authorizeRequests().antMatchers("/static/css").permitAll()
            .and().authorizeRequests().antMatchers("/js").permitAll()
            .and().formLogin().loginPage("/login").permitAll()
            .and().authorizeRequests().antMatchers("/usuario/crear").authenticated()
            .and().exceptionHandling().accessDeniedPage("/access_denied");
    }
}
