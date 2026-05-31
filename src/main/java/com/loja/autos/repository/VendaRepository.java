package com.loja.autos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.dto.response.VendasPorFormaPagamentoResponse;
import com.loja.autos.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, UUID> {
	
	@Query(value = """
		    SELECT
		        pv.forma_pagamento   AS formaPagamento,
		        COUNT(pv.id_pagamento_venda) AS totalTransacoes,
		        SUM(pv.valor_pago)   AS totalValor
		    FROM tb_pagamentos_vendas pv
		    JOIN tb_vendas v ON v.id_venda = pv.id_venda
		    WHERE v.status_venda = 'FECHADA'
		      AND DATE(v.data_hora) = CURRENT_DATE
		    GROUP BY pv.forma_pagamento
		    ORDER BY SUM(pv.valor_pago) DESC
		    """, nativeQuery = true)
		List<VendasPorFormaPagamentoResponse> findVendasHojePorFormaPagamento();

}
