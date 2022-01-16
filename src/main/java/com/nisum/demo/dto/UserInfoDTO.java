package com.nisum.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.demo.helper.MessageHelper;
import com.nisum.demo.helper.RegexHelper;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDTO {

    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String name;

    @Email(regexp = RegexHelper.EMAIL,message = MessageHelper.MESSAGE_ERROR_REGEXP)
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String email;

    @Pattern(regexp = RegexHelper.PASS,message = MessageHelper.MESSAGE_ERROR_REGEXP)
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    private String password;

    @Valid
    @JsonProperty("phones")
    @NotNull(message = MessageHelper.MESSAGE_NOT_NULL)
    @Size(min = 1, message = MessageHelper.MESSAGE_EMPTY_COLLECTION)
    private Collection<PhoneDTO> phoneDTOS;
}
