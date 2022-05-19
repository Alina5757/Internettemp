package ustu.is.InternetLab.worker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ustu.is.InternetLab.worker.model.Worker;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class WorkerService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Worker addWorker (String firstName, String lastName){
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)){
            throw new IllegalArgumentException("Worker name is null or empty");
        }
        final Worker worker = new Worker(firstName, lastName);
        em.persist(worker);
        return worker;
    }

    @Transactional(readOnly = true)
    public Worker findWorker(Long id){
        final Worker worker = em.find(Worker.class, id);
        if (worker == null){
            throw new EntityNotFoundException(String.format("Worker with id [%s] is not found", id));
        }
        return  worker;
    }

    @Transactional(readOnly = true)
    public List<Worker> findAllWorkers(){
        return em.createQuery("select w from Worker w", Worker.class)
                .getResultList();
    }

    @Transactional
    public Worker updateWorker(Long id, String firstname, String lastname){
        if (!StringUtils.hasText(firstname) || !StringUtils.hasText(lastname)){
            throw  new IllegalArgumentException("Worker name is null or empty");
        }
        final Worker currentWorker = findWorker(id);
        currentWorker.setFirstName(firstname);
        currentWorker.setLastName(lastname);
        return em.merge(currentWorker);
    }

    @Transactional
    public Worker deleteWorker(Long id){
        final Worker currentWorker = findWorker(id);
        em.remove(currentWorker);
        return currentWorker;
    }

    @Transactional
    public void deleteAllWorkers(){
        em.createQuery("delete from Worker").executeUpdate();
    }
}