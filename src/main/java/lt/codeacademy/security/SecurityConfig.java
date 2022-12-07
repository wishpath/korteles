package lt.codeacademy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static lt.codeacademy.utils.Utils.*;

//OLD WAY < --
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{

//}

// NEW WAY     <-----
//TODO 24 - Create SecurityConfig class and annotate it
@Configuration
class SecurityConfiguration{
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	//Disable CSRF
    	http.csrf().disable();
    	
    	//Setting session policy to stateless (doesn't create new session overtime)
    	//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
        
	        //If we want to secure the a website so that everyone who uses it needs to login,
	        //we'll need to use the isAuthenticated() method:
	        //.antMatchers("/*").authenticated()
	        //.antMatchers("/*").permitAll()
	        
	        //enable all unauthorized users to access our main page:

	        .antMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN") // Admin should be able to delete
	        .antMatchers(HttpMethod.PUT, "/admin/**").hasAuthority("ROLE_ADMIN") // Admin should be able to update
	        .antMatchers("/admins/*").hasAuthority(ADMIN)
	        .antMatchers("/managers/*").hasAnyAuthority(ADMIN, MANAGER) // Admin and Manager should be able to add something.
	        .antMatchers("/users/*").hasAnyAuthority(ADMIN, MANAGER, USER) // All three users should be able to get list.
	        //.antMatchers("/api/user/").hasAnyRole(ADMIN, MANAGER,USER) // All three users should be able to get something
	        //.anyRequest()
	        //.authenticated()
	        .antMatchers("/*").permitAll()
	        .antMatchers("/login").permitAll()	        
	        .and()
	        .formLogin()
	        .defaultSuccessUrl("/main");
       
	        
	        
	        //authorizeRequests().antMatchers("/").anonymous();
	        
	        //http.authorizeRequests().antMatchers("/authenticated").authenticated();
	        
        
        return http.build();
    }
        /*
         .antMatchers("/login").permitAll()
         
                .antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
                .hasAnyAuthority("Admin", "Editor", "Salesperson")
                .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                    .usernameParameter("email")
                    .permitAll()
                .and()
                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                .and()
                .logout().permitAll();
 
        http.headers().frameOptions().sameOrigin();
 
        return http.build();
    }
 
 */
   
    
	
	
}