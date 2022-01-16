package com.nisum.demo.helper;

import com.nisum.demo.dto.PhoneDTO;
import com.nisum.demo.dto.UserInfoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class DataExtensions {

    public UserInfoDTO getUserDefaultEmpty() {
        return  new UserInfoDTO();
    }

    public UserInfoDTO getUserDefault() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName("Juanito Jones");
        userInfoDTO.setEmail("juanito.jones@example.cl");
        userInfoDTO.setPassword("userR13");
        userInfoDTO.setPhoneDTOS(getPhonesDefault());
        return userInfoDTO;
    }

    public Collection<PhoneDTO> getPhonesDefault() {
        Collection<PhoneDTO> phoneDTOS = new ArrayList<>();
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber("1234567890");
        phoneDTO.setCitycode("1");
        phoneDTO.setContrycode("56");
        phoneDTOS.add(phoneDTO);
        return phoneDTOS;
    }

}
