package com.mvc.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Entity

@Table(name = "Accounts")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "User_Name", length = 20, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "Active", length = 1, nullable = false)
    private boolean active;

    @Column(name = "User_Role", length = 20, nullable = false)
    private String userRole;

    public UserEntity(UserEntityBuilder userEntityBuilder) {
        this.userName = userEntityBuilder.userName;
        this.encrytedPassword = userEntityBuilder.encrytedPassword;
        this.active = userEntityBuilder.active;
        this.userRole = userEntityBuilder.userRole;
        this.image = userEntityBuilder.image;
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

    @Override
    public String toString() {
        return "[" + this.userName + "," + this.encrytedPassword + "," + this.userRole + "]";
    }

    // -----------

    @Column(name="image")
    private String image;

    public UserEntity() {}

    public static class UserEntityBuilder {
        private final String userName;
        private String encrytedPassword;
        private boolean active;
        private String userRole;
        private String image;

//        public UserEntityBuilder() {
//        }

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
//        public String getImage() {
//            return image;
//        }
//
//        public void setImage(String image) {
//            this.image = image;
//        }
//
        public UserEntityBuilder(String userName) {
            this.userName = userName;
        }

        public UserEntityBuilder encrytedPassword(String encrytedPassword) {
            this.encrytedPassword = encrytedPassword;
            return this;
        }

        public UserEntityBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public UserEntityBuilder userRole(String userRole) {
            this.userRole = userRole;
            return this;
        }

        public UserEntityBuilder image(String image) {
            this.image  = image;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }

    }

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
}