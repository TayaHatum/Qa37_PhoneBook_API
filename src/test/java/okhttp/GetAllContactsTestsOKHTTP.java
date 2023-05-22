package okhttp;

import com.google.gson.Gson;
import dto.ErrorDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContactsTestsOKHTTP {
    String token ="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibm9hQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNjg1MzczNDEwLCJpYXQiOjE2ODQ3NzM0MTB9.qSFQBQkMEGVHRvyaofm8b6ONJZeW4dHPdUlTcVVSqrg";
    Gson gson=new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void getAllContactSuccess() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .get()
                .addHeader("Authorization",token)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);




    }
    @Test
    public void getAllContactWrongToken() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .get()
                .addHeader("Authorization","gcgf")
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),401);
        ErrorDTO errorDTO =gson.fromJson(response.body().string(),ErrorDTO.class);
        Assert.assertEquals(errorDTO.getError(),"Unauthorized");
    }
}
