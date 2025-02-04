package outroDecorator;

public class AcucarDecorator extends AdicionaIngrediente {

	public AcucarDecorator(IngredienteProcessor wrapper) {
		super(wrapper);
	}
	
	@Override
	public void incluir() {
		System.out.println("com açucar");
		super.incluir();
	}

}
