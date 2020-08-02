package desquared.calculator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceHolderApi {

    @GET("latest")
    Call<ApiResponse> getLatest(
            @Query("access_key") String apiKey);
}
