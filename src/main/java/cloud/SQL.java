package cloud;

import java.util.Arrays;

public class SQL {


    public static void main(String[] args) {
        Long l = Long.valueOf("Asadea-98123XZ");
        StringBuffer sql = new StringBuffer();
        sql.append(
                " SELECT TO_CHAR(R_DATE,'YYYY-MM-DD') AS R_DATE,T.WATER,T.NUCLEAR,T.LOCAL,T.HOT,T.QKJ_DIFF,T.LINK_DIFF,LIMIT,");
        sql.append(" T.LINK_RATE,WATER_RATE,NUCLEAR_RATE,LOCAL_RATE,HOT_RATE,FIRE_RATE,LIMIT_RATE,QKJ_TYPE,DECODE(FSDL_DIFF,null,0) AS FSDL_DIFF,FSDL_TYPE ");
        sql.append(" FROM DMISLN_DATA.SG_SPACE_FINAL T");
        sql.append(" WHERE R_DATE<=TO_DATE($1,'YYYY-MM-DD') AND R_DATE>=TO_DATE($1,'YYYY-MM-DD') AND USERTYPE=$2");
        sql.append(" ORDER BY #3 DESC");

        System.out.println(convert(sql.toString(), "2024", "02", "R_DATE"));

    }


    public static String convert(String sql, String... values) {
        for (int i = 0; i < values.length; i++) {
            sql = sql.replace("$" + (i + 1), "'" + values[i] + "'");
            sql = sql.replace("#" + (i + 1), values[i]);
        }
        return sql;
    }


}
