package com.loja.autos.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.autos.dto.response.CaixaResumoFechamentoResponse;
import com.loja.autos.entity.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, UUID> {
	
	@Query(value="select * from tb_caixas order by data_abertura desc, hora_abertura desc", nativeQuery = true)
	List<Caixa> getAll();
	
	@Query(value="select * from tb_caixas where data_fechamento is null order by data_abertura desc, hora_abertura desc", nativeQuery = true)
	List<Caixa> getAllOpen();
	
	@Query(value = """
	        SELECT 
	            tc.nome as nome, 
	            tc.valor_inicial as valorInicial, 
	            COALESCE(vendas.total_dinheiro, 0) AS totalVendidoDinheiro, 
	            COALESCE(gastos.total_gasto, 0) AS totalGastoDinheiro,
	            (tc.valor_inicial + COALESCE(vendas.total_dinheiro, 0) - COALESCE(gastos.total_gasto, 0)) AS saldoEsperado
	        FROM tb_caixas tc 
	        LEFT JOIN (
	            SELECT tv.id_caixa, SUM(tpv.valor_pago) AS total_dinheiro
	            FROM tb_vendas tv
	            INNER JOIN tb_pagamentos_vendas tpv ON tv.id_venda = tpv.id_venda
	            WHERE tpv.forma_pagamento = 'DINHEIRO'
	            GROUP BY tv.id_caixa
	        ) vendas ON tc.id_caixa = vendas.id_caixa
	        LEFT JOIN (
	            SELECT tl.id_caixa, SUM(tl.valor) AS total_gasto
	            FROM tb_lancamentos tl
	            WHERE tl.forma_pagamento = 'DINHEIRO' 
	              AND tl.status_lancamento = 'PAGO'
	            GROUP BY tl.id_caixa
	        ) gastos ON tc.id_caixa = gastos.id_caixa
	        WHERE 
	        tc.id_caixa = :idCaixa AND 
	        tc.data_abertura = CURRENT_DATE 
	        """, nativeQuery = true)
	    Optional<CaixaResumoFechamentoResponse> findResumoFechamento(@Param("idCaixa") UUID idCaixa);

}
