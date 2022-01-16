package com.nisum.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.demo.dto.PhoneDTO;
import com.nisum.demo.dto.UserInfoDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@Entity
@Table(name = "phone")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Phone {

    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String usersId;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String citycode;

    @Column(nullable = false)
    private String contrycode;

    public static Phone from(Users users, PhoneDTO phoneDto) {
        Phone phone = new Phone();
        phone.setUsersId(users.getId());
        phone.setNumber(phoneDto.getNumber());
        phone.setCitycode(phoneDto.getCitycode());
        phone.setContrycode(phoneDto.getContrycode());
        return phone;
    }

    public static Collection<Phone> from(Users users, UserInfoDTO userInfoDTO) {
        Collection<Phone> phones = new ArrayList<>();
        userInfoDTO.getPhoneDTOS().forEach(phoneDTO -> phones.add(from(users, phoneDTO)));
        return phones;
    }

}