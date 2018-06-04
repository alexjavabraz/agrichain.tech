package br.com.bjbraz.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.bjbraz.domain.Corporate;
import br.com.bjbraz.domain.User;
import br.com.bjbraz.repo.UserRepository;
import br.com.bjbraz.spring.MyUserPrincipal;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;
 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
	}

	public Optional<User> findByUserName(String userName) {
		return userRepository.findByName(userName);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public User findById(Long id) {
		User retorno = null;
		Optional<User> user = userRepository.findById(id);
		
		if(user != null)
			retorno = user.get();
		
		return retorno;
	}	
	
	@Transactional
	public User salvar(User c) {
		return userRepository.save(c);
	}	

}
