package com.hexad.lmsproj.DAO;

import com.hexad.lmsproj.datamodels.entity.UserCredentials;

public interface UserCredentialsDAO {

    public UserCredentials getUserCredsByUsername(String username);

    public UserCredentials saveUserCred(UserCredentials userCredentials);

    public UserCredentials getUserCredsByUserId(Long userId);
}
