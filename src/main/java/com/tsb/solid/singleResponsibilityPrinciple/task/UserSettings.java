package com.tsb.solid.singleResponsibilityPrinciple.task;

/**
 * 1. Identify different responsibilities in the given code.
 * 2. Create a separate class for each responsibility
 * 3. And ensure that each class has only one reason to change.
 */
public class UserSettings {
    private User user;

    public UserSettings(User user) {
        this.user = user;
    }

    public void changeEmail(String newEmail) {
        if (checkAccess(user)) {
            user.setEmail(newEmail);
            System.out.println("Email changed");
        }
    }

    private boolean checkAccess(User user) {
        // logic to check if the user has access rights, ...
        return true;
    }
}

class User {
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
