package com.hexad.lmsproj.authentication;

import com.hexad.lmsproj.datamodels.dto.UserDetailsDto;
import com.hexad.lmsproj.datamodels.vo.UserLoginVO;
import com.hexad.lmsproj.services.AuthenticationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/request")
public class AuthenticationController {
    private static final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationServices authenticationServices;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserLoginVO> userLogin(@RequestBody UserDetailsDto loginParam, @RequestHeader HttpHeaders rawHeaders) {
        try{
            UserLoginVO userLogin = authenticationServices.userLogin(loginParam.getUserName(),loginParam.getPassword());
            if(userLogin.getStatus().equalsIgnoreCase("Success")){
                logger.info("Login success");
                return new ResponseEntity<>(userLogin, HttpStatus.OK);
            }
            logger.info("Login failed");
            return new ResponseEntity<>(userLogin, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Something went wrong while login", e);
        }
		return null;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> userLogout(@RequestBody String logoutParam, @RequestHeader HttpHeaders rawHeaders) {

        try {
            JSONObject jsonObj = new JSONObject(logoutParam);
           String response = authenticationServices.userLogout(Long.valueOf(jsonObj.get("userId").toString()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while calling LDAP Authentication ", e);
        }
        return null;
    }
}
