package com.geekuniversity.framework.code10.hikari;

import lombok.Data;

/**
 * @author Created by lx_068
 */

@Data
public class User {
    private Long id;
    private String name;
    private String password;
    private String phoneNumber;
    private Long money;
}
