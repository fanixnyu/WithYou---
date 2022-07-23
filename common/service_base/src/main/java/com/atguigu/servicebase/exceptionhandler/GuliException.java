package com.atguigu.servicebase.exceptionhandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    private Integer Code;//状态码

    private String message;//异常信息

}
