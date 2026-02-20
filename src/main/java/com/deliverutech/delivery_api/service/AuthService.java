package com.deliverutech.delivery_api.service;

import com.deliverutech.delivery_api.dto.request.LoginRequest;
import com.deliverutech.delivery_api.dto.request.RegisterRequest;
import com.deliverutech.delivery_api.dto.response.LoginResponse;
import com.deliverutech.delivery_api.dto.response.UserResponse;
import com.deliverutech.delivery_api.model.Usuario;

public interface AuthService {
    
    LoginResponse login(LoginRequest request);

    Usuario register(RegisterRequest request);

    UserResponse getCurrentUser();
}
