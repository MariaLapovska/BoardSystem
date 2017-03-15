package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.User;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/profile")
    public String getProfilePage(Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());

        if (currentUser.getRole() == User.Role.USER) {
            return getUserProfilePage(currentUser, model);
        } else if (currentUser.getRole() == User.Role.ADMIN) {
            return getAdminProfilePage(currentUser, model);
        } else {
            return "redirect:/error";
        }
    }

    private String getAdminProfilePage(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("faculties", facultyService.findByIsAvailable(true));
        return "/admin/profile";
    }

    private String getUserProfilePage(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("faculties", facultyService.findByIsAvailable(true));

        Application application = applicationService.findByUser(user);
        if (application != null) {
            model.addAttribute("application", application);
            List<Application> applications = applicationService.findByFaculty(application.getFaculty());
            model.addAttribute("applicationNo", applications.indexOf(application));
            model.addAttribute("totalApplicationNo", applications.size());
        }
        return "/user/profile";
    }

    @GetMapping("/profile/edit")
    public String getEditProfilePage() {
        return "/profile/editProfile";
    }

    @GetMapping("/profile/delete")
    public String getDeleteProfilePage() {
        return "/profile/deleteProfile";
    }

    @PostMapping("/profile/edit")
    public void editProfile() {

    }

    @PostMapping("/profile/delete")
    public  void deleteProfile() {

    }
}
