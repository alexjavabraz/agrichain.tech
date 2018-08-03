package br.com.bjbraz.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bjbraz.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long id);
  
  User findByNameAndPassword(String nome, String password);
  
  Optional<User> findByName(String nome);
  
  User findByEmail(String email);
  
}
