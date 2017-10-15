package student.json.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import test.Author;

public class Mappings {

    private final Map<String, Mapping<?>> mappings = new LinkedHashMap<String, Mapping<?>>();

    public final <T> Mapping<T> addParamMapping(String jsonPropertyName, String boPropertyName, Class<T> type, int scale,
            boolean trim) {
        return addMapping(MappingSource.REQUEST_PARAMETER, jsonPropertyName, boPropertyName, type, scale, trim);
    }

    public final <T> Mapping<T> addJsonMapping(String jsonPropertyName, String boPropertyName, Class<T> type, int scale,
            boolean trim) {
        return addMapping(MappingSource.JSON, jsonPropertyName, boPropertyName, type, scale, trim);
    }

    public final <T> Mapping<T> addMapping(MappingSource source, String jsonPropertyName, String boPropertyName,
            Class<T> type, int scale, boolean trim) {
        Mapping<T> mapping = new Mapping<T>(source, jsonPropertyName, boPropertyName, type, scale, trim);
        mappings.put(jsonPropertyName, mapping);
        return mapping;
    }
    public final <T> Mapping<T> addMappingWithSubType(MappingSource source, String jsonPropertyName, String boPropertyName,
            Class<T> type, int scale, boolean trim,Class<?> subType) {
        Mapping<T> mapping = new Mapping<T>(source, jsonPropertyName, boPropertyName, type, scale, trim,subType);
        mappings.put(jsonPropertyName, mapping);
        return mapping;
    }

    public Collection<Mapping<?>> values() {
        return mappings.values();
    }

    public List<String> getPropertyNames() {
        return new ArrayList<String>(mappings.keySet());
    }

}