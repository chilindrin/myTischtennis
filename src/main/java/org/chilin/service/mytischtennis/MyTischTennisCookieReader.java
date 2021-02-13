package org.chilin.service.mytischtennis;

import lombok.Data;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

@Data
@Service
public class MyTischTennisCookieReader {

    private String cfid = "";
    private String cftoken = "0";
    private String SRV = "82";
    private String JSESSIONID = "";

    public boolean isLoginSuccessful(){
        return !StringUtils.isEmpty(cfid);
    }

    public String getAllMyTischtennisValuesTogether(){
        if (StringUtils.isEmpty(this.JSESSIONID)){
            return "cftoken="+cftoken+"; SRV="+SRV+"; cfid="+cfid;
        } else {
            return "cftoken="+cftoken+"; SRV="+SRV+"; JSESSIONID="+JSESSIONID + "; cfid="+cfid;
        }
    }

    public void extractCookiesFromLoginResponse(MultiValueMap<String, ResponseCookie> cookiesFromLoginResponse){
        ResponseCookie cftokenCookie = cookiesFromLoginResponse.getFirst("cftoken");
        String cftokenFromLogin = cftokenCookie.getValue();
        if (!StringUtils.isEmpty(cftokenFromLogin)){
            cftoken = cftokenFromLogin;
        }
        ResponseCookie srvCookie = cookiesFromLoginResponse.getFirst("SRV");
        String srvFromLogin = srvCookie.getValue();
        if (!StringUtils.isEmpty(srvFromLogin)){
            SRV = srvFromLogin;
        }
        ResponseCookie jsessionIdCookie = cookiesFromLoginResponse.getFirst("JSESSIONID");
        if (jsessionIdCookie != null && !StringUtils.isEmpty(jsessionIdCookie.getValue())){
            JSESSIONID = jsessionIdCookie.getValue();
        }
        ResponseCookie cfidCookie = cookiesFromLoginResponse.getFirst("cfid");
        if (cfidCookie != null && !StringUtils.isEmpty(cfidCookie.getValue())){
            cfid = cfidCookie.getValue();
        }
    }

}
