package com.loja.autos.batch.veiculo;

import org.springframework.batch.item.ItemProcessor;

import com.loja.autos.dto.request.VeiculoRequest;

public class VeiculoItemProcessor implements ItemProcessor<VeiculoRequest, VeiculoRequest> {

    @Override
    public VeiculoRequest process(VeiculoRequest veiculoRequest) {
        // Transformações adicionais podem ser aplicadas aqui
        return veiculoRequest;
    }
}
