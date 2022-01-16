package com.nisum.demo.service;

import com.nisum.demo.dto.UserInfoDTO;
import com.nisum.demo.exception.GeneralNisumException;
import com.nisum.demo.model.Users;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ApprovalService {

    private final UserInfoService userInfoService;
    private final PhoneService phoneService;

    @Transactional
    public Users user(UserInfoDTO userInfoDTO) throws GeneralNisumException {
        log.info("user | userInfoDTO={}", userInfoDTO);
        userInfoService.validNotExistByEmail(userInfoDTO.getEmail());
        Users users = userInfoService.save(userInfoDTO);
        phoneService.saveAll(users, userInfoDTO);
        return users;
    }
}
