package mobile.bean;

public class AEntity {
    public AEntity() {
        super();
    }
    
    public String getAttributeValue(String value) {
        if (value.contains("@xsi")) {
            return "";
        }
        return value;
    }
}
