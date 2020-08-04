package desquared.calculator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceHolderApi {

    @GET("latest")
    Call<JsonLatestModel> getLatest(
            @Query("access_key") String access_key);
}
