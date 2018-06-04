package br.com.bjbraz.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bjbraz.domain.Account;
import br.com.bjbraz.domain.Corporate;
import br.com.bjbraz.domain.User;
import br.com.bjbraz.repo.AccountRepository;
import br.com.bjbraz.repo.CorporateRepository;
import br.com.bjbraz.repo.UserRepository;

@Service
public class RegisterService {
	
	@Autowired
	private AccountRepository contaRepo;
	
	@Autowired
	private CorporateRepository empresaRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Transactional
	public Account saveAccount(Account c) {
		return contaRepo.save(c);
	}
	
	@Transactional
	public Corporate saveCorporate(Corporate c) {
		return empresaRepo.save(c);
	}
	
	@Transactional
	public User saveCorporate(User c) {
		return userRepo.save(c);
	}	

}
