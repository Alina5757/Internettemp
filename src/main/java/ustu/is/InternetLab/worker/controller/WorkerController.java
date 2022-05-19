package ustu.is.InternetLab.worker.controller;

import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.worker.model.Worker;
import ustu.is.InternetLab.worker.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    private  final WorkerService workerService;


    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/{id}")
    public Worker getWorker(@PathVariable Long id){
        return workerService.findWorker(id);
    }

    @GetMapping("/")
    public List<Worker> getWorkers(){
        return workerService.findAllWorkers();
    }

    @PostMapping("/")
    public Worker createWorker(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName){
        return workerService.addWorker(firstName, lastName);
    }

    @PatchMapping("/{id}")
    public Worker updateWorker(@PathVariable Long id,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName){
        return workerService.updateWorker(id, firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public Worker deleteWorker(@PathVariable Long id){
        return workerService.deleteWorker(id);
    }
}