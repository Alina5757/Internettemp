package ru.ulstu.is.sbapp.GSMWorker.controller;

import org.springframework.web.bind.annotation.*;
import ru.ulstu.is.sbapp.GSMWorker.model.GSMWorker;
import ru.ulstu.is.sbapp.GSMWorker.service.GSMWorkerService;

import java.util.List;

@RestController
@RequestMapping("/gsmWorker")
public class GSMWorkerController {
    private  final GSMWorkerService GSMworkerService;

    public GSMWorkerController(GSMWorkerService GSMworkerService) {
        this.GSMworkerService = GSMworkerService;
    }

    @GetMapping("/{id}")
    public GSMWorker getGSMWorker(@PathVariable Long id){
        return GSMworkerService.findGSMWorker(id);
    }

    @GetMapping("/")
    public List<GSMWorker> getGSMWorkers(){
        return GSMworkerService.findAllGSMWorkers();
    }

    @PostMapping("/")
    public GSMWorker createGSMWorker(@RequestParam("IdGSM") Long IdGSM,
                               @RequestParam("IdWorker") Long IdWorker){
        return GSMworkerService.addGSMWorker(IdGSM, IdWorker);
    }

    @PatchMapping("/{id}")
    public GSMWorker updateGSMWorker(@PathVariable Long id,
                               @RequestParam("IdGSM") Long IdGSM,
                               @RequestParam("IdWorker") Long IdWorker){
        return GSMworkerService.updateGSMWorker(id, IdGSM, IdWorker);
    }

    @DeleteMapping("/{id}")
    public GSMWorker deleteGSMWorker(@PathVariable Long id){
        return GSMworkerService.deleteGSMWorker(id);
    }
}