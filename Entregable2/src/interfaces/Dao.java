package interfaces;

import java.util.Optional;

public interface Dao<T> {
	
	// Optional es un tipo de dato que permite el manejo de null
	public Optional<T> getId(int id);
	
	// aca va el crud
}
