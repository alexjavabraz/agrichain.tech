package br.com.bjbraz.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;

import br.com.bjbraz.domain.ContactType;

public class ContactTypeDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5842213225792640799L;
	
	private List<ContactType> contactTypes;

	@JsonSerialize(typing = Typing.STATIC)
	public List<ContactType> getContactTypes() {
		return contactTypes;
	}

	public void setContactTypes(List<ContactType> contactTypes) {
		this.contactTypes = contactTypes;
	}
	
	
}
