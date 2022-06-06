package ru.ulstu.is.sbapp.worker.service;

public class WorkerNotFoundException extends RuntimeException {
    public WorkerNotFoundException(Long id) {
        super(String.format("Worker with id [%s] is not found", id));
    }
}
