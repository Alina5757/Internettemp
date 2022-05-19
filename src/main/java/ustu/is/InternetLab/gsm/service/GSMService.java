package ustu.is.InternetLab.gsm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ustu.is.InternetLab.gsm.model.GSM;
import ustu.is.InternetLab.gsm.repository.GSMRepository;
import ustu.is.InternetLab.util.validation.ValidatorUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class GSMService {
    private final GSMRepository gsmRepository;
    private final ValidatorUtil validatorUtil;

    public GSMService(GSMRepository gsmRepository, ValidatorUtil validatorUtil) {
        this.gsmRepository = gsmRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public GSM addGSM (String Name){
        final GSM gsm = new GSM(Name);
        validatorUtil.validate(gsm);
        return gsmRepository.save(gsm);
    }

    @Transactional(readOnly = true)
    public GSM findGSM(Long id){
        final Optional<GSM> gsm = gsmRepository.findById(id);
        return gsm.orElseThrow(() -> new GSMNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<GSM> findAllGSMs(){
        return gsmRepository.findAll();
    }

    @Transactional
    public GSM updateGSM(Long id, String Name){
        final GSM currentGSM = findGSM(id);
        currentGSM.setName(Name);
        validatorUtil.validate(currentGSM);
        return gsmRepository.save(currentGSM);
    }

    @Transactional
    public GSM deleteGSM(Long id){
        final GSM currentGSM = findGSM(id);
        gsmRepository.delete(currentGSM);
        return currentGSM;
    }

    @Transactional
    public void deleteAllGSMs(){
        gsmRepository.deleteAll();
    }
}