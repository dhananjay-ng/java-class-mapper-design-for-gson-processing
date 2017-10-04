package student.json.mapper;

import java.util.HashMap;
import java.util.Map;

public class Mapping<T> {
    final MappingSource source;
    final String jsonPropertyName;
    final String boPropertyName;
    final Class<T> type;
    final boolean trim;
    boolean skipBlanks = false;
    final int scale;
    MappingDirection direction = MappingDirection.BOTH;

    public Mapping<T> setDirection(MappingDirection direction) {
        this.direction = direction;
        return this;
    }

    public Mapping<T> setOutputOnly() {
        return setDirection(MappingDirection.TO_JSON_ONLY);
    }

    public Mapping<T> setInputOnly() {
        return setDirection(MappingDirection.TO_BO_ONLY);
    }

    public Mapping<T> skipBlanks() {
        this.skipBlanks = true;
        return this;
    }

    final Map<String, MappingMessage> messages = new HashMap<String, MappingMessage>();

    public Mapping(MappingSource source, String jsonPropertyName, String boPropertyName, Class<T> type, int scale,
            boolean trim) {
        this.source = source;
        this.jsonPropertyName = jsonPropertyName;
        this.boPropertyName = boPropertyName;
        this.type = type;
        this.scale = scale;
        this.trim = trim;
    }

   /* public Mapping<T> setInvalid(String section, String key, String... args) {
        return add(MessageName.INVALID, new MappingMessage(section, key, args));
    }

    public Mapping<T> setRequired(String section, String key, String... args) {
        return add(MessageName.REQUIRED, new MappingMessage(section, key, args));
    }

    public Mapping<T> add(MessageName validation, MappingMessage message) {
        messages.put(validation.name(), message);
        return this;
    }*/
}
