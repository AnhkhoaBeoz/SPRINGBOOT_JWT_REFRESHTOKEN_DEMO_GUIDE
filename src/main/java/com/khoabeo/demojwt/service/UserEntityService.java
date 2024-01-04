package com.khoabeo.demojwt.service;

import com.khoabeo.demojwt.payload.UserEntityResponse;

public interface UserEntityService {
    public UserEntityResponse getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);
}
