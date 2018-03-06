package seguros.api.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import seguros.api.domain.Usuario;
import seguros.api.spring.MyUserPrincipal;
import seguros.api.spring.repo.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
    private UsuarioRepository userRepository;
 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
	}

}
