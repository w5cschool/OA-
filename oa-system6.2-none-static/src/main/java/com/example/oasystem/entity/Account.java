package com.example.oasystem.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Account implements Serializable {
    private Integer id;

    private String loginName;

    private String image;

    private String nickName;

    private Integer age;

    private String role;

    private String password;

    private static final long serialVersionUID = 1L;

}