package cloud.model;

import lombok.Builder;
import lombok.Data;

/**
 * TODO
 *
 * @author xuhong.ding
 * @since 2021/12/14 14:55
 */
@Data
@Builder
public class Te {
    private String pmsApplyNo;

    private String projectName;

    private String applyDeptId;

    private String applyCityId;

}
