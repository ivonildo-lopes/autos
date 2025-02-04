package outroDecorator;

public class CafeBase implements IngredienteProcessor {

	@Override
	public void incluir() {
		System.out.println("café");
	}

}
