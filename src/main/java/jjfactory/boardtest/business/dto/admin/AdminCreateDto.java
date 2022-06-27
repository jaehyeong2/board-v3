package jjfactory.boardtest.business.dto.admin;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AdminCreateDto {
    private String adminPhone;
    private String adminName;
    private String adminEmail;
    private String password;
}
