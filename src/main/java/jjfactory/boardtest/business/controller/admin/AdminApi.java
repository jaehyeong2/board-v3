package jjfactory.boardtest.business.controller.admin;


import jjfactory.boardtest.business.dto.admin.AdminCreateDto;
import jjfactory.boardtest.business.service.admin.AdminService;
import jjfactory.boardtest.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminApi {
    private final AdminService adminService;

    @PostMapping("")
    public ApiResponse<String> createAdmin(@RequestBody AdminCreateDto adminCreateDto){
        return new ApiResponse<>(adminService.registerAdmin(adminCreateDto));
    }
}
