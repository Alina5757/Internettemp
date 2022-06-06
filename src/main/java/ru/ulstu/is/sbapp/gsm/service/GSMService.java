package ru.ulstu.is.sbapp.gsm.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.sbapp.gsm.model.GSM;
import ru.ulstu.is.sbapp.gsm.repository.GSMRepository;
import ru.ulstu.is.sbapp.util.validation.ValidatorUtil;

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
    public GSM addGSM (String name){
        final GSM gsm = new GSM(name);
        validatorUtil.validate(gsm);
        return gsmRepository.save(gsm);
    }

    @Transactional(readOnly = true)
    public GSM findGSM(Long id){
        final Optional<GSM> gsm = gsmRepository.findById(id);
        return gsm.orElseThrow(() -> new GSMNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<GSM> findAllGSMs(){ return gsmRepository.findAll(); }

    @Transactional
    public GSM updateGSM(Long id, String name){
        final GSM currentGSM = findGSM(id);
        currentGSM.setName(name);
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