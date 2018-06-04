package br.com.bjbraz.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bjbraz.domain.Account;
import br.com.bjbraz.domain.Corporate;
import br.com.bjbraz.repo.CorporateRepository;

/**
 * 
 * @author asimas
 *
 */
@Service
public class CorporateService {
	
	@Autowired
	private CorporateRepository corporateRepo;
	
	public Corporate consultarPorId(Long id) {
		Optional<Corporate> retorno = corporateRepo.findById(id);
		
		if(retorno == null)
			return null;
		
		return retorno.get();
	}
	
	public Optional<Corporate> findByNameLike(String name) {
		return corporateRepo.findByNameLike(name);
	}
	
	@Transactional
	public Corporate salvar(Corporate c) {
		return corporateRepo.save(c);
	}

}
