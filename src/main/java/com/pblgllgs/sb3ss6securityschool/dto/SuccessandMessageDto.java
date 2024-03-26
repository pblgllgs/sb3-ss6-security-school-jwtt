package com.pblgllgs.sb3ss6securityschool.dto;
/*
 *
 * @author pblgl
 * Created on 25-03-2024
 *
 */

public class SuccessandMessageDto {

    private boolean success;
    private String message;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }


}
