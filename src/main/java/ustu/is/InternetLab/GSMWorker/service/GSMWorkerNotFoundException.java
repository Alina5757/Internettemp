package ustu.is.InternetLab.GSMWorker.service;

public class GSMWorkerNotFoundException extends RuntimeException{
    public GSMWorkerNotFoundException(Long id) {
        super(String.format("GSMWorker with id [%s] is not found", id));
    }

}