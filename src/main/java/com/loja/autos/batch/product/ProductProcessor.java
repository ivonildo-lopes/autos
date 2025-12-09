package com.loja.autos.batch.product;

import org.springframework.batch.item.ItemProcessor;

/**
 * responsável por processar (ou transformar) os dados que estão sendo lidos antes que eles sejam gravados ou executados na próxima etapa do pipeline de batch.
 */
public class ProductProcessor implements ItemProcessor<Product,Product> {
    @Override
    public Product process(Product item) throws Exception {
        if(Long.valueOf(item.getPrice()) > 1000){
            return item;
        }
        return null;
    }
}