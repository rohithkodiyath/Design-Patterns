package converter;

public class Client {

	public static void main(String[] args) throws ConvertException {
		
		Converter <UserModel,UserEntity> mapper = new SimpleAnnotationBasedConverter<UserModel,UserEntity>(UserModel.class,UserEntity.class);
		UserModel model = new UserModel("Rohith",20);
		UserEntity entity = mapper.covertToEntity(model);
		System.out.println(entity);
		
		entity = new UserEntity("Raju",40);
		model = mapper.convertToModel(entity);
		System.out.println(model);
		
		
	}

}
