package ustu.is.InternetLab.gsm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ustu.is.InternetLab.gsm.model.GSM;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class GSMService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public GSM addGSM (String Name){
        if (!StringUtils.hasText(Name)){
            throw new IllegalArgumentException("GSM name is null or empty");
        }
        final GSM gsm = new GSM(Name);
        em.persist(gsm);
        return gsm;
    }

    @Transactional(readOnly = true)
    public GSM findGSM(Long id){
        final GSM gsm = em.find(GSM.class, id);
        if (gsm == null){
            throw new EntityNotFoundException(String.format("GSM with id [%s] is not found", id));
        }
        return  gsm;
    }

    @Transactional(readOnly = true)
    public List<GSM> findAllGSMs(){
        return em.createQuery("select g from GSM g", GSM.class)
                .getResultList();
    }

    @Transactional
    public GSM updateGSM(Long id, String Name){
        if (!StringUtils.hasText(Name)){
            throw new IllegalArgumentException("GSM name is null or empty");
        }
        final GSM currentGSM = findGSM(id);
        currentGSM.setName(Name);
        return em.merge(currentGSM);
    }

    @Transactional
    public GSM deleteGSM(Long id){
        final GSM currentGSM = findGSM(id);
        em.remove(currentGSM);
        return currentGSM;
    }

    @Transactional
    public void deleteAllGSMs(){
        em.createQuery("delete from GSM").executeUpdate();
    }
}