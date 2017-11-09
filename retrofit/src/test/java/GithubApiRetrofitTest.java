import okhttp3.ResponseBody;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Arrays;

/**
 * create by 陶江航 at 2017/11/9 10:36
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description TODO Writing description for function of the class
 */
public class GithubApiRetrofitTest {

    @Test
    public void contributorsBySimpleGetCall() throws Exception {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
        Api apiRetrofit = retrofit.create(Api.class);
        Call<ResponseBody> bodyCall = apiRetrofit.contributorsBySimpleGetCall("TernenceTao", "design-pattern");

        bodyCall.enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                System.out.println(Arrays.toString(throwable.getStackTrace()));
            }
        });
    }

}