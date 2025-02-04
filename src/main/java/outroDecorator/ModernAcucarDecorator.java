package outroDecorator;

public class ModernAcucarDecorator implements ModernIngredienteProcessor {

	@Override
	public void incluir() {
		System.out.println("açucar");
	}

}
