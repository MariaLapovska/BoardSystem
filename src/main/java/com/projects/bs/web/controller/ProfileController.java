package com.projects.bs.web.controller;

import com.projects.bs.domain.Application;
import com.projects.bs.domain.User;
import com.projects.bs.service.ApplicationService;
import com.projects.bs.service.FacultyService;
import com.projects.bs.service.UserService;
import com.projects.bs.service.auth.SecurityService;
import com.projects.bs.web.dto.EditProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
        model.addAttribute("application", application);
        if (application != null && !application.getFaculty().isAvailable()) {
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
        model.addAttribute("editUserForm", new EditProfileDto());
        return "/profile/editProfile";
    }

    @GetMapping("/profile/delete")
    public String getDeleteProfilePage(Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        model.addAttribute("application", currentUser.getApplication());
        return "/profile/deleteProfile";
    }

    @PostMapping("/profile/edit")
    public String editProfile(@ModelAttribute("editUserForm") @Valid EditProfileDto editUserForm, BindingResult result, Principal principal, Model model) {
        User currentUser = userService.findByLogin(principal.getName());
        if (result.hasErrors() || !userService.passwordMatches(editUserForm.getOldPassword(), currentUser.getPassword())) {
            model.addAttribute("user", currentUser);
            model.addAttribute("editUserForm", new EditProfileDto());
            model.addAttribute("error", "wrongInput");
            return "/profile/editProfile";
        }
        currentUser = userService.saveUser(editUserForm.editUser(currentUser));
        String message = "changesSuccess";
        if (currentUser == null || currentUser.getId() == 0) {
            message = "changesError";
        }
        return "redirect:/profile?message=" + message;
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(HttpServletRequest request, HttpServletResponse response) {
        User currentUser = userService.findByLogin(request.getUserPrincipal().getName());
        userService.delete(currentUser.getId());
        securityService.autoLogout(request, response);
        return "redirect:/";
    }
}
