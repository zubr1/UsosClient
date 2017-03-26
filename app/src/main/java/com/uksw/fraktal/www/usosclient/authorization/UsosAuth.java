package com.uksw.fraktal.www.usosclient.authorization;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by Grzesiek on 22.03.2017.
 */

public class UsosAuth extends DefaultApi10a {

    private static final String AUTHORIZE_URL = "https://apps.usos.uksw.edu.pl/services/oauth/authorize";
    private static final String REQUEST_TOKEN_URL = "apps.usos.uksw.edu.pl/services/oauth/request_token?scopes=studies";
    private static final String ACCESS_TOKEN_URL = "apps.usos.uksw.edu.pl/services/oauth/access_token";

    private static class InstanceHolder {
        private static final UsosAuth INSTANCE = new UsosAuth();
    }

    public static UsosAuth instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://" + ACCESS_TOKEN_URL;
    }

    @Override
    public String getRequestTokenEndpoint() {
        return "https://" + REQUEST_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return String.format(AUTHORIZE_URL, requestToken.getToken());
    }


}
