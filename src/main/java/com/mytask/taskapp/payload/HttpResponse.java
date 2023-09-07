package com.mytask.taskapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponse {

    private int  StatusCode;
    private String Status;
    private String Message;
    private Object Data;
}
