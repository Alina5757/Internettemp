package ustu.is.InternetLab.gsm.service;

public class GSMNotFoundException extends RuntimeException {
    public GSMNotFoundException(Long id) {
        super(String.format("GSM with id [%s] is not found", id));
    }

}
