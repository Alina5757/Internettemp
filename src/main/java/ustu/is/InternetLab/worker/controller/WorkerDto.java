package ustu.is.InternetLab.worker.controller;

import ustu.is.InternetLab.worker.model.Worker;

public class WorkerDto {
    private final Long id;
    private final String name;

    public WorkerDto(Worker worker) {
        this.id = worker.getId();
        this.name = String.format("%s %s", worker.getFirstName(), worker.getLastName());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}