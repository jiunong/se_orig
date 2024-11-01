package cloud.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MergeDataExample {
    List<DataModel> cachedData = new ArrayList<>();
    static Map<String, DataModel> mergedData = new LinkedHashMap<>();

    public static class DataModel {
        private String keyColumn; // 指定用于比较的列
        private String valueColumn; // 其他列的数据

        // Getters and Setters
        public String getKeyColumn() {
            return keyColumn;
        }

        public void setKeyColumn(String keyColumn) {
            this.keyColumn = keyColumn;
        }

        public String getValueColumn() {
            return valueColumn;
        }

        public void setValueColumn(String valueColumn) {
            this.valueColumn = valueColumn;
        }
    }
    public static class DataListener extends AnalysisEventListener<DataModel> {
        @Override
        public void invoke(DataModel data, AnalysisContext context) {
            if (mergedData.isEmpty()) {
                mergedData.put(data.getKeyColumn(), data);
            } else {
                String lastKey = mergedData.values().iterator().next().getKeyColumn();
                if (data.getKeyColumn().equals(lastKey)) {
                    // 合并逻辑，例如将valueColumn相加或其他操作
                    DataModel lastData = mergedData.get(lastKey);
                    lastData.setValueColumn("Merged Value"); // 示例合并逻辑
                } else {
                    mergedData.put(data.getKeyColumn(), data);
                }
            }
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 处理完所有数据后，可以在这里做额外的操作
        }

    }

    public static void main(String[] args) {
        String fileName = "input.xlsx";
        EasyExcel.read(fileName, DataModel.class, new DataListener()).sheet().doRead();

        // 假设mergedData已经被填充并且准备好写入新文件
        String outputFileName = "outputfile.xlsx";
        EasyExcel.write(outputFileName, DataModel.class)
            .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
            .sheet("SheetName")
            .doWrite(new ArrayList<>(mergedData.values()));
    }
}
