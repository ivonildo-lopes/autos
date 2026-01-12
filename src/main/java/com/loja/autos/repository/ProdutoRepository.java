package com.loja.autos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
	
	@Query(value="select * from tb_produtos where tipo_uso_produto = 'VENDA' order by nome asc", nativeQuery = true)
	List<Produto> getAllTipoVenda();

}
