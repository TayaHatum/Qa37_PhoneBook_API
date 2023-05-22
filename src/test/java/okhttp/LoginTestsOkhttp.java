package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;


public class LoginTestsOkhttp {

    Gson gson=new Gson();
    public static final MediaType JSON=MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();


    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDTO auth = AuthRequestDTO.builder().username("noa@gmail.com").password("Nnoa12345$").build();
        RequestBody body= RequestBody.create(gson.toJson(auth),JSON);
        Request request= new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);

        AuthResponseDTO responseDTO = gson.fromJson(response.body().string(),AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());

    }
    @Test
    public void loginWrongEmail() throws IOException {
        AuthRequestDTO auth = AuthRequestDTO.builder().username("noagmail.com").password("Nnoa12345$").build();
        RequestBody body= RequestBody.create(gson.toJson(auth),JSON);
        Request request= new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);

        ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
        Assert.assertEquals(errorDTO.getStatus(),401);
        Assert.assertEquals(errorDTO.getMessage(),"Login or Password incorrect");
        Assert.assertEquals(errorDTO.getPath(),"/v1/user/login/usernamepassword");


    }
    @Test
    public void loginWrongPassword() throws IOException {
        AuthRequestDTO auth = AuthRequestDTO.builder().username("noa@gmail.com").password("Nnoa123").build();
        RequestBody body= RequestBody.create(gson.toJson(auth),JSON);
        Request request= new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);
        ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
        Assert.assertEquals(errorDTO.getMessage(),"Login or Password incorrect");


    }
    @Test
    public void loginUnregisteredUser() throws IOException {
        AuthRequestDTO auth = AuthRequestDTO.builder().username("mom@gmail.com").password("Nnoa12345$").build();
        RequestBody body= RequestBody.create(gson.toJson(auth),JSON);
        Request request= new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);
        ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
        Assert.assertEquals(errorDTO.getMessage(),"Login or Password incorrect");

    }
}
