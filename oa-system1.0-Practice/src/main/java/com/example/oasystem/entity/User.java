package com.example.oasystem.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class User extends UserKey implements Serializable {
    private String nickName;

    private Integer age;

    private String location;

    private String password;

    private String role;

    private static final long serialVersionUID = 1L;
}