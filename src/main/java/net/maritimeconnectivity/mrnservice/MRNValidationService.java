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
        else{
            String[] tokens = stripMRN(organizationMrn).split(":");
            int orgIdx = -1;
            for(int i=0; i< tokens.length ; i++)
            {
                if(tokens[i].equals("org")) {
                    orgIdx = i + 1;
                    break;
                }
            }
            if(orgIdx>=0 && orgIdx < tokens.length)
                return tokens[orgIdx];
            else
                return "";
        }

    }

    private boolean checkType(String typeString){
        if(typeString.equals(MRN.Type.org.toString()) ||
                typeString.equals(MRN.Type.design.toString()) ||
                typeString.equals(MRN.Type.device.toString()) ||
                typeString.equals(MRN.Type.user.toString()) ||
                typeString.equals(MRN.Type.vessel.toString()) ||
                typeString.equals(MRN.Type.specification.toString()))
            return true;
        else
            return false;
    }

    //////////////////// not implemented yet ////////////////////
    private boolean checkOrganization(String orgString){
        return true;
    }
    /////////////////////////////////////////////////////////////

    private String stripMRN(String mrnString){
        return mrnString.replaceAll("^\"+", "").replaceAll("\"+$", "");
    }

    public boolean checkMRNValidity(String mrnString){
       String[] tokens = stripMRN(mrnString).split(":");
       if(tokens.length<=4)
           return false;
       if(!tokens[0].equals("urn"))
           return false;
       if(!tokens[1].equals("mrn"))
           return false;

       if(!tokens[2].equals("mcl"))
           return false;
       if(!checkType(tokens[3]))
           return false;
       if(!checkOrganization(tokens[4]))
           return false;
       return true;
    }
}
