package com.loja.autos.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.loja.autos.dto.response.FaturamentoDiarioResponse;
import com.loja.autos.dto.response.FaturamentoMensalResponse;
import com.loja.autos.entity.Venda;

public interface RelatorioRepository extends JpaRepository<Venda, UUID> {

    @Query(value = """
        SELECT
            DATE(v.data_hora)        AS data,
            COUNT(v.id_venda)        AS totalVendas,
            SUM(pv.valor_pago)       AS totalValor
        FROM tb_vendas v
        JOIN tb_pagamentos_vendas pv ON pv.id_venda = v.id_venda
        WHERE v.status_venda = 'FECHADA'
          AND DATE(v.data_hora) BETWEEN :dataInicio AND :dataFim
        GROUP BY DATE(v.data_hora)
        ORDER BY DATE(v.data_hora)
        """, nativeQuery = true)
    List<FaturamentoDiarioResponse> findFaturamentoDiario(
        @Param("dataInicio") LocalDate dataInicio,
        @Param("dataFim") LocalDate dataFim
    );
    
    
    @Query(value = """
    	    SELECT
		    EXTRACT(YEAR FROM v.data_hora)  AS ano,
		    EXTRACT(MONTH FROM v.data_hora) AS mes,
		    COUNT(v.id_venda)               AS totalVendas,
		    SUM(pv.valor_pago)              AS totalValor
		FROM tb_vendas v
		JOIN tb_pagamentos_vendas pv
		    ON pv.id_venda = v.id_venda
		WHERE v.status_venda = 'FECHADA'
		  AND v.data_hora::date BETWEEN :dataInicio AND :dataFim
		GROUP BY
		    EXTRACT(YEAR FROM v.data_hora),
		    EXTRACT(MONTH FROM v.data_hora)
		ORDER BY
		    EXTRACT(YEAR FROM v.data_hora),
		    EXTRACT(MONTH FROM v.data_hora);
    	    """, nativeQuery = true)
    	List<FaturamentoMensalResponse> findFaturamentoMensal(
    	    @Param("dataInicio") LocalDate dataInicio,
    	    @Param("dataFim") LocalDate dataFim
    	);
}