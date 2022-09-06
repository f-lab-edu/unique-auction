package com.uniqueAuction.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultMsg {
    private String code;
    private String message;
}
