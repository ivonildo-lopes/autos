package com.loja.autos.batch.cliente;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class ClienteItemWriter implements ItemWriter<Cliente> {

	@Override
	public void write(Chunk<? extends Cliente> clientes) throws Exception {
		clientes.forEach(System.out::println);
		
	}
}
