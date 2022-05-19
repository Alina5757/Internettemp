package ustu.is.InternetLab.GSMWorker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ustu.is.InternetLab.GSMWorker.model.GSMWorker;
import ustu.is.InternetLab.GSMWorker.repository.GSMWorkerRepository;
import ustu.is.InternetLab.util.validation.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
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