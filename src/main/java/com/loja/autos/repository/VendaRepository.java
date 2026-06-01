package com.loja.autos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	
	
	@Query(value = """
		    SELECT
		        v.id_venda      AS id,
		        v.data_hora     AS dataHora,
		        c.nome          AS nomeCaixa,
		        p.nome          AS nomeCliente,
		        v.status_venda  AS statusVenda,
		        v.observacoes   AS observacoes
		    FROM tb_vendas v
		    JOIN tb_caixas c ON c.id_caixa = v.id_caixa
		    LEFT JOIN tb_clientes cl ON cl.id_cliente = v.id_cliente
		    LEFT JOIN tb_pessoa p ON p.id_pessoa = cl.id_pessoa
		    WHERE v.id_venda = :id
		    """, nativeQuery = true)
		VendaDetalheProjection findDetalheById(@Param("id") UUID id);

		@Query(value = """
		    SELECT
		        pr.nome             AS nomeProduto,
		        iv.tipo_unidade     AS tipoUnidade,
		        iv.peso_emkg       AS pesoEmKg,
		        iv.quantidade_unidade AS quantidadeUnidade,
		        iv.valor_unitario   AS valorUnitario
		    FROM tb_items_vendas iv
		    JOIN tb_produtos pr ON pr.id_produto = iv.id_produto
		    WHERE iv.id_venda = :id
		    """, nativeQuery = true)
		List<ItemVendaProjection> findItensByVendaId(@Param("id") UUID id);

		@Query(value = """
		    SELECT
		        pv.forma_pagamento  AS formaPagamento,
		        pv.valor_pago       AS valorPago
		    FROM tb_pagamentos_vendas pv
		    WHERE pv.id_venda = :id
		    """, nativeQuery = true)
		List<PagamentoVendaProjection> findPagamentosByVendaId(@Param("id") UUID id);
		
		public interface VendaDetalheProjection {
		    String getId();
		    String getDataHora();
		    String getNomeCaixa();
		    String getNomeCliente();
		    String getStatusVenda();
		    String getObservacoes();
		}

		public interface ItemVendaProjection {
		    String getNomeProduto();
		    String getTipoUnidade();
		    Double getPesoEmKg();
		    Integer getQuantidadeUnidade();
		    Double getValorUnitario();
		}

		public interface PagamentoVendaProjection {
		    String getFormaPagamento();
		    Double getValorPago();
		}

}
