package desquared.calculator;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JsonLatestModel {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("timestamp")
    @Expose
    private int timestamp;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("rates")
    @Expose
    public JsonRatesModel rates;

    public JsonLatestModel(boolean success, int timestamp, String base, String date, JsonRatesModel rates) {
        this.success = success;
        this.timestamp = timestamp;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public JsonRatesModel getRates() {
        return rates;
    }

    public void setRates(JsonRatesModel rates) {
        this.rates = rates;
    }

    @Override
    @NonNull
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
