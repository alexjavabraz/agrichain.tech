package br.com.bjbraz.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "account")
public class Account implements Serializable {
	
	public Account(){}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5702983895367745095L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_account")
	private Long id;
	
	@Column(name = "nm_account")
	private String name;
	
	@Column(name = "account_address")
	private String address;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_user")
	private User user;
	
	@Column(name = "dh_last_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastUpdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_corporate")
	private Corporate corporate;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getEndereco() {
		return address;
	}

	public void setEndereco(String endereco) {
		this.address = endereco;
	}

	public BigDecimal getSaldo() {
		return balance;
	}

	public void setSaldo(BigDecimal saldo) {
		this.balance = saldo;
	}

	public User getUsuario() {
		return user;
	}

	public void setUsuario(User usuario) {
		this.user = usuario;
	}

	public Date getDataAtualizacao() {
		return dateLastUpdate;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dateLastUpdate = dataAtualizacao;
	}

	public Corporate getCorporate() {
		return corporate;
	}

	public void setCorporate(Corporate corporate) {
		this.corporate = corporate;
	}
	
	
}
