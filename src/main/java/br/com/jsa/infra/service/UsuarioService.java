package br.com.jsa.infra.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jsa.infra.model.Usuario;
import br.com.jsa.infra.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> u = this.usuarioRepository.findByEmail(username);
		if(u.isPresent())
			return u.get();
		
		throw new UsernameNotFoundException("Dados inválido");
	}
	
}
