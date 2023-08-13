package com.github.wxiaoqi.security.modules.auth.util.user;

import com.github.wxiaoqi.security.common.util.jwt.IJWTInfo;
import com.github.wxiaoqi.security.common.util.jwt.JWTHelper;
import com.github.wxiaoqi.security.configuration.KeyConfiguration;
import com.github.wxiaoqi.security.modules.inspur.util.JWTwechat;
import com.github.wxiaoqi.security.modules.inspur.util.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ace on 2017/9/10.
 */
@Component
public class JwtTokenUtil {

    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfiguration keyConfiguration;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String generateToken(IJWTInfo jwtInfo) throws Exception {
        return JWTHelper.generateToken(jwtInfo, keyConfiguration.getUserPriKey(), expire);
    }

    public IJWTInfo getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token, keyConfiguration.getUserPubKey());
    }

    public String genToken(TokenModel tokenModel) throws Exception {
        return JWTwechat.getToken(tokenModel, keyConfiguration.getUserPriKey(), expire);
    }

    public TokenModel getfromToken(String token) throws Exception {
        return JWTwechat.getparserToken(token, keyConfiguration.getUserPubKey());
    }

}
