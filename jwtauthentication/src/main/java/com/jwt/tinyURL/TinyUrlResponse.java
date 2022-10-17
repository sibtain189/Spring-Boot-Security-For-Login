package com.jwt.tinyURL;

import java.util.List;

public class TinyUrlResponse {
    
    public DataTinyResponse data;
    public int code;
    public List<Errors> errors;
    
    public DataTinyResponse getData() {
        return data;
    }
    public void setData(DataTinyResponse data) {
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<Errors> getErrors() {
        return errors;
    }
    public void setErrors(List<Errors> errors) {
        this.errors = errors;
    }
    
    
    
}
