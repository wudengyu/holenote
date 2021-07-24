package holenote.web.controller.manage;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import holenote.business.entities.User;
import holenote.business.form.ChangePasswordForm;
import holenote.business.repository.UserRepository;

@Controller
@RequestMapping("manage")
public class PasswordManageController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("changepassword")
    public String showform(Model model){
        model.addAttribute(new ChangePasswordForm());
        return "manage/changepassword";
    }

    @PostMapping("changepassword")
    public String changepassword(Principal currentuser,@Valid ChangePasswordForm changepasswordform, BindingResult result) {
        if(!result.hasErrors()){
            User user=userRepository.findByUsername(currentuser.getName());
            if(!passwordEncoder.matches(changepasswordform.getOldpassword(),user.getPassword())){
                result.addError(new FieldError("changepasswordform","oldpassword","旧密码错误"));
            }
            if(!changepasswordform.getNewpassword().equals(changepasswordform.getConfirmpassword())){
                result.addError(new FieldError("changepasswordform","newpassword","两次密码不一致"));
            }
        }
        if(!result.hasErrors()){
            User user=userRepository.findByUsername(currentuser.getName());
            user.setPassword(passwordEncoder.encode(changepasswordform.getNewpassword()));
            try{
                userRepository.save(user);
            }catch(Exception e){
                result.addError(new ObjectError("changepasswordform","保存失败"));
            }
        }
        if(result.hasErrors()){
            return "manage/changepassword";
        }
        else
            return "redirect:/logout";
    }

    
}
