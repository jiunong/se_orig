package cloud.enums;

import java.util.Arrays;

public enum AreaEnum {

    SHENYANG("1", "2", "3"),
    LIAOYANG("1", "2", "3");

    private String aId;
    private String rId;
    private String cType;

    AreaEnum(String aId, String bId, String cId) {
        this.aId = aId;
        this.rId = bId;
        this.cType = cId;
    }

    public String getaId() {
        return aId;
    }

    public String getrId() {
        return rId;
    }

    public String getcType() {
        return cType;
    }

    public static AreaEnum getByAId(String aId) {
        return Arrays.stream(AreaEnum.values()).filter(u -> aId.equals(u.getaId())).findFirst().get();
    }

    public static AreaEnum getByCId(String cId) {
        return Arrays.stream(AreaEnum.values()).filter(u -> cId.equals(u.getrId())).findFirst().get();
    }

    public static AreaEnum getByTypeId(String typeId) {
        return Arrays.stream(AreaEnum.values()).filter(u -> typeId.equals(u.getcType())).findFirst().get();
    }
}
