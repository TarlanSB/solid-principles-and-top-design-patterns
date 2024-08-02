package com.tsb.solid.singleResponsibilityPrinciple.solution;

public class UserSettings {
    private User user;
    private SecurityService security;

    public UserSettings(User user, SecurityService security) {
        this.user = user;
        this.security = security;
    }

    public void changeEmail(String newEmail) {
        if (security.hasAccess(user)){
            user.setEmail(newEmail);
            System.out.println("Email changed");
        } else {
            System.out.println("Access denied");
        }
    }
}
