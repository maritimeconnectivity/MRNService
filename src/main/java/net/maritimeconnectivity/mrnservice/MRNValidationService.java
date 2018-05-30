package net.maritimeconnectivity.mrnservice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MRNValidationService {

    public String getMrnMask(String nameSpace, String organizationMrn) {
        //e.g. "urn:mrn:mcl:<namespace>:<orgShortName>:"
        //if nameSpace=org then organizationMrn is not provided

        String orgShortName = getOrgShortName(organizationMrn);
        if (orgShortName.isEmpty())
            return "urn:mrn:mcl:" + nameSpace + ":";
        else
            return "urn:mrn:mcl:" + nameSpace + ":" + orgShortName + ":";

    }

    private String getValidatedType(MRN.Type type){
        return type.toString().toLowerCase();
    }

    public String getMrnMaskWithType(MRN.Type type, String nameSpace, String organizationMrn) {
        //for service artifacts (specification,design,instance) the mask will be "urn:mrn:mcl:service:<namespace>:<orgShortName>:"
        //if nameSpace=org then organizationMrn is not provided

        String orgShortName = getOrgShortName(organizationMrn);
        if (orgShortName.isEmpty())
            return "urn:mrn:mcl:"+ getValidatedType(type) + ":" + nameSpace + ":";
        else
            return "urn:mrn:mcl:"+ getValidatedType(type) + ":" + nameSpace + ":" + orgShortName + ":";

    }

    public Boolean checkMrnForNamespace(String nameSpace, String organizationMrn){
        /*
        examples of valid mrns (needs to follow the mrn-syntax from https://www.iana.org/assignments/urn-formal/mrn)
        vessel: "urn:mrn:mcp:vessel:dma:vesselid-123"
        specification: "urn:mrn:mcp:service:specification:dma:specid-123"
        instance: "urn:mrn:mcp:service:instance:dma:instanceid-123"

        if nameSpace=org then organizationMrn is not provided
        */
        return false;
    }

    public String getOrgShortName(String organizationMrn){
        if( organizationMrn.isEmpty())
            return "";
        else
            return organizationMrn;
    }
}
