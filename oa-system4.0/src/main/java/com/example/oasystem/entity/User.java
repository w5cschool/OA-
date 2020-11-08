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

    private String loginName;

    private String password;

    private String nickName;

    private String age;

    private String location;

    private String role;

    private String images;

    private static final long serialVersionUID = 1L;
}