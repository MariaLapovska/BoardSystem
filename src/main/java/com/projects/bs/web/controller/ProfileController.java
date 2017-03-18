package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.User;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.UserService;
import com.projects.bs.service.auth.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private SecurityService securityService;

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
    public String getEditProfilePage(Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        model.addAttribute("user", currentUser);
        model.addAttribute("userForm", new User());
        return "/profile/editProfile";
    }

    @GetMapping("/profile/delete")
    public String getDeleteProfilePage(Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        model.addAttribute("application", currentUser.getApplication());
        return "/profile/deleteProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(Principal principal, @ModelAttribute("userForm") User userForm) {
        User currentUser = userService.findByLogin(principal.getName());
        currentUser.setName(userForm.getName());
        currentUser.setSurname(userForm.getSurname());
        currentUser.setPassword(userForm.getNewPassword());
        userService.saveUser(currentUser);
        return "redirect:/profile";
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = userService.findByLogin(request.getUserPrincipal().getName());
        userService.delete(currentUser.getId());
        securityService.autoLogout(request, response);
        return "redirect:/";
    }
}
