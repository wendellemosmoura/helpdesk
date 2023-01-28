package com.wendel.helpdesk.resources.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long timestamp;
    private Integer status;
    private String error;
    private String messager;
    private String path;

    public StandardError() {
        super();
    }

    public StandardError(Long timestamp, Integer status, String error, String messager, String path) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.messager = messager;
        this.path = path;
    }
}
