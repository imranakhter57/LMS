package com.hexad.lmsproj.services.impl;

import com.hexad.lmsproj.DAO.UserCredentialsDAO;
import com.hexad.lmsproj.DAO.UserProfileDAO;
import com.hexad.lmsproj.datamodels.entity.UserCredentials;
import com.hexad.lmsproj.datamodels.entity.UserProfile;
import com.hexad.lmsproj.datamodels.vo.UserLoginVO;
import com.hexad.lmsproj.services.AuthenticationServices;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuthenticationServicesImpl implements AuthenticationServices {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(AuthenticationServicesImpl.class);

    @Autowired
    UserCredentialsDAO userCredentialsDAO;

    @Autowired
    UserProfileDAO userProfileDAO;

    @Override
    public UserLoginVO userLogin(String username, String password) throws JSONException {
        UserLoginVO userLoginVO = new UserLoginVO();
        try {
            UserCredentials userCredentials = userCredentialsDAO.getUserCredsByUsername(username);

            if (userCredentials != null && userCredentials.getPASSWORD() != null && userCredentials.getPASSWORD().equalsIgnoreCase(password)) {
                UserProfile userProfile = userProfileDAO.getUserProfileByUserId(userCredentials.getUserId());
                userCredentials.setLastLogin(new Timestamp(System.currentTimeMillis()));
                userCredentialsDAO.saveUserCred(userCredentials);
                userLoginVO.setStatus("Success");
                userLoginVO.setUserId(userCredentials.getUserId());
                userLoginVO.setRoleId(userCredentials.getRoleId());
                userLoginVO.setUserFullName(userProfile.getFirstName() + " " + userProfile.getLastName());
            } else {
                userLoginVO.setStatus("Failed");
                userLoginVO.setErrorMessage("Invalid username or password. Please check your credentials");
            }
        } catch (Exception e){
            userLoginVO.setStatus("Failed");
            userLoginVO.setErrorMessage("Something went wrong");
        }
        return userLoginVO;
    }

    @Override
    public String userLogout(Long userId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        try {
            UserCredentials userCredentials = userCredentialsDAO.getUserCredsByUserId(userId);
            userCredentials.setLastLogout(new Timestamp(System.currentTimeMillis()));
            userCredentialsDAO.saveUserCred(userCredentials);
            jsonObject.put("Status","Success");
            logger.info("Logout success");
        } catch (Exception e){
            jsonObject.put("Status","Success");
            jsonObject.put("errorMessage","Something went wrong while logout");
        }
        return jsonObject.toString();
    }
}
