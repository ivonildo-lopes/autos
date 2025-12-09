package com.loja.autos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, UUID> {

	@Query(nativeQuery = true, value = "select * from tb_lancamentos l where l.tipo_lancacamento = :tipoLancamento")
	List<Lancamento> findAllLancamento(String tipoLancamento);

}
