package holenote.business.form;

public class QueryUserForm {
    
    private String username;
    private String district_code="000000";
    private int organization_id=0;
    private String realname;
    private String telephone;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getDistrict_code() {
        return district_code;
    }
    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }
    public int getOrganization_id() {
        return organization_id;
    }
    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
