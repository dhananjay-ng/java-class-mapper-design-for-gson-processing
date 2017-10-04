package student.json.mapper;

public class MappingMessage {
    final String section;
    final String key;
    final String[] args;

    public MappingMessage(String section, String key, String[] args) {
        this.section = section;
        this.key = key;
        this.args = args;
    }

}
