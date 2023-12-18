package aptech.dating.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import aptech.dating.security.AuthEntryPointJwt;
import aptech.dating.security.AuthTokenFilter;
import aptech.dating.service.AdminService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
	@Autowired
	AdminService adminService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(adminService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/admin/**").hasAuthority("admin")
						.requestMatchers("/api/banned/**").hasAuthority("admin")
						.requestMatchers("/api/block/**").hasAuthority("admin")
						.requestMatchers("/api/blogImage/**").hasAuthority("admin")
						.requestMatchers("/api/conversation/**").hasAuthority("admin")
						.requestMatchers("/api/drinking/**").hasAuthority("admin")
						.requestMatchers("/api/evidence/**").hasAuthority("admin")
						.requestMatchers("/api/exercise/**").hasAuthority("admin")
						.requestMatchers("/api/expecting/**").hasAuthority("admin")
						.requestMatchers("/api/family/**").hasAuthority("admin")
						.requestMatchers("/api/file/**").hasAuthority("admin")
						.requestMatchers("/api/habit/**").hasAuthority("admin")
						.requestMatchers("/api/hobby/**").hasAuthority("admin")
						.requestMatchers("/api/language/**").hasAuthority("admin")
						.requestMatchers("/api/literacy/**").hasAuthority("admin")
						.requestMatchers("/api/mathching/**").hasAuthority("admin")
						.requestMatchers("/api/message/**").hasAuthority("admin")
						.requestMatchers("/api/music/**").hasAuthority("admin")
						.requestMatchers("/api/notification/**").hasAuthority("admin")
						.requestMatchers("/api/pet/**").hasAuthority("admin")
						.requestMatchers("/api/purpose/**").hasAuthority("admin")
						.requestMatchers("/api/reason/**").hasAuthority("admin")
						.requestMatchers("/api/report/**").hasAuthority("admin")
						.requestMatchers("/api/singer/**").hasAuthority("admin")
						.requestMatchers("/api/smoking/**").hasAuthority("admin")
						.requestMatchers("/api/status/**").hasAuthority("admin")
						.requestMatchers("/api/user/**").hasAuthority("admin")
						.requestMatchers("/api/userConversation/**").hasAuthority("admin")
						.requestMatchers("/api/userExercise/**").hasAuthority("admin")
						.requestMatchers("/api/userExpecting/**").hasAuthority("admin")
						.requestMatchers("/api/userHobby/**").hasAuthority("admin")
						.requestMatchers("/api/userImage/**").hasAuthority("admin")
						.requestMatchers("/api/userLanguage/**").hasAuthority("admin")
						.requestMatchers("/api/userMusic/**").hasAuthority("admin")
						.requestMatchers("/api/userPet/**").hasAuthority("admin")
						.requestMatchers("/api/userSinger/**").hasAuthority("admin")
						.requestMatchers("/api/videoCall/**").hasAuthority("admin")
						.requestMatchers("/api/work/**").hasAuthority("admin").anyRequest().authenticated());

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
