import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * create by 陶江航 at 2017/11/9 10:28
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 测试retrofit
 */
public interface Api {
    /**
     * retrofit使用场景1:传递path variable
     * post/get接口
     *
     * @param owner 参数
     * @param repo  参数
     * @return 接口的返回内容
     */
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(
            @Path("owner") String owner,
            @Path("repo") String repo);


    /**
     * retrofit使用场景2:使用post请求传递参数 variable
     * post接口
     * <p>
     * 模拟表单上传的方式
     *
     * @param bankCard 参数
     * @return 服务器响应
     */
    @FormUrlEncoded
    @POST("user/auth/card/bind.html")
    Call<ResponseBody> simplePostCall(@Field("bankCard") String bankCard);


    /**
     * retrofit 使用场景3:post请求接口，使用@Body传递参数
     *
     * @param dto 参数对象
     * @return 服务器返回内容
     */
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("user/auth/card/bind.html")
    Call<ResponseBody> simplePostCall(@Body BindCardParamDto dto);

    /**
     * retrofit 使用场景3:post请求接口，使用@Body传递参数
     *
     * @param dto 参数对象
     * @return 服务器返回内容
     */
    @Headers({"Content-Type: application/json","Accept: application/json","username:taojianghang"})
    @POST("user/auth/card/bind.html")
    Call<List<User>> simplePostCall2(@Body BindCardParamDto dto);
}
