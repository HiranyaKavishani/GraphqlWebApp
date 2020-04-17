package test.graphql;

public class Language {
    private String name;
    private String code;

    Language(String name, String code) {
        this.name = name;
        this.code = code;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCode(String code) {
        this.code = code;
    }

    private String getName() {
       return name;
    }

    private String getCode() {
        return code;
    }
}
