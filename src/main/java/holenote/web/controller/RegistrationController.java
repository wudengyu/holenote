package holenote.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import holenote.business.entities.District;
import holenote.business.entities.Organization;
import holenote.business.entities.User;
import holenote.business.form.RegistrationForm;
import holenote.business.repository.DistrictRepository;
import holenote.business.repository.OrganizationRepository;
import holenote.business.repository.UserRepository;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private DistrictRepository districtRepository;
        
    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute
    public List<District> districts(){
        return districtRepository.findByEnabledTrue();
    }

    @ModelAttribute
    public List<Organization> organizations(){
        return organizationRepository.findByEnabledTrue();
    }

    @RequestMapping
    public String registration(Model model) {
        model.addAttribute(new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String registration(@Valid RegistrationForm registrationForm,Errors errors){
        if(!errors.hasErrors()){
            User newuser=new User();
            newuser.setUsername(registrationForm.getUsername());
            newuser.setPassword(passwordEncoder.encode(registrationForm.getOriginalpassword()));
            newuser.setDistrict(new District(registrationForm.getDistrict_code()));
            newuser.setOrganization(new Organization(registrationForm.getOrganization_id()));
            newuser.setRealname(registrationForm.getRealname());
            newuser.setTelephone(registrationForm.getTelephone());
            try{
                userRepository.save(newuser);
            }catch(Exception e){
                errors.rejectValue("username", "save_fail", "保存失败，可能用户名已存在");
                return "manage/registration";
            }
            return "redirect:/";
        }
        return "registration";
    }

}
