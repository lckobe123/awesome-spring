package com.ternence.skills.feign.api;

import com.ternence.skills.feign.bean.Contributor;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * github的API接口描述Java类
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/3 17:09
 */
public interface Github {

    @RequestLine("GET /repos/{owner}/{repo}/contributors")
    List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

}
