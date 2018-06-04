package br.com.bjbraz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bjbraz.domain.Corporate;

@Repository
public interface CorporateRepository extends JpaRepository<Corporate, Long> {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Corporate> findById(Long id);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Optional<Corporate> findByNameLike(String name);
	
	/**
	 * 
	 */
	public List<Corporate> findAll();	
	

}
