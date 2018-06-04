package br.com.bjbraz.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bjbraz.domain.ContactType;

@Repository
public interface ContactRepository extends JpaRepository<ContactType, Long>{
	
	/**
	 * 
	 */
	public List<ContactType> findAll();

}
