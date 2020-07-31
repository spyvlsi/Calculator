package desquared.calculator;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderApi {

    @GET("latest")
    Call<ApiResponse> getResult();
}
