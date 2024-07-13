package com.kirana.store.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorResponse {
    private int status;
    private int errorCode;
    private String message;
}
