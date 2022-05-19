package ustu.is.InternetLab.GSMWorker.controller;

import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.GSMWorker.model.GSMWorker;
import ustu.is.InternetLab.GSMWorker.service.GSMWorkerService;

import java.util.List;

@RestController
@RequestMapping("/gsmWorker")
public class GSMWorkerController {
    private  final GSMWorkerService GSMworkerService;

    public GSMWorkerController(GSMWorkerService GSMworkerService) {
        this.GSMworkerService = GSMworkerService;
    }

    @GetMapping("/{id}")
    public GSMWorkerDto getGSMWorker(@PathVariable Long id){
        return new GSMWorkerDto(GSMworkerService.findGSMWorker(id));
    }

    @GetMapping("/")
    public List<GSMWorkerDto> getGSMWorkers(){
        return GSMworkerService.findAllGSMWorkers().stream()
                .map(GSMWorkerDto::new)
                .toList();
    }

    @PostMapping("/")
    public GSMWorkerDto createGSMWorker(@RequestParam("IdGSM") Long IdGSM,
                               @RequestParam("IdWorker") Long IdWorker){
        return new GSMWorkerDto(GSMworkerService.addGSMWorker(IdGSM, IdWorker));
    }

    @PatchMapping("/{id}")
    public GSMWorkerDto updateGSMWorker(@PathVariable Long id,
                               @RequestParam("IdGSM") Long IdGSM,
                               @RequestParam("IdWorker") Long IdWorker){
        return new GSMWorkerDto(GSMworkerService.updateGSMWorker(id, IdGSM, IdWorker));
    }

    @DeleteMapping("/{id}")
    public GSMWorkerDto deleteGSMWorker(@PathVariable Long id){
        return new GSMWorkerDto(GSMworkerService.deleteGSMWorker(id));
    }
}