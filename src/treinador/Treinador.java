package treinador;

import java.io.IOException;

public abstract class Treinador {
	
	protected Double fator = 0.01;

	public abstract void treinar() throws NumberFormatException, IOException;

}
