package outroDecorator;

@FunctionalInterface
public interface ModernIngredienteProcessor {
	
	void incluir();
	
	// compor processadores - decorators
	
	default ModernIngredienteProcessor andThen(ModernIngredienteProcessor next) {
		return () -> {
			this.incluir();
			next.incluir();
		};
	}

}


