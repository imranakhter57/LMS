package com.hexad.lmsproj.services;

import com.hexad.lmsproj.datamodels.vo.UserLoginVO;
import org.json.JSONException;

public interface AuthenticationServices {

    public UserLoginVO userLogin(String username, String password) throws JSONException;

    public String userLogout(Long userId) throws JSONException;
}
