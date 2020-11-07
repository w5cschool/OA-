package com.example.oasystem.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String nickName;

    private String loginName;

    private Integer age;

    private String location;

    private String password;

    private String role;

    private static final long serialVersionUID = 1L;
}