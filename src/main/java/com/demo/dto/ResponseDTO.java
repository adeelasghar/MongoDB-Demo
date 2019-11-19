package com.demo.dto;

import lombok.*;
import java.io.Serializable;

/**
 * @author Adeel.Asghar
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    Object content;
    boolean isSuccessful = true;
    String message = "Request processed successfully!";

}
