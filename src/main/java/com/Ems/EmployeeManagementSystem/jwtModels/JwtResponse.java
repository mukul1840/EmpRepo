package com.Ems.EmployeeManagementSystem.jwtModels;

import lombok.*;
@Builder
public class JwtResponse {
    private String jwtToken;
    private String userName;

    // private constructor to force the use of the builder
    private JwtResponse(String jwtToken, String userName) {
        this.jwtToken = jwtToken;
        this.userName = userName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getUserName() {
        return userName;
    }

    // Manual builder method
    public static JwtResponseBuilder builder() {
        return new JwtResponseBuilder();
    }

    // Inner builder class
    public static class JwtResponseBuilder {
        private String jwtToken;
        private String userName;

        private JwtResponseBuilder() {
            // private constructor to force the use of the outer class builder() method
        }

        public JwtResponseBuilder jwtToken(String jwtToken) {
            this.jwtToken = jwtToken;
            return this;
        }



        public JwtResponse build() {
            return new JwtResponse(jwtToken, userName);
        }

        public JwtResponseBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }
    }
}
