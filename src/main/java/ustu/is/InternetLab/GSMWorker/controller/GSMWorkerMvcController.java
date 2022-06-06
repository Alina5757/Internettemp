package ustu.is.InternetLab.GSMWorker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.GSMWorker.service.GSMWorkerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/gsmworker")
public class GSMWorkerMvcController {

    private final GSMWorkerService GSMWorkerService;

    public GSMWorkerMvcController(GSMWorkerService GSMWorkerService) {
        this.GSMWorkerService = GSMWorkerService;
    }

    @GetMapping
    public String getGSMWorkers(Model model) {
        model.addAttribute("gsmworkers",
                GSMWorkerService.findAllGSMWorkers().stream()
                        .map(GSMWorkerDto::new)
                        .toList());
        return "gsmworker";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editGSMWorker(@PathVariable(required = false) Long id, Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("gsmworkerDto", new GSMWorkerDto());
        } else {
            model.addAttribute("gsmworkerId", id);
            model.addAttribute("gsmworkerDto", new GSMWorkerDto(GSMWorkerService.findGSMWorker(id)));
        }
        return "gsmworker-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveGSMWorker(@PathVariable(required = false) Long id,
                                @ModelAttribute @Valid GSMWorkerDto gsmworkerDto,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "gsmworker-edit";
        }
        if (id == null || id <= 0) {
            GSMWorkerService.addGSMWorker(gsmworkerDto.getIdGSM(), gsmworkerDto.getIdWorker());
        } else {
            GSMWorkerService.updateGSMWorker(id, gsmworkerDto.getIdGSM(), gsmworkerDto.getIdWorker());
        }
        return "redirect:/gsmworker";
    }

    @PostMapping("/delete/{id}")
    public String deleteGSMWorker(@PathVariable Long id) {
        GSMWorkerService.deleteGSMWorker(id);
        return "redirect:/gsmworker";
    }
}