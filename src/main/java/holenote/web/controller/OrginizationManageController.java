package holenote.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage/orginization")
public class OrginizationManageController {
    @GetMapping
    public String list(){
        return "manage/orginization";
    }
    
}
