package com.aryan.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ApiException {

    private final String message;

    private final ZonedDateTime timeStamp;

}
