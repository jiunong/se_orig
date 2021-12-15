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
public class FeederEnergyModel {
    private String energyId;
    private String energyName;
    private String stationId;
    private String feederId;
    private String feederName;
    private String stationIdB;
    private String contain;
}
