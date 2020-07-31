package desquared.calculator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolderApi {

    @GET("convert")
    Call<List<ResultApiResponse>> getResult();
}
