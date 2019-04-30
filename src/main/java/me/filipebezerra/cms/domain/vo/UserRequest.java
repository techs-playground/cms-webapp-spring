package me.filipebezerra.cms.domain.vo;

import lombok.Data;
import me.filipebezerra.cms.domain.models.Role;

@Data
public class UserRequest {

    private String identity;

    private String name;

    private Role role;

}
