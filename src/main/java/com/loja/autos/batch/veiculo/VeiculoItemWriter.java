package com.loja.autos.batch.veiculo;

import java.util.stream.Collectors;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.loja.autos.dto.request.VeiculoRequest;
import com.loja.autos.mappers.VeiculoMapper;
import com.loja.autos.repository.VeiculoRepository;

@Component
public class VeiculoItemWriter implements ItemWriter<VeiculoRequest> {

    @Autowired
    private VeiculoRepository veiculoRepository; 
    
    private VeiculoMapper mapper;

	@Override
	public void write(Chunk<? extends VeiculoRequest> chunk) throws Exception {
		
		var listaNova =  chunk.getItems().stream().map(dto -> mapper.conververToModel(dto)).collect(Collectors.toList());
		
		veiculoRepository.saveAll(listaNova);
		
	}
}
