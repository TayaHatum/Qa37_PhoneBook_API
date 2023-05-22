package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
//{
//        "username": "string",
//        "password": "string"
//        }
public class AuthRequestDTO {
    private String username;
    private String password;
}
