package converter;

public interface Converter <M,D> {
	
	D covertToEntity (M model) throws ConvertException;
	
	M convertToModel (D entity) throws ConvertException;
}
