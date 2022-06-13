package jjfactory.boardtest.business.service.report;

import jjfactory.boardtest.business.domain.admin.Admin;
import jjfactory.boardtest.business.domain.report.Warning;
import jjfactory.boardtest.business.domain.user.User;
import jjfactory.boardtest.business.dto.report.WarningChangeReq;
import jjfactory.boardtest.business.dto.report.WarningDto;
import jjfactory.boardtest.business.dto.report.WarningResponse;
import jjfactory.boardtest.business.repository.admin.AdminRepository;
import jjfactory.boardtest.business.repository.report.WarningRepository;
import jjfactory.boardtest.business.repository.user.UserRepository;
import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class WarningService {
    private final WarningRepository warningRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public WarningResponse findWarning(Long id){
        Warning warning = getWarning(id);
        return new WarningResponse(warning);
    }

    public String create(WarningDto dto){
        User user = getUser(dto);
        Admin admin = getAdmin(dto);

        Warning warning = Warning.create(dto, admin, user);
        warningRepository.save(warning);
        return "Y";
    }

    public String delete(Long id){
        warningRepository.deleteById(id);
        return "Y";
    }

    public String update(Long id, WarningChangeReq dto){
        Warning warning = getWarning(id);
        warning.changeCode(dto.getCode());
        return "Y";
    }

    private Warning getWarning(Long id) {
        Warning warning = warningRepository.findById(id).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        });
        return warning;
    }

    private User getUser(WarningDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        });
        return user;
    }

    private Admin getAdmin(WarningDto dto) {
        Admin admin = adminRepository.findById(dto.getAdminId()).orElseThrow(() -> {
            throw new BusinessException(ErrorCode.NOT_FOUND);
        });
        return admin;
    }
}
