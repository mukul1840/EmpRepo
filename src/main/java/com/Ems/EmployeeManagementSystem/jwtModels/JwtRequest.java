package com.Ems.EmployeeManagementSystem.jwtModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {
   private String email;
   private String password;

    public String getPassword() {
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
}
