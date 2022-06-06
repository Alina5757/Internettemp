package ustu.is.InternetLab.worker.controller;

import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.WebConfiguration;
import ustu.is.InternetLab.worker.model.Worker;
import ustu.is.InternetLab.worker.service.WorkerService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/worker")
public class WorkerController {
    private  final WorkerService workerService;


    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("/{id}")
    public WorkerDto getWorker(@PathVariable Long id){
        return new WorkerDto(workerService.findWorker(id));
    }

    @GetMapping
    public List<WorkerDto> getWorkers(){
        return workerService.findAllWorkers().stream()
                .map(WorkerDto::new)
                .toList();
    }

    @PostMapping
    public WorkerDto createWorker(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName){
        return new WorkerDto(workerService.addWorker(firstName, lastName));
    }

    @PutMapping("/{id}")
    public WorkerDto updateWorker(@PathVariable Long id,
                                  @RequestBody @Valid WorkerDto workerDto){
        return new WorkerDto(workerService.updateWorker(id, workerDto.getFirstName(), workerDto.getLastName()));
    }

    @DeleteMapping("/{id}")
    public WorkerDto deleteWorker(@PathVariable Long id){
        return new WorkerDto(workerService.deleteWorker(id));
    }
}