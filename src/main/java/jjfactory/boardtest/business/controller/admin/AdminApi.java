package jjfactory.boardtest.business.controller.admin;


import jjfactory.boardtest.business.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RequiredArgsConstructor
@RestController
public class AdminApi {
    private final AdminService adminService;

//    @PostMapping("")
//    public ApiResponse<String> createAdmin(@RequestBody AdminCreateDto adminCreateDto){
//
//    }
}
