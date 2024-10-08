package com.data.tallermodelodatos.exception;

import lombok.Data;
import java.time.LocalDateTime;

@Data

public class ErrorMessage {
    private String status;
    private String message;
    private LocalDateTime timestamp;


}
