package cloud.files;

import cn.hutool.system.SystemUtil;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/9/1 14:03
 */
public class PathUtil {

    public final static String SVG = "svg";
    public final static String SVG_LATER = "svg_later";


    /**
     * TODO Tomcat webapps/ 文件夹目录
     */
    public static String TOMCAT_WEBAPP_PATH() {
        return System.getProperty("catalina.home")
                .concat(SystemUtil.getOsInfo().getFileSeparator())
                .concat("webapps")
                .concat(SystemUtil.getOsInfo().getFileSeparator());
    }

    /**
     * TODO 获取webapp/omspdjx_upload/下的文件夹名字
     *
     * @param dir :
     * @return java.lang.String
     * @author xuhong.ding
     * @since 2021/9/1 14:11
     */
    public static String OMSPDJX_UPLOAD(String dir) {
        return TOMCAT_WEBAPP_PATH()
                .concat(dir)
                .concat(SystemUtil.getOsInfo().getFileSeparator());
    }

}
