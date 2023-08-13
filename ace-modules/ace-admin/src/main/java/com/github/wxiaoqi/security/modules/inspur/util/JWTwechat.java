package com.github.wxiaoqi.security.modules.inspur.util;


import com.github.wxiaoqi.security.common.constant.CommonConstants;
import com.github.wxiaoqi.security.common.util.StringHelper;
import com.github.wxiaoqi.security.common.util.jwt.IJWTInfo;
import com.github.wxiaoqi.security.common.util.jwt.JWTInfo;
import com.github.wxiaoqi.security.common.util.jwt.RsaKeyHelper;
import com.github.wxiaoqi.security.configuration.KeyConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;


public class JWTwechat {
    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     * @param tokenModel
     * @param priKey
     * @param expire
     * @return
     * @throws Exception
     */
    public static String getToken(TokenModel tokenModel, byte priKey[], int expire) throws Exception {
        String compact = Jwts.builder()
                .setSubject(tokenModel.getUsername())
                .claim(CommonConstants.JWT_KEY_USER_ID, tokenModel.getUserId())
                .claim(CommonConstants.JWT_KEY_NAME, tokenModel.getName())
                .claim(CommonConstants.PHONE, tokenModel.getPhone())
                .claim(CommonConstants.OPEN_ID, tokenModel.getOpenId())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKey))
                .compact();
        return compact;
    }


    /**
     * 获取token中的信息
     *
     * @param token
     * @param pubKeyPath
     * @return
     * @throws Exception
     */
    public static TokenModel getparserToken(String token, byte[] pubKeyPath) throws Exception {
        if (token.startsWith("Bearer")) {
            token = token.replace("Bearer ", "");
        }
        Jws<Claims> claimsJws = parserToken(token, pubKeyPath);
        Claims body = claimsJws.getBody();
        return new TokenModel(body.getSubject(),StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_USER_ID)),StringHelper.getObjectValue(body.get(CommonConstants.JWT_KEY_NAME)),StringHelper.getObjectValue(body.get(CommonConstants.PHONE)),StringHelper.getObjectValue(body.get(CommonConstants.OPEN_ID)));
    }


    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Jws<Claims> parserToken(String token, byte[] pubKeyPath) throws Exception {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(rsaKeyHelper.getPublicKey(pubKeyPath)).parseClaimsJws(token);
        return claimsJws;
    }



   /*
    public static void main(String[] args) {
        RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();
        String oNVoS5RAS0Uj2ITVrkbj7JYT_3As = Jwts.builder().claim(CommonConstants.OPEN_ID, "oNVoS5RAS0Uj2ITVrkbj7JYT_3As")
                .claim(CommonConstants.SESSION_KE, "VQdeFWK3ecAu5ZdGwstrGw==")
                .setExpiration(DateTime.now().plusSeconds(14400).toDate())
               // .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey())
                .compact();
        System.out.println(oNVoS5RAS0Uj2ITVrkbj7JYT_3As);
    }
    */

}
