package student.json.mapper;

import java.util.HashMap;
import java.util.Map;

public class Mapping<T> {
    public boolean isSkipBlanks() {
		return skipBlanks;
	}

	public void setSkipBlanks(boolean skipBlanks) {
		this.skipBlanks = skipBlanks;
	}

	public MappingSource getSource() {
		return source;
	}

	public String getJsonPropertyName() {
		return jsonPropertyName;
	}

	public String getBoPropertyName() {
		return boPropertyName;
	}

	public Class<T> getType() {
		return type;
	}

	public boolean isTrim() {
		return trim;
	}

	public int getScale() {
		return scale;
	}

	public MappingDirection getDirection() {
		return direction;
	}

	public Map<String, MappingMessage> getMessages() {
		return messages;
	}

	final MappingSource source;
    final String jsonPropertyName;
    final String boPropertyName;
    final Class<T> type;
    final boolean trim;
    boolean skipBlanks = false;
    final int scale;
     Class<?> subType=null;

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
            boolean trim,Class<?> subType) {
        this.source = source;
        this.jsonPropertyName = jsonPropertyName;
        this.boPropertyName = boPropertyName;
        this.type = type;
        this.scale = scale;
        this.trim = trim;
        this.subType=subType;
    }
    public Mapping(MappingSource source, String jsonPropertyName, String boPropertyName, Class<T> type, int scale,
            boolean trim) {
        this.source = source;
        this.jsonPropertyName = jsonPropertyName;
        this.boPropertyName = boPropertyName;
        this.type = type;
        this.scale = scale;
        this.trim = trim;
    }
  }
