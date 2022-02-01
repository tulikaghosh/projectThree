package com.project3.revtech.service;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.ProductPojo;
import com.project3.revtech.pojo.UserPojo;


public interface UserService {
	UserPojo updateUserService(UserPojo userInfo) throws ApplicationException;
    UserPojo getAUserService(int user_id) throws ApplicationException;
}