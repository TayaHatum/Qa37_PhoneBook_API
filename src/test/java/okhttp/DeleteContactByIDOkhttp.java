package okhttp;

import com.google.gson.Gson;
import dto.DeleteByIdResponseDTO;
import dto.ErrorDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDOkhttp {
    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibm9hQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjg1MzczNDEwLCJpYXQiOjE2ODQ3NzM0MTB9.qSFQBQkMEGVHRvyaofm8b6ONJZeW4dHPdUlTcVVSqrg";
    Gson gson=new Gson();
    OkHttpClient client = new OkHttpClient();
    String id;


    @BeforeMethod
    public void precondition(){
        // create contact
        // get id from "message": "Contact was added! ID: 932c375d-1fb4-4255-be43-76ef37dabeec"
        // id="".
    }
    @Test
    public void deleteContactByIdSuccess() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/"+id)
                .delete()
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),200);
        DeleteByIdResponseDTO dto = gson.fromJson(response.body().string(), DeleteByIdResponseDTO.class);
        Assert.assertEquals(dto.getMessage(),"Contact was deleted!");
        System.out.println( dto.getMessage());

    }

    @Test
    public void deleteContactByIdWrongToken() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/d35dcb44-2498-4936-b9e8-482b87063a6e")
                .delete()
                .addHeader("Authorization","gfhf")
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),401);
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        Assert.assertEquals(errorDTO.getError(),"Unauthorized");


    }
    @Test
    public void deleteContactByIdNotFound() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/"+123)
                .delete()
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),400);
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        Assert.assertEquals(errorDTO.getError(),"Bad Request");
        Assert.assertEquals(errorDTO.getMessage(),"Contact with id: 123 not found in your contacts!");


    }


}




//8cc346ca-2dfa-4468-a490-cd820aa590b0
//        wow1884@gmail.com
//d35dcb44-2498-4936-b9e8-482b87063a6e
//        mia1621@mail.com
//f8c8d840-7c66-4036-b0ce-69a994cadb68
//        mia1123@mail.com
//41768523-5100-436d-b75e-5d037918b44b
//        wow1520@gmail.com
//6c52534f-de45-45dc-9b12-4f038d3aee4c
//        wow1790@gmail.com
//e5e2838b-d748-4f93-94cd-5ee6330b23d9
//        wow1632@gmail.com
