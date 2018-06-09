package net.maritimeconnectivity.mrnservice;

public class MRNInput{
    String nameSpace;
    String organizationMrn;
    String type;

    public MRNInput(){

    }

    public MRNInput(String nameSpace, String organizationMrn, String type) {
        this.nameSpace = nameSpace;
        this.organizationMrn = organizationMrn;
        this.type = type;
    }

    public MRNInput(String nameSpace, String organizationMrn) {
        this.nameSpace = nameSpace;
        this.organizationMrn = organizationMrn;
        this.type = "";
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getOrganizationMrn() {
        return organizationMrn;
    }

    public void setOrganizationMrn(String organizationMrn) {
        this.organizationMrn = organizationMrn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
