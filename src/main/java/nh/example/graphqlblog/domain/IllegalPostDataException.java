package nh.example.graphqlblog.domain;

public class IllegalPostDataException extends Exception {

    private final String fieldName;
    private final String msg;

    public IllegalPostDataException(String fieldName, String msg) {
        this.fieldName = fieldName;
        this.msg = msg;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMsg() {
        return msg;
    }
}
