package br.com.bjbraz.domain;

import java.io.Serializable;
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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "blockchain_data")
public class BlockchainData  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086719031217218783L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_blockchain_data")
	private Long id;

	@Column(name = "dh_transaction")
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	
	//{"humidity": 51.0, "hora": "09:58:41", "temperature": 24.0, "data": "12/04/2018"}

	@Column(name = "date_sensor", nullable = false)
	private String dateSensor;
	
	@Column(name = "hour_sensor", nullable = false)
	private String hourSensor;
	
	@Column(name = "humidity", nullable = false)
	private String humidity;
	
	@Column(name = "temperature", nullable = false)
	private Integer temperature;
	
	@Column(name = "transaction_hash", nullable = true)
	private String transactionHash;	
	
	@Column(name = "smart_contract_address", nullable = true)
	private String smartContractAddress;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_smart_contract")
	private SmartContract smartContract;
	
	@Column(name = "latitude", nullable = true)
	private String latitude;
	
	@Column(name = "longitude", nullable = true)
	private String longitude;

}