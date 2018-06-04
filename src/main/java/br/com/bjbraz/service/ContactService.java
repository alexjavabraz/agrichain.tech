package br.com.bjbraz.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bjbraz.domain.ContactType;
import br.com.bjbraz.repo.ContactRepository;

/**
 * 
 * @author asimas
 *
 */
@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepo;
	
	@Transactional
	public ContactType saveAccount(ContactType c) {
		return contactRepo.save(c);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<ContactType> findAll() {
		return contactRepo.findAll();
	}	

}