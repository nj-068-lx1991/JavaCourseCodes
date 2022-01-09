package com.rpc.core.demo.exception;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义模拟异常
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

    private String message;
}
