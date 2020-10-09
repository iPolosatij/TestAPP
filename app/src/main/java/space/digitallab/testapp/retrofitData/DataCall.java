package space.digitallab.testapp.retrofitData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataCall {

    @GET("https://reqres.in/api/users")
    Call <Repo> messages();

}