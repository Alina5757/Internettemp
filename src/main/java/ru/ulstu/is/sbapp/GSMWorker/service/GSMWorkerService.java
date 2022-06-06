package ru.ulstu.is.sbapp.GSMWorker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.GSMWorker.model.GSMWorker;
import ru.ulstu.is.sbapp.GSMWorker.repository.GSMWorkerRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

import java.util.List;
import java.util.Optional;

@Service
public class GSMWorkerService {
    private final GSMWorkerRepository gsmworkerRepository;
    private final ValidatorUtil validatorUtil;

    public GSMWorkerService(GSMWorkerRepository gsmworkerRepository, ValidatorUtil validatorUtil) {
        this.gsmworkerRepository = gsmworkerRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public GSMWorker addGSMWorker (Long IdGSM, Long IdWorker){
        final GSMWorker gsmWorker = new GSMWorker(IdGSM, IdWorker);
        validatorUtil.validate(gsmWorker);
        return gsmworkerRepository.save(gsmWorker);
    }

    @Transactional(readOnly = true)
    public GSMWorker findGSMWorker(Long id){
        final Optional<GSMWorker> gsmworker = gsmworkerRepository.findById(id);
        return gsmworker.orElseThrow(() -> new GSMWorkerNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<GSMWorker> findAllGSMWorkers(){
        return gsmworkerRepository.findAll();
    }

    @Transactional
    public GSMWorker updateGSMWorker(Long id, Long IdGSM, Long IdWorker){
        final GSMWorker currentGSMWorker = findGSMWorker(id);
        currentGSMWorker.setIdGSM(IdGSM);
        currentGSMWorker.setIdWorker(IdWorker);
        validatorUtil.validate(currentGSMWorker);
        return gsmworkerRepository.save(currentGSMWorker);
    }

    @Transactional
    public GSMWorker deleteGSMWorker(Long id){
        final GSMWorker currentGSMWorker = findGSMWorker(id);
        gsmworkerRepository.delete(currentGSMWorker);
        return currentGSMWorker;
    }

    @Transactional
    public void deleteAllGSMWorkers(){
        gsmworkerRepository.deleteAll();
    }
}