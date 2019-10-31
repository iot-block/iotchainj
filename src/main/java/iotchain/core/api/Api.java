package iotchain.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import okhttp3.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Api {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .build();

    private String url;

    public Api(String url){
        this.url = url.endsWith("/")?url.substring(0,url.length()-1):url;
    }

    /**
     * request list data
     */
    protected <T> List<T> call2(String path, Map data, Class<T> clazz) throws IOException {
        TypeReference<ApiResponse<List<T>>> type = new TypeReference<ApiResponse<List<T>>>(clazz) {};
        ApiResponse<List<T>> apiResponse = callInner(path, data, type);
        return apiResponse.getResult();
    }

    /**
     * request single object
     */
    protected <T> T call(String path, Map data, Class<T> clazz) throws IOException {
        TypeReference<ApiResponse<T>> type = new TypeReference<ApiResponse<T>>(clazz) {};
        ApiResponse<T> apiResponse = callInner(path, data, type);
        return apiResponse.getResult();
    }

    private <T> T callInner(String path, Map data, TypeReference<T> type) throws IOException {
        String json = JSON.toJSONString(data, SerializerFeature.WriteNonStringValueAsString);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Request request = new Request.Builder()
                .url(this.url+"/"+path)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String resp = response.body().string();
            T apiResponse = JSON.parseObject(resp, type);
            return apiResponse;
        }
    }
}
