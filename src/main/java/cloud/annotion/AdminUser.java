package cloud.annotion;


import lombok.Data;

@Data
public class AdminUser extends User{

    @MapField(column = "TYPE",defaultValue = "ADMIN_TYPE")
    private String type;

    @Override
    public String toString() {
        return "AdminUser{" +
                "type='" + type + '\'' +
                "id='" + super.getId() + '\'' +
                "number='" + super.getNumber() + '\'' +
                "name='" + super.getName() + '\'' +
                '}';
    }
}
