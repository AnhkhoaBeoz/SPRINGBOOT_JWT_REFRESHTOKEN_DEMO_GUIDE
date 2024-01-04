package com.khoabeo.demojwt.service;

import com.khoabeo.demojwt.modal.UserEntity;
import com.khoabeo.demojwt.payload.UserEntityResponse;
import com.khoabeo.demojwt.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements  UserEntityService{
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Override
    public UserEntityResponse getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<UserEntity> page = this.userEntityRepository.findAll(pageable);

        List<UserEntity> content = page.getContent();

        UserEntityResponse userEntityResponse = new UserEntityResponse();
        userEntityResponse.setContent(content);
        userEntityResponse.setPageNo(page.getNumber());
        userEntityResponse.setPageSize(page.getSize());
        userEntityResponse.setTotalElements(page.getTotalElements());
        userEntityResponse.setTotalPages(page.getTotalPages());
        userEntityResponse.setLast(page.isLast());

        return userEntityResponse;
    }
}
