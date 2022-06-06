package ustu.is.InternetLab.worker.controller;

import ustu.is.InternetLab.worker.model.Worker;

public class WorkerDto {
    private Long id;
    private String firstName;
    private String lastName;

    public WorkerDto(Worker worker) {
        this.id = worker.getId();
        this.firstName = String.format("%s", worker.getFirstName());
        this.lastName = String.format("%s", worker.getLastName());
    }

    public  WorkerDto(){}

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}