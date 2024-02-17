package com.Ems.EmployeeManagementSystem.jwtModels;

import lombok.*;

@Builder
public class JwtResponse {
    private String jwtToken;
    private String userName;
    private String roles;

    private JwtResponse(String jwtToken, String userName, String roles) {
        this.jwtToken = jwtToken;
        this.userName = userName;
        this.roles = roles;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    public String getRoles() {
        return roles;
    }

    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }

    public static class JwtResponseBuilder {
        private String jwtToken;
        private String userName;
        private String roles;

        private JwtResponseBuilder() {

        }

        public JwtResponseBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }

        public JwtResponse build() {
            return new JwtResponse(jwtToken, userName, roles);
        }

        public JwtResponseBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public JwtResponseBuilder roles(String roles) {
            this.roles = roles;
            return this;
        }
    }
}
