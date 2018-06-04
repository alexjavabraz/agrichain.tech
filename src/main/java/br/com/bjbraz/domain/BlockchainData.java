package br.com.bjbraz.domain;

import java.io.Serializable;import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private String temperature;
	
	@Column(name = "transaction_hash", nullable = true)
	private String transactionHash;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDateSensor() {
		return dateSensor;
	}

	public void setDateSensor(String dateSensor) {
		this.dateSensor = dateSensor;
	}

	public String getHourSensor() {
		return hourSensor;
	}

	public void setHourSensor(String hourSensor) {
		this.hourSensor = hourSensor;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTransactionHash() {
		return transactionHash;
	}

	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}
	
}