/*package student.json.mapper;

import java.util.*;

public class Mappings {

    private final Map<String, Mapping<?>> mappings = new LinkedHashMap<String, Mapping<?>>();

    public final <T> Mapping<T> addParamMapping(String formPropertyName, String boPropertyName, Class<T> type, int scale,
            boolean trim) {
        return addMapping(MappingSource.REQUEST_PARAMETER, formPropertyName, boPropertyName, type, scale, trim);
    }

    public final <T> Mapping<T> addFormMapping(String formPropertyName, String boPropertyName, Class<T> type, int scale,
            boolean trim) {
        return addMapping(MappingSource.FORM, formPropertyName, boPropertyName, type, scale, trim);
    }

    public final <T> Mapping<T> addMapping(MappingSource source, String formPropertyName, String boPropertyName,
            Class<T> type, int scale, boolean trim) {
        Mapping<T> mapping = new Mapping<T>(source, formPropertyName, boPropertyName, type, scale, trim);
        mappings.put(formPropertyName, mapping);
        return mapping;
    }

    public Collection<Mapping<?>> values() {
        return mappings.values();
    }

    public List<String> getPropertyNames() {
        return new ArrayList<String>(mappings.keySet());
    }

}*/