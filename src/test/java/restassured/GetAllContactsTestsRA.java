package restassured;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTestsRA {
    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibm9hQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjg1MzczNDEwLCJpYXQiOjE2ODQ3NzM0MTB9.qSFQBQkMEGVHRvyaofm8b6ONJZeW4dHPdUlTcVVSqrg";
    @BeforeMethod
    public void  preCondition(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";

    }

    @Test
    public void getAllContactsSuccess(){

        GetAllContactsDTO contactsDTO =given()
                .header("Authorization",token)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(GetAllContactsDTO.class);


       List<ContactDTO> list =contactsDTO.getContacts();
        for (ContactDTO contact:list) {
            System.out.println(contact.getId());
            System.out.println(contact.getEmail());
            System.out.println("Size of list -->" +list.size());

        }

    }
    @Test
    public void getAllContactWrongToken(){

    }
}
