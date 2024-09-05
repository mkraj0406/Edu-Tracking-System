package com.jsp.ets.utility;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class MessageModel {
    private String to;
    private Date sendDate;
    private  String subject;
    private  String text;
}
