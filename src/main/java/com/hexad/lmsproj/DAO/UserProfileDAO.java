package com.hexad.lmsproj.DAO;

import com.hexad.lmsproj.datamodels.entity.UserCredentials;
import com.hexad.lmsproj.datamodels.entity.UserProfile;

public interface UserProfileDAO {
    public UserProfile getUserProfileByUserId(Long userId);
}
