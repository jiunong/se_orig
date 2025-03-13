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
public class SoaDataAnalyseUserDto implements Serializable {

    /** 主健 */
    private String userId;


    /** 城市ID */
    private String cityId;

    /** 城市名称 */
    private String cityName;


    /** 单位名称 */
    private String userName;

    /** 级别 */
    private String userLevel;

    /** 200kV主变ID */
    private String trans220Id;

    /** 200kV主变名 */
    private String trans220Name;

    /** 66kV线路ID */
    private String line66Id;

    /** 66kV线路名 */
    private String line66Name;

    /** 66kV主变ID */
    private String trans66Id;

    /** 660kV主变名 */
    private String trans66Name;

    /** 10kV线路ID */
    private String line10Id;

    /** 10kV线路名 */
    private String line10Name;
}
