package holenote.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(pageable);
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";
    }

    @GetMapping("add")
    public String add(Model model,@RequestParam(defaultValue = "0") int pageNum,@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("organization", new Organization());
        return "manage/addorganization";
    }

    @PostMapping("edit")
    public String edit(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize, @RequestParam Long selectId) {
        Optional<Organization> organization = organizationRepository.findById(selectId);
        if (organization.isEmpty())
            return "redirect:/manage/listorganization";
        else {
            model.addAttribute("organization", organization.get());
            model.addAttribute("pageNum", pageNum);
            model.addAttribute("pageSize", pageSize);
            return "manage/editorganization";
        }
    }

    @PostMapping("create")
    public String create(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize, Organization organization) {
        int total = (int) organizationRepository.countByEnabledTrue();
        try {
            organizationRepository.save(organization);
            pageNum = total/pageSize;
        } catch (Exception e) {
            model.addAttribute("message", "添加失败");
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(pageable);
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";

    }

    @PostMapping("delete")
    public String delete(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize, @RequestParam Long selectId) {
        try {
            organizationRepository.deleteById(selectId);
        } catch (Exception e) {
            model.addAttribute("message", "删除失败");

        }
        int total = (int) organizationRepository.countByEnabledTrue();
        if(pageNum>(total-1)/pageSize)
            pageNum=(total-1)/pageSize;
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(pageable);
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";
    }

    @PostMapping("update")
    public String save(Model model, @RequestParam(defaultValue = "0") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize, Organization organization) {
        try {
            organizationRepository.save(organization);
        } catch (Exception e) {
            model.addAttribute("message", "修改失败");
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Organization> organizations = organizationRepository.findByEnabledTrue(pageable);
        model.addAttribute("organizations", organizations);
        return "manage/listorganization";
    }

}
