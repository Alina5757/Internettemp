package ru.ulstu.is.sbapp.GSMWorker.service;

public class GSMWorkerNotFoundException extends RuntimeException{
    public GSMWorkerNotFoundException(Long id) {
        super(String.format("GSMWorker with id [%s] is not found", id));
    }

}