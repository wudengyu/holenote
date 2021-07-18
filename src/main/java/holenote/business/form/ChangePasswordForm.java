package holenote.business.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ChangePasswordForm {
    
    @NotNull(message="密码不能为空")
    private String oldpassword;

    @NotNull(message="密码不能为空")
    @Size(min=6,max=12,message = "密码长度必须在6到12位之间")
    private String newpassword;

    @NotNull(message="密码不能为空")
    @Size(min=6,max=12,message = "密码长度必须在6到12位之间")
    private String confirmpassword;

    public String getOldpassword() {
        return oldpassword;
    }
    
    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }
    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
    
    public String getConfirmpassword() {
        return confirmpassword;
    }
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    
}
