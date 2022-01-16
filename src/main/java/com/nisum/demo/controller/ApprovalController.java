package com.nisum.demo.controller;

import com.nisum.demo.dto.UserInfoDTO;
import com.nisum.demo.exception.GeneralNisumException;
import com.nisum.demo.helper.MessageHelper;
import com.nisum.demo.service.ApprovalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/approval", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApprovalController {

    private final ApprovalService approvalService;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity user(
            @Valid @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
            @RequestBody UserInfoDTO userInfoDTO) throws GeneralNisumException {
        log.info("user | userInfoDTO={}", userInfoDTO.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(approvalService.user(userInfoDTO));
    }
}
