package desquared.calculator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultApiResponse {

    public class Example {

        @SerializedName("success")
        @Expose
        private boolean success;
        @SerializedName("query")
        @Expose
        private ConvertApiResponse query;
        @SerializedName("info")
        @Expose
        private InfoApiResponse info;
        @SerializedName("historical")
        @Expose
        private String historical;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("result")
        @Expose
        private float result;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public ConvertApiResponse getQuery() {
            return query;
        }

        public void setQuery(ConvertApiResponse query) {
            this.query = query;
        }

        public InfoApiResponse getInfo() {
            return info;
        }

        public void setInfo(InfoApiResponse info) {
            this.info = info;
        }

        public String getHistorical() {
            return historical;
        }

        public void setHistorical(String historical) {
            this.historical = historical;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public float getResult() {
            return result;
        }

        public void setResult(float result) {
            this.result = result;
        }

    }
}
