package com.loja.autos.analytics.repositorys;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Venda;

public interface AnalyticsRepository extends JpaRepository<Venda, UUID> {

	@Query("""
			    SELECT v.dataHora, COUNT(v.id), SUM(v.valorTotal)
			    FROM Venda v
			    GROUP BY v.dataHora
			    ORDER BY v.dataHora DESC
			""")
	List<Object[]> vendasPorDia();

	@Query("""
			    SELECT SUM(c.valorInicial)
			    FROM Caixa c
			    WHERE c.dataFechamento = null
			""")
	BigDecimal caixaAtual();

	@Query("""
			    SELECT v.cliente.pessoa.nome, SUM(v.valorTotal), COUNT(v.id)
			    FROM Venda v
			    GROUP BY v.cliente.pessoa.nome
			    ORDER BY SUM(v.valorTotal) DESC
			""")
	List<Object[]> clienteQueMaisCompra();

	@Query("""
			    SELECT vp.produto.nome, SUM(vp.valorTotalItem)
			    FROM ItemVenda vp
			    GROUP BY vp.produto.nome
			    ORDER BY vp.produto.nome DESC
			""")
	List<Object[]> produtosMaisVendidos();

	@Query("""
			    SELECT AVG(v.valorTotal)
			    FROM Venda v
			""")
	Double ticketMedio();

	@Query("""
			    SELECT fp.formaPagamento, COUNT(fp.id), SUM(fp.valorPago)
			    FROM PagamentoVenda fp
			    GROUP BY fp.formaPagamento
			""")
	List<Object[]> vendasPorFormaPagamento();

}
