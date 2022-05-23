package br.com.jsa.api.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
}
