package learnspring.business.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationForm {

    @Size(min=6,max=20,message = "用户名长度必须在6到20位之间")
    private String username;
    
    @Size(min=6,max=12,message = "密码长度必须在6到12位之间")
    private String originalpassword;

    
    @Size(min=6,max=6,message = "密码长度必须为6")
    private String district_code;
    private int organization_id;
    @NotNull(message = "真实姓名不能为空")
    @Size(min=2,message = "真实姓名长度必须大于2")
    private String realname;
    @Size(min=11,max=11,message = "联系电话长度必须为11位")
    private String telephone;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getOriginalpassword() {
        return originalpassword;
    }
    public void setOriginalpassword(String originalpassword) {
        this.originalpassword = originalpassword;
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
