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
public class SoaDataAnalyseProjectDto implements Serializable {

    /** 主健 */
    private String projectId;

    /** 批次ID */
    private String analyseId;

    /** 城市ID */
    private String cityId;

    /** 城市名称 */
    private String cityName;

    /** 年份 */
    private String rYear;

    /** 工程名称 */
    private String projectName;

    /** 解决问题 */
    private String deal;

    /** 增加变电容量 */
    private String cap;

    /** 实际增加供电能力 */
    private String power;

    /** 是否城市中心区 */
    private String area;

    /** 投产时间 */
    private String onDate;

    /** 类别 */
    private String rType;

    /** 是否确认 */
    private String isConfirm;
}
