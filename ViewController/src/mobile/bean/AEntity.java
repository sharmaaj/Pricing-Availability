package mobile.bean;

public class AEntity {
    public AEntity() {
        super();
    }
    
    protected String getAttributeValue(String value) {
        if (value.contains("@xsi")) {
            return "";
        }
        return value;
    }
    
    public String getValue(String value) {
        if (value.contains("@nil")) {
            return "";
        }
        return value;
    }
}
