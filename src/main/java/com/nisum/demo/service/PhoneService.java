package com.nisum.demo.service;

import com.nisum.demo.dto.UserInfoDTO;
import com.nisum.demo.model.Phone;
import com.nisum.demo.model.Users;
import com.nisum.demo.repository.PhoneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PhoneService {

    private final PhoneRepository phoneRepository;

    @Transactional
    public Collection<Phone> saveAll(Users users, UserInfoDTO userInfoDTO) {
        log.info("saveAll | users={}, userInfoDTO={}", users, userInfoDTO);
        return saveAll(Phone.from(users, userInfoDTO));
    }

    @Transactional
    public Collection<Phone> saveAll(Collection<Phone> phones) {
        log.info("saveAll | phones={}",phones);
        return phoneRepository.saveAll(phones);
    }
}
