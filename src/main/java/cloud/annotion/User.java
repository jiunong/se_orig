package cloud.annotion;

import lombok.Data;

// 定义一个实体类
@Data
public class User {
    @MapField(column = "ID")
    private String id;

    @MapField(column = "NUMBER")
    private String number;

    @MapField(column = "NAME",defaultValue = "测试账号")
    private String name;



    // getter和setter方法
}

