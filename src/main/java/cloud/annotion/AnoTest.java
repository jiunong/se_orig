package cloud.annotion;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnoTest {

    private static Map<Class<?>, Field[]> fieldCache = new HashMap<>();

    public static void main(String[] args) {


        HashMap<String, Object> map = MapUtil.of("NUMBER", "1");
        map.put("ID","123");
        //map.put("TYPE","TYPE123");
        //map.put("TYPE1","TYPE123");
        HashMap<String, Object> map2 = MapUtil.of("NUMBER", "2");
        map2.put("ID","1233");
        map2.put("TYPE","1233");
        map2.put("TYPE1","TYPE1233");
        map2.put("NAME","TYPE1233");

        List<Map<String, Object>> of = ListUtil.of(map, map2);
        List<AdminUser> users = convertToList(of, AdminUser.class);
        users.stream().map(AdminUser::toString).forEach(System.out::println);
        System.out.println(1);
    }

    public static <T> List<T> convertToList(List<Map<String, Object>> mapList, Class<T> targetClass) {
        List<T> resultList = new ArrayList<>();

        for (Map<String, Object> map : mapList) {
            try {
                T instance = targetClass.getDeclaredConstructor().newInstance();
                // 获取目标类及其父类的所有字段
                Field[] fields = getAllFields(targetClass);
                for (Field field : fields) {
                    MapField annotation = field.getAnnotation(MapField.class);
                    if (annotation != null) {
                        // 处理带有注解的字段
                        field.setAccessible(true);
                        String mapKey = annotation.column();
                        String defaultValue = annotation.defaultValue();
                        Object mapValue = map.get(mapKey);
                        if (mapValue != null) {
                            field.set(instance, convertValue(mapValue, field.getType()));
                        }else {
                            field.set(instance, defaultValue);
                        }
                    } else {
                        System.err.println("字段 " + field.getName() + " 没有 @MapField 注解，跳过处理。");
                    }
                }
                resultList.add(instance);
            } catch (Exception e) {
                throw new RuntimeException("Convert failed", e);
            }
        }

        return resultList;
    }

    private static Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;

        // 基本类型转换
        if (targetType == String.class) {
            return value.toString();
        } else if (targetType == Integer.class || targetType == int.class) {
            return Integer.valueOf(value.toString());
        } else if (targetType == Long.class || targetType == long.class) {
            return Long.valueOf(value.toString());
        } else if (targetType == Double.class || targetType == double.class) {
            return Double.valueOf(value.toString());
        } else if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.valueOf(value.toString());
        } else if (targetType == Timestamp.class) {
            // 如果已经是Timestamp类型，直接返回
            if (value instanceof Timestamp) {
                return value;
            }
            // 如果是Long类型（毫秒时间戳），转换为Timestamp
            if (value instanceof Long || value.toString().matches("\\d+")) {
                long timestamp = Long.parseLong(value.toString());
                return new Timestamp(timestamp);
            }
            // 如果是日期字符串，尝试解析
            try {
                return Timestamp.valueOf(value.toString());
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Cannot convert to Timestamp: " + value, e);
            }
        }

        return value;
    }

    private static Field[] getAllFields(Class<?> targetClass) {
        if (fieldCache.containsKey(targetClass)) {
            return fieldCache.get(targetClass);
        }
        Field[] fields = combineFields(targetClass);
        fieldCache.put(targetClass, fields);
        return fields;
    }

    private static Field[] combineFields(Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        Field[] superFields = targetClass.getSuperclass().getDeclaredFields();
        Field[] allFields = new Field[fields.length + superFields.length];
        System.arraycopy(fields, 0, allFields, 0, fields.length);
        System.arraycopy(superFields, 0, allFields, fields.length, superFields.length);
        return allFields;
    }






}
