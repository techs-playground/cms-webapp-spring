package me.filipebezerra.cms.domain.vo;

import lombok.Data;
import me.filipebezerra.cms.domain.models.Role;

@Data
public class UserRequest {

    String identity;

    String name;

    Role role;

}
