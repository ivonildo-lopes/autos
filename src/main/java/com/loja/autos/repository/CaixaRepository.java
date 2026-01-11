package com.loja.autos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, UUID> {
	
	@Query(value="select * from tb_caixas order by data_abertura desc, hora_abertura desc", nativeQuery = true)
	List<Caixa> getAll();

}
