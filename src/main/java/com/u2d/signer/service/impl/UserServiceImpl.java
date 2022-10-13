package com.u2d.signer.service.impl;

import com.u2d.signer.dto.request.UserRequest;
import com.u2d.signer.dto.response.UserResponse;
import com.u2d.signer.entity.Permission;
import com.u2d.signer.entity.User;
import com.u2d.signer.exception.ResourceExistsException;
import com.u2d.signer.repository.UserRepository;
import com.u2d.signer.service.PermissionService;
import com.u2d.signer.service.UserService;
import com.u2d.signer.utils.PasswordUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private ModelMapper mapper;

    private PermissionService permissionService;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper mapper, PermissionService permissionService) {
        this.repository = repository;
        this.mapper = mapper;
        this.permissionService = permissionService;
    }

    public UserResponse create(UserRequest userRequest) {
        Optional<User> user = this.repository.findByCpfCnpj(userRequest.getCpfCnpj());
        if(user.isPresent())
            throw new ResourceExistsException(User.class);
        List<Permission> permissions = userRequest.getPermissions().stream()
                .map(perm -> this.permissionService.findByDescription(perm.getDescription()).get())
                .collect(Collectors.toList());
        User u = this.mapper.map(userRequest, User.class);
        u.setPassword(PasswordUtil.encoder(u.getPassword()));
        u.setPermissions(permissions);
        User userSaved = this.repository.save(u);
        return this.mapper.map(userSaved, UserResponse.class);
    }
}
