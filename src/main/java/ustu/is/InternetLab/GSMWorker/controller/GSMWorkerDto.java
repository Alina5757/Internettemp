package ustu.is.InternetLab.GSMWorker.controller;

import ustu.is.InternetLab.GSMWorker.model.GSMWorker;

public class GSMWorkerDto {
    private Long id;
    private Long IdGSM;
    private Long IdWorker;

    public GSMWorkerDto(GSMWorker gsmworker) {
        this.id = gsmworker.getId();
        this.IdGSM = gsmworker.getIdGSM();
        this.IdWorker = gsmworker.getIdWorker();
    }

    public GSMWorkerDto(){}

    public Long getId() {
        return id;
    }

    public Long getIdGSM() {
        return IdGSM;
    }
    public void setIdGSM(Long Idgsm) { this.IdGSM = Idgsm; }

    public Long getIdWorker() {
        return IdWorker;
    }
    public void setIdWorker(Long Idworker) { this.IdWorker = Idworker; }
}