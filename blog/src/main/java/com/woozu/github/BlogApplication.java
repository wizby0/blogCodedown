package com.woozu.github;

import com.woozu.github.config.CustomUserDetails;
import com.woozu.github.entities.Role;
import com.woozu.github.entities.User;
import com.woozu.github.repositories.UserRepository;
import com.woozu.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class BlogApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	/**
	 * Password grants are switched on by injecting an AuthenticationManager.
	 * Here, we setup the builder so that the userDetailsService is the one we coded.
	 * @param builder
	 * @param repository
	 * @throws Exception
	 */
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception {
		if (repository.count()==0)
			userService.save(new User("admin", "adminPassword", Arrays.asList(new Role("USER"), new Role("ACTUATOR") , new Role("ADMIN"))));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);

		if (repository.count()==1)
			userService.save(new User("user1", "user1", Arrays.asList(new Role("USER"), new Role("ACTUATOR") , new Role("ClIENT"))));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	/**
	 * We return an istance of our CustomUserDetails.
	 * @param repository
	 * @return
	 */
	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
}
