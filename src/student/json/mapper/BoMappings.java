package student.json.mapper;

import java.util.Date;

import test.Author;

public class BoMappings {

	public static Mappings getBoMappings(String type) {

		Mappings mappings = new Mappings();
/*		final MappingSource MAP = MappingSource.MAP;
*/
		final MappingSource MAP = MappingSource.JSON	;

		if (type.equals("student.data.Student")) {
			mappings.addMapping(MAP, "id", "id", String.class, 0, true);
			mappings.addMapping(MAP, "name", "name", String.class, 0, true);
			mappings.addMapping(MAP, "birthDate", "birthDate", Date.class, 0, true);
			mappings.addMapping(MAP, "joinDate", "joinDate", Date.class, 0, true);
			mappings.addMapping(MAP, "gender", "gender", String.class, 0, true);
			mappings.addMapping(MAP, "standard", "standard", Integer.class, 0, true);
			mappings.addMapping(MAP, "division", "division", String.class, 0, true);
			mappings.addMapping(MAP, "rollNumber", "rollNumber", Integer.class, 0, true);

			return mappings;
		} else if (type.equals("student.web.User")) {
			mappings.addMapping(MAP, "userId", "userId", String.class, 0, true);
			mappings.addMapping(MAP, "userName", "userName", String.class, 0, true);
			mappings.addMapping(MAP, "password", "password", String.class, 0, true);

			return mappings;
		}
		else if(type.equals("test.Book")) {
			mappings.addMapping(MAP,"id","id",Integer.class,0,true);
			mappings.addMapping(MAP, "name", "name", String.class, 0, true);
			mappings.addMapping(MAP, "author", "author", Author.class, 0, true);


		}
		else if(type.equals("test.Author")) {
			mappings.addMapping(MAP,"firstName","firstName",String.class,0,true);
			mappings.addMapping(MAP, "lastName", "lastName", String.class, 0, true);
		}

		return mappings;

	}

}
