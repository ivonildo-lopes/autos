package outroDecorator;

public class ModernCafeBase implements ModernIngredienteProcessor {

	@Override
	public void incluir() {
		System.out.println("café");
	}

}
