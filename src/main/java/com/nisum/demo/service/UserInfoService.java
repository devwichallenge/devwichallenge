package com.nisum.demo.service;

import com.nisum.demo.dto.UserInfoDTO;
import com.nisum.demo.exception.GeneralNisumException;
import com.nisum.demo.helper.CryptoHelper;
import com.nisum.demo.helper.JwtHelper;
import com.nisum.demo.helper.MessageHelper;
import com.nisum.demo.model.Users;
import com.nisum.demo.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {

    private final UsersRepository usersRepository;
    private final CryptoHelper cryptoHelper;
    private final JwtHelper jwtHelper;

    @Transactional
    public Users save(UserInfoDTO userInfoDTO) {
        log.info("save | userInfoDTO={}", userInfoDTO);
        String password = cryptoHelper.encode(userInfoDTO.getPassword());
        String token = jwtHelper.createToken(userInfoDTO.getEmail());
        return save(Users.fromUserInfo(userInfoDTO, password, token));
    }

    @Transactional
    public Users save(Users users) {
        log.info("save | users={}", users);
        return usersRepository.save(users);
    }

    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public void validNotExistByEmail(String email) throws GeneralNisumException {
        Optional<Users> users = findByEmail(email);
        if (users.isPresent()) {
            throw new GeneralNisumException(MessageHelper.MESSAGE_EMAIL_EXISTS);
        }
    }
}
