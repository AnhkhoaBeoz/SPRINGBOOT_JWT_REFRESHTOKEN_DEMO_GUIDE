package com.khoabeo.demojwt.payload;

import com.khoabeo.demojwt.modal.UserEntity;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityResponse {
    private List<UserEntity> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
