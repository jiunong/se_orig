package cloud.autoTest;

import cloud.annotion.SoaDataAnalysePowerDto;
import com.alibaba.fastjson.JSON;
import com.github.houbb.data.factory.core.util.DataUtil;

public class DataFactory {

    public static void main(String[] args) {
        SoaDataAnalyseUserDto build = DataUtil.build(SoaDataAnalyseUserDto.class);
        System.out.println(JSON.toJSONString(build,true));
    }

}
