package com.u2d.signer.service;

import com.u2d.signer.dto.request.UserRequest;
import com.u2d.signer.dto.response.UserResponse;

public interface UserService {

    UserResponse create(UserRequest userRequest);
}
