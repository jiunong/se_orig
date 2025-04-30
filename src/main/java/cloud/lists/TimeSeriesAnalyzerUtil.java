package cloud.lists;

import java.util.*;

public class TimeSeriesAnalyzerUtil {
    // 定义时间序列类型枚举
    public enum TimeSeriesType {
        QUARTER_HOUR(96, 15),    // 15分钟一个点
        HOUR(24, 60),           // 1小时一个点
        MINUTE(1440, 1);        // 1分钟一个点

        private final int pointCount;
        private final int minuteInterval;

        TimeSeriesType(int pointCount, int minuteInterval) {
            this.pointCount = pointCount;
            this.minuteInterval = minuteInterval;
        }
    }

    // 分析结果类
    public static class AnalysisResult {
        private final String maxTime;
        private final String minTime;
        private final double maxValue;
        private final double minValue;
        private final TimeSeriesType type;

        public AnalysisResult(String maxTime, String minTime, double maxValue, double minValue, TimeSeriesType type) {
            this.maxTime = maxTime;
            this.minTime = minTime;
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.type = type;
        }

        public void printResult() {
            System.out.println(type.name() + "数据分析结果：");
            System.out.println("最大值时间点: " + maxTime + ", 数值: " + maxValue);
            System.out.println("最小值时间点: " + minTime + ", 数值: " + minValue);
        }
    }

    // 统一的分析方法
    public static AnalysisResult analyzeData(List<Map<String, Object>> dataList, TimeSeriesType type) {
        // 参数验证
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("数据列表不能为空");
        }

        // 使用 Stream API 处理数据
        double[] sumArray = new double[type.pointCount];
        dataList.stream().forEach(map -> {
            for (int i = 0; i < type.pointCount; i++) {
                String key = "v" + i;
                Object value = map.get(key);
                if (value != null) {
                    sumArray[i] += convertToDouble(value);
                }
            }
        });

        // 使用 Stream API 找出最大值和最小值
        int[] maxIndex = {0};
        int[] minIndex = {0};
        double[] maxValue = {sumArray[0]};
        double[] minValue = {sumArray[0]};

        for (int i = 1; i < sumArray.length; i++) {
            if (sumArray[i] > maxValue[0]) {
                maxValue[0] = sumArray[i];
                maxIndex[0] = i;
            }
            if (sumArray[i] < minValue[0]) {
                minValue[0] = sumArray[i];
                minIndex[0] = i;
            }
        }

        // 转换时间
        String maxTime = convertToTime(maxIndex[0], type);
        String minTime = convertToTime(minIndex[0], type);

        return new AnalysisResult(maxTime, minTime, maxValue[0], minValue[0], type);
    }

    // 统一的时间转换方法
    private static String convertToTime(int index, TimeSeriesType type) {
        switch (type) {
            case QUARTER_HOUR:
                int hours = index / 4;
                int minutes = (index % 4) * 15;
                return String.format("%02d:%02d", hours, minutes);
            case HOUR:
                return String.format("%02d:00", index);
            case MINUTE:
                int hour = index / 60;
                int minute = index % 60;
                return String.format("%02d:%02d", hour, minute);
            default:
                throw new IllegalArgumentException("不支持的时间序列类型");
        }
    }

    // 优化后的数值转换方法
    private static double convertToDouble(Object value) {
        try {
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("无法转换的数值类型: " + value, e);
        }
        throw new IllegalArgumentException("不支持的数据类型: " + value.getClass());
    }

    // 示例数据生成方法
    private static List<Map<String, Object>> createSampleData(TimeSeriesType type) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < type.pointCount; i++) {
            map.put("v" + i, Math.random() * 100);
        }
        dataList.add(map);

        return dataList;
    }

    /**
     * 将24点数据平铺为96点数据
     * @param hourlyData 24点数据列表
     * @return 96点数据列表
     */
    public static List<Map<String, Object>> expandHourlyToQuarterHour(List<Map<String, Object>> hourlyData) {
        // 参数验证
        if (hourlyData == null || hourlyData.isEmpty()) {
            throw new IllegalArgumentException("小时数据列表不能为空");
        }

        List<Map<String, Object>> expandedData = new ArrayList<>();

        for (Map<String, Object> hourMap : hourlyData) {
            Map<String, Object> expandedMap = new HashMap<>();

            // 遍历24个小时点
            for (int hour = 0; hour < 24; hour++) {
                String hourKey = "v" + hour;
                Object hourValue = hourMap.get(hourKey);

                if (hourValue != null) {
                    double value = convertToDouble(hourValue);
                    // 将每个小时的值复制到对应的4个15分钟时间点
                    for (int quarter = 0; quarter < 4; quarter++) {
                        int expandedIndex = hour * 4 + quarter;
                        expandedMap.put("v" + expandedIndex, value);
                    }
                }
            }

            expandedData.add(expandedMap);
        }

        return expandedData;
    }

    /**
     * 将96点数据(15分钟)平铺为1440点数据(1分钟)
     * @param quarterHourData 96点数据列表
     * @return 1440点数据列表，键格式为 DATA_HH_MM
     */
    public static List<Map<String, Object>> expandQuarterHourToMinute(List<Map<String, Object>> quarterHourData) {
        // 参数验证
        if (quarterHourData == null || quarterHourData.isEmpty()) {
            throw new IllegalArgumentException("15分钟数据列表不能为空");
        }

        List<Map<String, Object>> expandedData = new ArrayList<>();

        for (Map<String, Object> quarterMap : quarterHourData) {
            Map<String, Object> expandedMap = new HashMap<>();

            // 遍历96个15分钟点
            for (int quarterIndex = 0; quarterIndex < 96; quarterIndex++) {
                String quarterKey = "v" + quarterIndex;
                Object quarterValue = quarterMap.get(quarterKey);

                if (quarterValue != null) {
                    double value = convertToDouble(quarterValue);
                    // 计算对应的小时和分钟
                    int hour = quarterIndex / 4;
                    int baseMinute = (quarterIndex % 4) * 15;

                    // 将每个15分钟的值复制到对应的15个1分钟时间点
                    for (int minuteOffset = 0; minuteOffset < 15; minuteOffset++) {
                        int minute = baseMinute + minuteOffset;
                        String key = String.format("DATA_%d_%d", hour, minute);
                        expandedMap.put(key, value);
                    }
                }
            }

            expandedData.add(expandedMap);
        }

        return expandedData;
    }

    /**
     * 将24点数据直接平铺为1440点数据
     * @param hourlyData 24点数据列表
     * @return 1440点数据列表
     */
    public static List<Map<String, Object>> expandHourlyToMinute(List<Map<String, Object>> hourlyData) {
        // 先转换为96点
        List<Map<String, Object>> quarterHourData = expandHourlyToQuarterHour(hourlyData);
        // 再转换为1440点
        return expandQuarterHourToMinute(quarterHourData);
    }

    /**
     * 修改分析方法以支持新的键名格式
     */
    public static AnalysisResult analyzeMinuteData(List<Map<String, Object>> dataList) {
        // 参数验证
        if (dataList == null || dataList.isEmpty()) {
            throw new IllegalArgumentException("数据列表不能为空");
        }

        double[] sumArray = new double[1440];

        // 遍历所有数据
        for (Map<String, Object> map : dataList) {
            // 遍历24小时
            for (int hour = 0; hour < 24; hour++) {
                // 遍历每小时的60分钟
                for (int minute = 0; minute < 60; minute++) {
                    String key = String.format("DATA_%d_%d", hour, minute);
                    Object value = map.get(key);
                    if (value != null) {
                        int index = hour * 60 + minute;
                        sumArray[index] += convertToDouble(value);
                    }
                }
            }
        }

        // 找出最大值和最小值
        int maxIndex = 0;
        int minIndex = 0;
        double maxValue = sumArray[0];
        double minValue = sumArray[0];

        for (int i = 1; i < sumArray.length; i++) {
            if (sumArray[i] > maxValue) {
                maxValue = sumArray[i];
                maxIndex = i;
            }
            if (sumArray[i] < minValue) {
                minValue = sumArray[i];
                minIndex = i;
            }
        }

        // 转换时间
        String maxTime = convertMinuteIndexToTime(maxIndex);
        String minTime = convertMinuteIndexToTime(minIndex);

        return new AnalysisResult(maxTime, minTime, maxValue, minValue, TimeSeriesType.MINUTE);
    }

    /**
     * 将分钟索引转换为时间字符串
     * @param index 分钟索引 (0-1439)
     * @return 格式化的时间字符串 (HH:mm)
     */
    private static String convertMinuteIndexToTime(int index) {
        int hour = index / 60;
        int minute = index % 60;
        return String.format("%02d:%02d", hour, minute);
    }

    // 测试代码
    public static void main(String[] args) {
        try {
            // 创建96点测试数据
            List<Map<String, Object>> quarterHourData = createSampleData(TimeSeriesType.QUARTER_HOUR);
            List<Map<String, Object>> quarterHourData2 = createSampleData(TimeSeriesType.QUARTER_HOUR);
            quarterHourData.addAll(quarterHourData2);
            System.out.println("原始96点数据分析结果：");
            analyzeData(quarterHourData, TimeSeriesType.QUARTER_HOUR).printResult();

            // 将96点数据平铺为1440点
            List<Map<String, Object>> expandedToMinute = expandQuarterHourToMinute(quarterHourData);
            System.out.println("\n平铺后的1440点(分钟)数据分析结果：");
            analyzeMinuteData(expandedToMinute).printResult();

            // 打印部分数据示例
            Map<String, Object> firstMap = expandedToMinute.get(0);
            System.out.println("\n数据格式示例：");
            int count = 0;
            for (Map.Entry<String, Object> entry : firstMap.entrySet()) {
                if (count < 5) {  // 只打印前5个数据点作为示例
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                    count++;
                } else {
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("分析过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
