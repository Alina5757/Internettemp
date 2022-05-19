package ustu.is.InternetLab.gsm.controller;

import ustu.is.InternetLab.gsm.model.GSM;

public class GSMDto {
    private final Long id;
    private final String name;

    public GSMDto(GSM gsm) {
        this.id = gsm.getId();
        this.name = String.format("%s", gsm.getName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}