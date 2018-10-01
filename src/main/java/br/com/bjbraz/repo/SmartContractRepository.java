package br.com.bjbraz.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bjbraz.domain.SmartContract;

@Repository
public interface SmartContractRepository extends JpaRepository<SmartContract, Long> {
	
	public List<SmartContract> findByAtivo(boolean ativo);
	
	public Optional<SmartContract> findOneByAddress(String address);
	
}