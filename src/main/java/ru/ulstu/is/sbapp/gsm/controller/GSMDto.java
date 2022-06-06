package ru.ulstu.is.sbapp.gsm.controller;

import ru.ulstu.is.sbapp.gsm.model.GSM;

public class GSMDto {
    private Long id;
    private String name;

    public GSMDto(GSM gsm) {
        this.id = gsm.getId();
        this.name = String.format("%s", gsm.getName());
    }

    public  GSMDto(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }
}