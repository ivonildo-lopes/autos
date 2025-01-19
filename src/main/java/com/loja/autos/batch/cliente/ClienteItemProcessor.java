package com.loja.autos.batch.cliente;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ClienteItemProcessor implements ItemProcessor<Cliente, Cliente> {

    @Override
    public Cliente process(Cliente cliente) {
        // Simples transformação: tudo para maiúsculas
        return new Cliente(cliente.getNome().toUpperCase(), cliente.getEmail().toUpperCase());
    }
}
