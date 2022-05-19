package ustu.is.InternetLab.GSMWorker.controller;

import ustu.is.InternetLab.GSMWorker.model.GSMWorker;

public class GSMWorkerDto {
    private final Long id;
    private final Long IdGSM;
    private final Long IdWorker;

    public GSMWorkerDto(GSMWorker gsmworker) {
        this.id = gsmworker.getId();
        this.IdGSM = gsmworker.getIdGSM();
        this.IdWorker = gsmworker.getIdWorker();
    }

    public Long getId() {
        return id;
    }

    public Long getIdGSM() {
        return IdGSM;
    }

    public Long getIdWorker() {
        return IdWorker;
    }

}