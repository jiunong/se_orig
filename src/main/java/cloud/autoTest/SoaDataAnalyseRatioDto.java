/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package cloud.autoTest;

import lombok.Data;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author ding
* @date 2024-12-16
**/
@Data
public class SoaDataAnalyseRatioDto implements Serializable {

    /** 主健 */
    private String ratioId;

    /** 批次ID */
    private String analyseId;

    /** 城市ID */
    private String cityId;

    /** 城市名称 */
    private String cityName;

    /** 年份 */
    private String rYear;

    /** 220kV容载比 */
    private String ratio220;

    /** 66kV容载比 */
    private String ratio66;

    /** 存在问题 */
    private String remark;

    /** 是否确认 */
    private String isConfirm;
}
