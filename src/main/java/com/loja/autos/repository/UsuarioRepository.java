package com.loja.autos.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.loja.autos.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	
	@Query("SELECT u FROM Usuario u WHERE u.pessoa.documento = :documento")
	Optional<Usuario> findByDocumento(String documento);

}
