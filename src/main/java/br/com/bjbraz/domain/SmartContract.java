package br.com.bjbraz.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
@Entity
@Table(name = "smart_contract")
public class SmartContract implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3031754207297444357L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_smart_contract")
	private Long id;
	
	@Column(name = "nm_account")
	private String name;
	
	@Column(name = "contract_address")
	private String address;
	
	@Column(name = "sender_address")
	private String senderAddress;
	
	@Column(name = "receiver_address")
	private String receiverAddress;
	
	@Column(name = "owner_address")
	private String ownerAddress;
	
	@Column(name = "max_temp")
	private Integer maxTemp;
	
	@Column(name = "dh_deploy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deployDate;
	
	private boolean ativo;
	
	

}
