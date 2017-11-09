import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

/**
 * create by 陶江航 at 2017/11/9 10:41
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 通过Retrofit请求API
 */
public class RequestByRetrofit {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build();

    public static void main(String[] args) {

        new RequestByRetrofit().testObjPost();
    }

    /**
     * 测试返回对象的使用场景
     */
    public void testObjPost() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://localhost:8080/")
                .build();
        Api apiRetrofit = retrofit.create(Api.class);
        BindCardParamDto bindCardParamDto = new BindCardParamDto();
        bindCardParamDto.setBankCard("412341234");
        bindCardParamDto.setPhone("41234123");
        bindCardParamDto.setVerifyCode("341234");
        Call<List<User>> bodyCall = apiRetrofit.simplePostCall2(bindCardParamDto);
        bodyCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (User user : response.body()) {
                    System.out.println(user);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * 测试普通的post请求
     */
    public void testPost() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://localhost:8080/")
                .build();
        Api apiRetrofit = retrofit.create(Api.class);
        BindCardParamDto bindCardParamDto = new BindCardParamDto();
        bindCardParamDto.setBankCard("412341234");
        bindCardParamDto.setPhone("41234123");
        bindCardParamDto.setVerifyCode("341234");
        Call<ResponseBody> bodyCall = apiRetrofit.simplePostCall(bindCardParamDto);
        //异步调用
        bodyCall.enqueue(new ApiCallback());
    }

    /**
     * 测试普通的get请求
     */
    public void testGet() {
        Api apiRetrofit = retrofit.create(Api.class);
        Call<ResponseBody> bodyCall = apiRetrofit.contributorsBySimpleGetCall(
                "TernenceTao",
                "design-pattern");
        //同步调用
        //Callback<ResponseBody> res= bodyCall.execute();
        //异步调用
        bodyCall.enqueue(new ApiCallback());
    }

    class ApiCallback implements Callback<ResponseBody> {

        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                System.out.println(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void onFailure(Call<ResponseBody> call, Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
