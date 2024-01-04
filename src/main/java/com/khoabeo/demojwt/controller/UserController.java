    package com.khoabeo.demojwt.controller;

    import com.khoabeo.demojwt.Constants.AppConstants;
    import com.khoabeo.demojwt.payload.UserEntityResponse;
    import com.khoabeo.demojwt.repository.UserEntityRepository;
    import com.khoabeo.demojwt.service.UserEntityService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestParam;
    import org.springframework.web.bind.annotation.RestController;

    @RestController

    public class UserController {
        @Autowired
        private UserEntityService userEntityService;
        @GetMapping("/listuser")
        public UserEntityResponse getAllUsers(
                @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
                @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
        ) {

            return this.userEntityService.getAllUser(pageNo, pageSize, sortBy, sortDir);
        }
    }
