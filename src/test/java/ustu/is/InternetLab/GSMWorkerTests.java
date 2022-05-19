package ustu.is.InternetLab;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ustu.is.InternetLab.GSMWorker.model.GSMWorker;
import ustu.is.InternetLab.GSMWorker.service.GSMWorkerNotFoundException;
import ustu.is.InternetLab.GSMWorker.service.GSMWorkerService;
import ustu.is.InternetLab.gsm.model.GSM;
import ustu.is.InternetLab.gsm.service.GSMNotFoundException;
import ustu.is.InternetLab.gsm.service.GSMService;
import ustu.is.InternetLab.worker.model.Worker;
import ustu.is.InternetLab.worker.service.WorkerNotFoundException;
import ustu.is.InternetLab.worker.service.WorkerService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SpringBootTest
public class GSMWorkerTests {
    private static final Logger log = LoggerFactory.getLogger(GSMWorkerTests.class);

    @Autowired
    private GSMService gsmService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private GSMWorkerService gsmWorkerService;

    //Заполнение таблиц
    @Test
    void testCreate() {
        gsmService.deleteAllGSMs();

        final GSM gsm1 = gsmService.addGSM("Бензин А-98");
        log.info(gsm1.toString());
        final GSM gsm2 = gsmService.addGSM("Бензин А-100");
        log.info(gsm2.toString());
        final GSM gsm3 = gsmService.addGSM("Бензин А-96");
        log.info(gsm3.toString());
        final GSM gsm4 = gsmService.addGSM("Керосин");
        log.info(gsm4.toString());

        workerService.deleteAllWorkers();

        final Worker worker1 = workerService.addWorker("Иван", "Иванов");
        log.info(worker1.toString());
        final Worker worker2 = workerService.addWorker("Михаил", "Чернов");
        log.info(worker2.toString());
        final Worker worker3 = workerService.addWorker("Кирилл", "Федоров");
        log.info(worker3.toString());

        gsmWorkerService.deleteAllGSMWorkers();

        final GSMWorker gsmWorker1 = gsmWorkerService.addGSMWorker(gsm1.getId(), worker1.getId());
        log.info(gsmWorker1.toString());
        final GSMWorker gsmWorker2 = gsmWorkerService.addGSMWorker(gsm1.getId(), worker2.getId());
        log.info(gsmWorker2.toString());
        final GSMWorker gsmWorker3 = gsmWorkerService.addGSMWorker(gsm1.getId(), worker3.getId());
        log.info(gsmWorker3.toString());
        final GSMWorker gsmWorker4 = gsmWorkerService.addGSMWorker(gsm2.getId(), worker2.getId());
        log.info(gsmWorker4.toString());
        final GSMWorker gsmWorker5 = gsmWorkerService.addGSMWorker(gsm3.getId(), worker3.getId());
        log.info(gsmWorker5.toString());
        final GSMWorker gsmWorker6 = gsmWorkerService.addGSMWorker(gsm4.getId(), worker3.getId());
        log.info(gsmWorker6.toString());
    }

    @Test
    void testWorkerCreate() {
        workerService.deleteAllWorkers();
        final Worker worker = workerService.addWorker("Иван", "Иванов");
        log.info(worker.toString());
        Assertions.assertNotNull(worker.getId());
    }

    @Test
    void testWorkerRead() {
        workerService.deleteAllWorkers();
        final Worker worker = workerService.addWorker("Иван", "Иванов");
        log.info(worker.toString());
        final Worker findWorker = workerService.findWorker(worker.getId());
        log.info(findWorker.toString());
        Assertions.assertEquals(worker, findWorker);
    }

    @Test
    void testWorkerReadNotFound() {
        workerService.deleteAllWorkers();
        Assertions.assertThrows(WorkerNotFoundException.class, () -> workerService.findWorker(-1L));
    }

    @Test
    void testWorkerReadAll() {
        workerService.deleteAllWorkers();
        workerService.addWorker("Иван", "Иванов");
        workerService.addWorker("Петр", "Петров");
        final List<Worker> workers = workerService.findAllWorkers();
        log.info(workers.toString());
        Assertions.assertEquals(workers.size(), 2);
    }

    ///////////////////////////////////////////////////////////////////////////////

    @Test
    void testGSMCreate() {
        gsmService.deleteAllGSMs();
        final GSM gsm = gsmService.addGSM("Бензин А-100");
        log.info(gsm.toString());
        Assertions.assertNotNull(gsm.getId());
    }

    @Test
    void testGSMRead() {
        gsmService.deleteAllGSMs();
        final GSM gsm = gsmService.addGSM("Бензин А-102");
        log.info(gsm.toString());
        final GSM findGSM = gsmService.findGSM(gsm.getId());
        log.info(findGSM.toString());
        Assertions.assertEquals(gsm, findGSM);
    }

    @Test
    void testGSMReadNotFound() {
        gsmService.deleteAllGSMs();
        Assertions.assertThrows(GSMNotFoundException.class, () -> gsmService.findGSM(-1L));
    }

    @Test
    void testGSMReadAll() {
        gsmService.deleteAllGSMs();
        gsmService.addGSM("Бензин В-100");
        gsmService.addGSM("Бензин В-102");
        final List<GSM> gsms = gsmService.findAllGSMs();
        log.info(gsms.toString());
        Assertions.assertEquals(gsms.size(), 2);
    }

    /////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void testGSMWorkerCreate() {
        gsmWorkerService.deleteAllGSMWorkers();
        GSM gsm1 = gsmService.addGSM("Бензин А-1");
        Worker worker1 = workerService.addWorker("Иван", "Иванов");
        final GSMWorker gsmworker = gsmWorkerService.addGSMWorker(gsm1.getId(), worker1.getId());
        log.info(gsmworker.toString());
        Assertions.assertNotNull(gsmworker.getId());
    }

    @Test
    void testGSMWorkerRead() {
        gsmWorkerService.deleteAllGSMWorkers();
        final GSM gsm1 = gsmService.addGSM("Бензин А-1");
        final Worker worker1 = workerService.addWorker("Иван", "Иванов");
        final GSMWorker gsmWorker = gsmWorkerService.addGSMWorker(gsm1.getId(), worker1.getId());
        log.info(gsmWorker.toString());
        final GSMWorker findGSMWorker = gsmWorkerService.findGSMWorker(gsmWorker.getId());
        log.info(findGSMWorker.toString());
        Assertions.assertEquals(gsmWorker, findGSMWorker);
    }

    @Test
    void testGSMWorkerReadNotFound() {
        gsmWorkerService.deleteAllGSMWorkers();
        Assertions.assertThrows(GSMWorkerNotFoundException.class, () -> gsmWorkerService.findGSMWorker(-1L));
    }

    @Test
    void testGSMWorkerReadAll() {
        gsmWorkerService.deleteAllGSMWorkers();
        final GSM gsm1 = gsmService.addGSM("Бензин А-1");
        final Worker worker1 = workerService.addWorker("Иван", "Иванов");
        gsmWorkerService.addGSMWorker(gsm1.getId(), worker1.getId());
        final GSM gsm2 = gsmService.addGSM("Бензин А-30");
        final Worker worker2 = workerService.addWorker("Семен", "Семенович");
        gsmWorkerService.addGSMWorker(gsm2.getId(), worker2.getId());
        final List<GSMWorker> gsmworkers = gsmWorkerService.findAllGSMWorkers();
        log.info(gsmworkers.toString());
        Assertions.assertEquals(gsmworkers.size(), 2);
    }
}