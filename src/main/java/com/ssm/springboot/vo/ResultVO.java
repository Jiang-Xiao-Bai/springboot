package com.ssm.springboot.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ziyi on 2018/7/5.
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -8630858531126402157L;

    /* 错误码 */
    private Integer code;

    /* 错误信息 */
    private String msg;

    /* 返回具体的内容 */
    private T data;
}
