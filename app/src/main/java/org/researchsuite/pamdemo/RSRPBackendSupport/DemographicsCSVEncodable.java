package org.researchsuite.pamdemo.RSRPBackendSupport;

import org.apache.commons.lang3.StringUtils;
import org.researchsuite.rsrp.CSVBackend.CSVEncodable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Created by christinatsangouri on 4/20/18.
 */

public class DemographicsCSVEncodable extends DemographicsResult implements CSVEncodable {

    public static String TYPE = "DemographicsCSVEncodable";

    public DemographicsCSVEncodable(UUID uuid, String taskIdentifier, UUID taskRunUUID, String icecream, String food) {
        super(uuid, taskIdentifier, taskRunUUID, icecream, food);
    }

    @Override
    public String[] toRecords() {

        String record = getTimestamp() + "," + this.getIcecream() + "," + this.getFood();
        String[] completeRecordArray = new String[]{record};

        return completeRecordArray;
    }

    @Override
    public String getTypeString() {
        return this.getTaskIdentifier();
    }

    @Override
    public String getHeader() {
        String[] header = new String[]{"timestamp","icecream","food"};
        String headerJoined = StringUtils.join(header,",");
        return headerJoined;
    }

    private String getTimestamp () {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int zone = calendar.get(Calendar.ZONE_OFFSET);

        StringBuilder timestampBuilder = new StringBuilder();
        timestampBuilder.append(year);
        timestampBuilder.append("-");
        timestampBuilder.append(month);
        timestampBuilder.append("-");
        timestampBuilder.append(date);
        timestampBuilder.append("T");
        timestampBuilder.append(hour);
        timestampBuilder.append(":");
        timestampBuilder.append(minute);
        timestampBuilder.append(":");
        timestampBuilder.append(second);
        timestampBuilder.append("-");
        timestampBuilder.append(zone);

        String timestamp = timestampBuilder.toString();

        return timestamp;
    }
}
