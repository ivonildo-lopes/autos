package com.loja.autos.batch.veiculo;

import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import com.loja.autos.dto.request.VeiculoRequest;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class VeiculoItemReader implements ItemReader<VeiculoRequest> {
	
	@Value("${file.veiculo.input}")
	private String source;
		
    private Iterator<VeiculoRequest> veiculoIterator;
    
    @Override
    public VeiculoRequest read() throws Exception {
        if (veiculoIterator == null) {
            ClassPathResource resource = new ClassPathResource(source);
            try (Reader reader = new FileReader(resource.getFile())) {
                List<String> headerList = Arrays.asList(
                    "placa", "renavam", "chassi", "marca", "modelo", 
                    "cor", "anoFabricacao", "anoModelo", "tipoVeiculo", "quilometragem", "statusVeiculo");

                ColumnPositionMappingStrategy<VeiculoRequest> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(VeiculoRequest.class);
                strategy.setColumnMapping(headerList.toArray(new String[0]));

                CSVReader csvReader = new CSVReader(reader);
                csvReader.readNext(); // Ignore a primeira linha (cabeçalho)

                CsvToBean<VeiculoRequest> csvToBean = new CsvToBeanBuilder<VeiculoRequest>(csvReader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

                this.veiculoIterator = csvToBean.iterator();
            }
        }
        return veiculoIterator.hasNext() ? veiculoIterator.next() : null;
    }
    
}
