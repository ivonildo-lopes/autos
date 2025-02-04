package outroDecorator;

public abstract class AdicionaIngrediente implements IngredienteProcessor {
	
	protected final IngredienteProcessor wrapper;
	
	public AdicionaIngrediente(IngredienteProcessor wrapper) {
		this.wrapper = wrapper;
	}
	
	
	@Override
	public void incluir() {
		wrapper.incluir();
	}

}
