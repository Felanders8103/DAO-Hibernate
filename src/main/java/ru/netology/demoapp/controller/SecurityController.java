package ru.netology.demoapp.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecurityController {
    @Secured("READ")
    @GetMapping("/secure/read")
    public String readData() {
        return "Только для READ";
    }

    @RolesAllowed("WRITE")
    @GetMapping("/secure/write")
    public String writeData() {
        return "Только для WRITE";
    }

    @PreAuthorize("hasAuthority('WRITE') or hasAuthority('DELETE')")
    @GetMapping("/secure/write-or-delete")
    public String writeOrDelete() {
        return "Доступно для WRITE и DELETE";
    }

    @GetMapping("/secure/me")
    public String getMe(@RequestParam String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        if (currentUser.equals(username)) {
            return "Привет, " + username + "! Это твои данные.";
        } else {
            return "Отказано в доступе";
        }
    }
}
