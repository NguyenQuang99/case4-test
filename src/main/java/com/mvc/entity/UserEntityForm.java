package com.mvc.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserEntityForm {
    private Long id;
    private String userName;
    private String encrytedPassword;
    private boolean active;
    private String userRole;
    private MultipartFile image;

    public UserEntityForm () {

    }

    public UserEntityForm(UserEntityFormBuilder userEntityFormBuilder) {
        this.userName = userEntityFormBuilder.userName;
        this.encrytedPassword = userEntityFormBuilder.encrytedPassword;
        this.image = userEntityFormBuilder.image;
        this.active = userEntityFormBuilder.active;
        this.userRole = userEntityFormBuilder.userRole;;
    }

    public static class UserEntityFormBuilder {
        private final String userName;
        private String encrytedPassword;
        private boolean active;
        private String userRole;
        private MultipartFile image;

//        public String getUserName() {
//            return userName;
//        }
//
//        public String getEncrytedPassword() {
//            return encrytedPassword;
//        }
//
//        public void setEncrytedPassword(String encrytedPassword) {
//            this.encrytedPassword = encrytedPassword;
//        }
//
//        public boolean isActive() {
//            return active;
//        }
//
//        public void setActive(boolean active) {
//            this.active = active;
//        }
//
//        public String getUserRole() {
//            return userRole;
//        }
//
//        public void setUserRole(String userRole) {
//            this.userRole = userRole;
//        }
//
//        public MultipartFile getImage() {
//            return image;
//        }
//
//        public void setImage(MultipartFile image) {
//            this.image = image;
//        }

        public UserEntityFormBuilder(String userName) {
            this.userName = userName;
        }

        public UserEntityFormBuilder encrytedPassword(String encrytedPassword) {
            this.encrytedPassword = encrytedPassword;
            return this;
        }

        public UserEntityFormBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public UserEntityFormBuilder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserEntityFormBuilder image(MultipartFile image) {
            this.image = image;
            return this;
        }

        public UserEntityForm build() {
            return new UserEntityForm(this);
        }


    }

//    public Long getId() {
//        return id;
//    }
//
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getEncrytedPassword() {
//        return encrytedPassword;
//    }
//
//    public void setEncrytedPassword(String encrytedPassword) {
//        this.encrytedPassword = encrytedPassword;
//    }
//
//    public boolean isActive() {
//        return active;
//    }
//
//    public void setActive(boolean active) {
//        this.active = active;
//    }
//
//    public String getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(String userRole) {
//        this.userRole = userRole;
//    }
//
//    public MultipartFile getImage() {
//        return image;
//    }
//
//    public void setImage(MultipartFile image) {
//        this.image = image;
//    }
}
