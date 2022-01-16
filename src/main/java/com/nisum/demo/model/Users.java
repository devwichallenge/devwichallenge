package com.nisum.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.demo.dto.UserInfoDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users {

    @Id
    private String id = UUID.randomUUID().toString();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modified = LocalDateTime.now();


    @JsonProperty(value = "last_login")
    @Column(nullable = false)
    private LocalDateTime lastLogin = LocalDateTime.now();

    @Column(nullable = false)
    private String token;

    @JsonProperty("isactive")
    @Column(nullable = false)
    private Boolean isActive = true;

    public static Users fromUserInfo(UserInfoDTO userInfoDTO, String password, String token) {
        Users users = new Users();
        users.setName(userInfoDTO.getName());
        users.setEmail(userInfoDTO.getEmail());
        users.setPassword(password);
        users.setToken(token);
        return users;
    }
}
