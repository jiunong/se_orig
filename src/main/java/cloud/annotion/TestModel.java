package cloud.annotion;

public class TestModel {


    @ResultMapping(column = "ID",javaType = "String")
    private String id;

    @ResultMapping(column = "NUMBER",javaType = "Integer")
    private int num;

}
