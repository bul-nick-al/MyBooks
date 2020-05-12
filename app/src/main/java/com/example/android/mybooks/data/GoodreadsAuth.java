package com.example.android.mybooks.data;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

public class GoodreadsAuth extends DefaultApi10a {
    @Override
    public String getRequestTokenEndpoint() {
        return "https://www.goodreads.com/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://www.goodreads.com/oauth/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://www.goodreads.com/oauth/authorize";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return String.format("https://goodreads.com/oauth/authorize?mobile=1&oauth_token=%s", requestToken.getToken());
    }
}
