package holenote.web.controller.manage;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import holenote.business.entities.Organization;
import holenote.business.repository.OrganizationRepository;

@Controller
@RequestMapping("/manage/organization")
public class OrginizationManageController {

    @Autowired
    OrganizationRepository organizationRepository;

    @GetMapping
    public String list(Model model,@PageableDefault(size=10) Pageable pageable) {
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(pageable);
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";
    }

    @GetMapping("add")
    public String add(Model model,@PageableDefault(size=10) Pageable pageable) {
        model.addAttribute("pageable", pageable);
        model.addAttribute("organization", new Organization());
        return "manage/addorganization";
    }

    @PostMapping(path="edit",params = "selectId")
    public String edit(Model model,@PageableDefault(size=10) Pageable pageable, @RequestParam Long selectId) {
        Optional<Organization> organization = organizationRepository.findById(selectId);
        model.addAttribute("organization", organization.get());
        model.addAttribute("pageable", pageable);
        return "manage/editorganization";
    }

    @PostMapping("create")
    public String create(Model model,@RequestParam(defaultValue = "0") int size,Organization organization) {
        int total = (int) organizationRepository.countByEnabledTrue();
        int page=0;
        try {
            organizationRepository.save(organization);
            page = total/size;
        } catch (Exception e) {
            model.addAttribute("message", "添加失败");
        }
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(PageRequest.of(page, size));
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";

    }

    @PostMapping(path="delete",params = "selectId")
    public String delete(Model model,@PageableDefault(size=10) Pageable pageable, @RequestParam Long selectId) {
        int page=pageable.getPageNumber();
        int size=pageable.getPageSize();
        try {
            organizationRepository.deleteById(selectId);
        } catch (Exception e) {
            model.addAttribute("message", "删除失败");
        }
        int total = (int) organizationRepository.countByEnabledTrue();
        if(page>(total-1)/size)
            page=(total-1)/size;
        Page<Organization> organizations = organizationRepository.findByEnabledTrue( PageRequest.of(page, size));
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";
    }

    @PostMapping("update")
    public String save(Model model,@PageableDefault(size=10) Pageable pageable, Organization organization) {
        try {
            organizationRepository.save(organization);
        } catch (Exception e) {
            model.addAttribute("message", "修改失败");
        }
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(pageable);
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";
    }

}
