package org.chilin.service;

import org.chilin.model.MyTischTennisSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TTRService {

    public static final String MYTISCHTENNIS_LOGIN = "/community/login/";
    public static final String GIVEME_TTR_POINTS = "/community/index/";
    public static final String MYTISCHTENNIS_LOGOUT = "/community/logout/";

    @Autowired
    private MyTischTennisSession myTischTennisSession;

    @Autowired
    private TTRExtractor ttrExtractor;

    private WebClient myTischtennisClient;
    private String baseUrl = "https://www.mytischtennis.de";

    public String getTtrPoints(){
        myTischtennisClient = createMyTischtennisClient();

        logInAndGetCookies();

        if (isLogInSuccessfull()){
            String allMyTischtennisValuesTogether = this.myTischTennisSession.getMyTischTennisCookieReader()
                    .getAllMyTischtennisValuesTogether();
            String pageWithTTRPoints = getPageWithTTRPoints(myTischtennisClient,allMyTischtennisValuesTogether);
            String ttrPoints = ttrExtractor.extractTTRPoints(pageWithTTRPoints);
            this.myTischTennisSession.setMyTtrPoints(ttrPoints);
            logOutFromMyTischtennis();
        }

        return this.myTischTennisSession.getMyTtrPoints();
    }

    private void logOutFromMyTischtennis(){
        WebClient.RequestBodySpec logoutRequest = myTischtennisClient.method(HttpMethod.GET).uri(MYTISCHTENNIS_LOGOUT);
        logoutRequest.exchange().block().bodyToMono(String.class).block();
    }

    private String getPageWithTTRPoints(WebClient myTischtennisClient,String cookieTogether){
        WebClient.RequestBodySpec ttrRequest = myTischtennisClient
                .method(HttpMethod.GET)
                .uri(GIVEME_TTR_POINTS)
                .header(HttpHeaders.COOKIE,cookieTogether)
                .accept(MediaType.APPLICATION_JSON);
        return ttrRequest.exchange().block().bodyToMono(String.class).block();
    }

    private boolean isLogInSuccessfull(){
        return this.myTischTennisSession.getMyTischTennisCookieReader().isLoginSuccessful();
    }

    private void logInAndGetCookies() {
        myTischtennisClient.post()
                .uri(MYTISCHTENNIS_LOGIN)
                .body(BodyInserters.fromFormData("userNameB", "anguerrero@gmail.com")
                        .with("userPassWordB", "Tenis21De1Mesa83")
                        .with("targetPage", "https://www.mytischtennis.de/public/home?fromlogin=1&logout=true"))
                .exchange()
                .doOnSuccess(clientResponse -> this.myTischTennisSession.getMyTischTennisCookieReader()
                        .extractCookiesFromLoginResponse(clientResponse.cookies()))
                .flatMap(clientResponse -> clientResponse.bodyToMono(String.class)).block();
    }

    private WebClient createMyTischtennisClient(){
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE)
                .build();
    }

}
