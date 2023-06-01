package com.home.work.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActiveUserDTO {

    private String username;

    private String sessionId;
}
