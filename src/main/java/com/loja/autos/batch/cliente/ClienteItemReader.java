package com.loja.autos.batch.cliente;

import org.springframework.batch.item.ItemReader;

import java.util.Arrays;
import java.util.List;

public class ClienteItemReader implements ItemReader<Cliente> {

    private List<Cliente> clientes = Arrays.asList(
            new Cliente("João", "joao@example.com"),
            new Cliente("Maria", "maria@example.com")
    );
    private int index = 0;

    @Override
    public Cliente read() {
        if (index < clientes.size()) {
            return clientes.get(index++);
        } else {
            return null;
        }
    }
}
