package org.researchsuite.pamdemo.RSRPBackendSupport;

import android.support.annotation.Nullable;

import org.researchstack.backbone.result.StepResult;
import org.researchsuite.rsrp.Core.RSRPFrontEndServiceProvider.spi.RSRPFrontEnd;
import org.researchsuite.rsrp.Core.RSRPIntermediateResult;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by christinatsangouri on 4/17/18.
 */

public class DemographicsResult extends RSRPIntermediateResult {

    public static String TYPE = "DemographicsResult";

    private String icecream;
    private String food;


    public DemographicsResult(UUID uuid, String taskIdentifier, UUID taskRunUUID, String icecream, String food) {
        super(TYPE, uuid, taskIdentifier, taskRunUUID);

        this.icecream = icecream;
        this.food = food;
    }

    public String getIcecream() {
        return icecream;
    }
    public String getFood(){
        return food;
    }

}