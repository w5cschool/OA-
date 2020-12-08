package com.example.oasystem.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Role implements Serializable {
    private Integer id;

    private String role;

    private String name;

    private static final long serialVersionUID = 1L;
}