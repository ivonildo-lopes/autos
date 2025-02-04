package outroDecorator;

public class Teste {

	public static void main(String[] args) {
//		IngredienteProcessor cafe = new CafeBase();
//		IngredienteProcessor acucar = new AcucarDecorator(cafe);
//		
//		IngredienteProcessor pipeline = acucar;
//		
//		pipeline.incluir();
		
		
		// test modern
		ModernIngredienteProcessor coffee = new ModernCafeBase();
		ModernIngredienteProcessor sugar = new ModernAcucarDecorator();
		
		ModernIngredienteProcessor processors = () -> {};
		
		processors = processors.andThen(coffee).andThen(sugar);
		
		processors.incluir();

	}

}
