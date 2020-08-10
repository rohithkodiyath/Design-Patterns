package converter;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;

public class SimpleAnnotationBasedConverter<M, D> implements Converter<M, D> {

	private HashMap<String, String> modelToEntityFieldMap = new HashMap<>();
	private Class<M> clazzM;
	private Class<D> clazzE;

	public SimpleAnnotationBasedConverter(Class<M> modelClazz,Class<D> entityClazz) {
		this.clazzM = modelClazz;
		this.clazzE = entityClazz;
		extractFieldFromClass();
	}

	private void extractFieldFromClass() {
		if (clazzM == null)
			return;
		Field[] fields = clazzM.getDeclaredFields();
		for (Field field : fields) {
			EntityMapper annotation = field.getAnnotation(EntityMapper.class);
			String entityFieldName = annotation == null ? field.getName() : annotation.name();
			String modelFieldName = field.getName();
			modelToEntityFieldMap.put(modelFieldName, entityFieldName);
		}
	}

	public D covertToEntity(M source) throws ConvertException {
		D target;
		try {
			if (source == null)
				return null;
			target = clazzE.newInstance();
			for (Entry<String, String> entry : modelToEntityFieldMap.entrySet()) {
				String modelFieldName = entry.getKey();
				String entityFieldName = entry.getValue();
				Field targetField = clazzE.getDeclaredField(entityFieldName);
				Field sourceField = clazzM.getDeclaredField(modelFieldName);
				if (targetField != null && sourceField != null) {
					sourceField.setAccessible(true);
					targetField.setAccessible(true);
					targetField.set(target, sourceField.get(source));
				}
			}
		} catch (InstantiationException e) {
			throw new ConvertException("Error Ocuured while creating object");
		} catch (IllegalAccessException e) {
			throw new ConvertException("Can not access field");
		} catch (NoSuchFieldException e) {
			throw new ConvertException("No field found with the name");
		} catch (SecurityException e) {
			throw new ConvertException("");
		} catch (IllegalArgumentException e) {
			throw new ConvertException("");
		}
		return target;
		
	}

	@Override
	public M convertToModel(D source) throws ConvertException {
		M target;
		try {
			if (source == null)
				return null;
			target = clazzM.newInstance();
			for (Entry<String, String> entry : modelToEntityFieldMap.entrySet()) {
				String modelFieldName = entry.getKey();
				String entityFieldName = entry.getValue();
				Field targetField = clazzM.getDeclaredField(entityFieldName);
				Field sourceField = clazzE.getDeclaredField(modelFieldName);
				if (targetField != null && sourceField != null) {
					sourceField.setAccessible(true);
					targetField.setAccessible(true);
					targetField.set(target, sourceField.get(source));
				}
			}
		} catch (InstantiationException e) {
			throw new ConvertException("Error Ocuured while creating object");
		} catch (IllegalAccessException e) {
			throw new ConvertException("Can not access field");
		} catch (NoSuchFieldException e) {
			throw new ConvertException("No field found with the name");
		} catch (SecurityException e) {
			throw new ConvertException("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new ConvertException("");
		}
		return target;
		
	}

	public Class<M> getClassOfModel() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		return (Class<M>) paramType.getActualTypeArguments()[0];
	}

	public Class<D> getClassOfEntity() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType paramType = (ParameterizedType) type;
		return (Class<D>) paramType.getActualTypeArguments()[1];
	}

	

}
