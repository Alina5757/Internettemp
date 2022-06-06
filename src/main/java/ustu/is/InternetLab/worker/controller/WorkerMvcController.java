package ustu.is.InternetLab.worker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.worker.service.WorkerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/worker")
public class WorkerMvcController {

    private final WorkerService workerService;

    public WorkerMvcController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public String getWorkers(Model model) {
        model.addAttribute("workers",
                workerService.findAllWorkers().stream()
                        .map(WorkerDto::new)
                        .toList());
        return "worker";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editWorker(@PathVariable(required = false) Long id,
                             Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("workerDto", new WorkerDto());
        } else {
            model.addAttribute("workerId", id);
            model.addAttribute("workerDto", new WorkerDto(workerService.findWorker(id)));
        }
        return "worker-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveWorker(@PathVariable(required = false) Long id,
                             @ModelAttribute @Valid WorkerDto workerDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "worker-edit";
        }
        if (id == null || id <= 0) {
            workerService.addWorker(workerDto.getFirstName(), workerDto.getLastName());
        } else {
            workerService.updateWorker(id, workerDto.getFirstName(), workerDto.getLastName());
        }
        return "redirect:/worker";
    }

    @PostMapping("/delete/{id}")
    public String deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        return "redirect:/worker";
    }
}