package jjfactory.boardtest.business.service.admin;

import jjfactory.boardtest.business.domain.admin.Admin;
import jjfactory.boardtest.business.dto.admin.AdminCreateDto;
import jjfactory.boardtest.business.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public String create(AdminCreateDto dto){
        Admin admin = Admin.enrollAdmin(dto);
        adminRepository.save(admin);
        return "y";
    }

}
