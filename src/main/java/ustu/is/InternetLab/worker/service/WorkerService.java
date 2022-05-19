package ustu.is.InternetLab.worker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ustu.is.InternetLab.util.validation.ValidatorUtil;
import ustu.is.InternetLab.worker.model.Worker;
import ustu.is.InternetLab.worker.repository.WorkerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final ValidatorUtil validatorUtil;

    public WorkerService(WorkerRepository workerRepository, ValidatorUtil validatorUtil) {
        this.workerRepository = workerRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public Worker addWorker (String firstName, String lastName){
        final Worker worker = new Worker(firstName, lastName);
        validatorUtil.validate(worker);
        return workerRepository.save(worker);
    }

    @Transactional(readOnly = true)
    public Worker findWorker(Long id){
        final Optional<Worker> worker = workerRepository.findById(id);
        return worker.orElseThrow(() -> new WorkerNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Worker> findAllWorkers(){ return workerRepository.findAll();
    }

    @Transactional
    public Worker updateWorker(Long id, String firstname, String lastname){
        final Worker currentWorker = findWorker(id);
        currentWorker.setFirstName(firstname);
        currentWorker.setLastName(lastname);
        validatorUtil.validate(currentWorker);
        return workerRepository.save(currentWorker);
    }

    @Transactional
    public Worker deleteWorker(Long id){
        final Worker currentWorker = findWorker(id);
        workerRepository.delete(currentWorker);
        return currentWorker;
    }

    @Transactional
    public void deleteAllWorkers(){
        workerRepository.deleteAll();
    }
}