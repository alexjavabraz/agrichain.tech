package br.com.bjbraz.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "corporate")
public class Corporate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7691510569268755777L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_corporate")
	private Long id;

	@Column(name = "dh_register")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRegister;
	
	@Column(name = "dh_last_changed")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastChanged;

	@Column(name = "name", nullable = false, length = 200)
	private String name;
	
	@Column(name = "alias", nullable = false, length = 50)
	private String alias;
	
	@Column(name = "address", nullable = false, length = 200)
	private String address;
	
	@Column(name = "neighborhood", nullable = false, length = 200)
	private String neighborhood;	
	
	@Column(name = "state", nullable = false, length = 200)
	private String state;
	
	@Column(name = "city", nullable = false, length = 200)
	private String city;
	
	@Column(name = "country", nullable = false, length = 200)
	private String country;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;	
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="corporate")
	public List<Account> accounts;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_contact_info")
	private ContactInfo contactInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Date dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Date getDateLastChanged() {
		return dateLastChanged;
	}

	public void setDateLastChanged(Date dateLastChanged) {
		this.dateLastChanged = dateLastChanged;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}