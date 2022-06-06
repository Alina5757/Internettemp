package ustu.is.InternetLab.GSMWorker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ustu.is.InternetLab.GSMWorker.model.GSMWorker;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class GSMWorkerService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public GSMWorker addGSMWorker (Long IdGSM, Long IdWorker){
        if (!(IdGSM != null) || !(IdWorker != null)){
            throw new IllegalArgumentException("Id's is null or empty");
        }
        final GSMWorker gsmWorker = new GSMWorker(IdGSM, IdWorker);
        em.persist(gsmWorker);
        return gsmWorker;
    }

    @Transactional(readOnly = true)
    public GSMWorker findGSMWorker(Long id){
        final GSMWorker gsmWorker = em.find(GSMWorker.class, id);
        if (gsmWorker == null){
            throw new EntityNotFoundException(String.format("GSMWorker with id [%s] is not found", id));
        }
        return  gsmWorker;
    }

    @Transactional(readOnly = true)
    public List<GSMWorker> findAllGSMWorkers(){
        return em.createQuery("select gsmworker from GSMWorker gsmworker", GSMWorker.class)
                .getResultList();
    }

    @Transactional
    public GSMWorker updateGSMWorker(Long id, Long IdGSM, Long IdWorker){
        if (!(IdGSM == null) || !(IdWorker == null)){
            throw  new IllegalArgumentException("GSMWorker Id's is null or empty");
        }
        final GSMWorker currentGSMWorker = findGSMWorker(id);
        currentGSMWorker.setIdGSM(IdGSM);
        currentGSMWorker.setIdWorker(IdWorker);
        return em.merge(currentGSMWorker);
    }

    @Transactional
    public GSMWorker deleteGSMWorker(Long id){
        final GSMWorker currentGSMWorker = findGSMWorker(id);
        em.remove(currentGSMWorker);
        return currentGSMWorker;
    }

    @Transactional
    public void deleteAllGSMWorkers(){
        em.createQuery("delete from GSMWorker").executeUpdate();
    }
}