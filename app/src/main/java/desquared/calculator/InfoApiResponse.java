package desquared.calculator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoApiResponse {

    @SerializedName("timestamp")
    @Expose
    private int timestamp;
    @SerializedName("rate")
    @Expose
    private float rate;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

}
