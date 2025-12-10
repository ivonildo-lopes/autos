package com.loja.autos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

	@Query("SELECT c FROM Cliente c WHERE c.pessoa.documento = :documento")
	Cliente findByDocumento(String documento);

	@Query(value = """
								    SELECT *
			FROM tb_clientes tc
			  where
			  tc.ativo is true and
			 tc.data_ultima_compra = CURRENT_DATE - INTERVAL '30 days';
								    """, nativeQuery = true)
	List<Cliente> consultaClientesAusentePor30dias();

	@Query(value = """
					    SELECT *
			FROM tb_clientes tc
			  where 
			  tc.ativo is true and
			  tc.data_ultima_compra = CURRENT_DATE - INTERVAL '61 days';
					    """, nativeQuery = true)
	List<Cliente> consultaClientesAusentePor60dias();

	@Query(value = """
					    SELECT *
			FROM tb_clientes tc
			  where 
			  tc.ativo is true and
			  tc.data_ultima_compra = CURRENT_DATE - INTERVAL '90 days';
					    """, nativeQuery = true)
	List<Cliente> consultaClientesAusentePor90dias();

}
