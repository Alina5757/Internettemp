package ustu.is.InternetLab.gsm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.gsm.service.GSMService;

import javax.validation.Valid;

@Controller
@RequestMapping("/gsm")
public class GSMMvcController {

    private final GSMService GSMService;

    public GSMMvcController(GSMService GSMService) {
        this.GSMService = GSMService;
    }

    @GetMapping
    public String getGSMs(Model model) {
        model.addAttribute("gsms",
                GSMService.findAllGSMs().stream()
                        .map(GSMDto::new)
                        .toList());
        return "gsm";
    }

    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editGSM(@PathVariable(required = false) Long id,
                          Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("gsmDto", new GSMDto());
        } else {
            model.addAttribute("gsmId", id);
            model.addAttribute("gsmDto", new GSMDto(GSMService.findGSM(id)));
        }
        return "gsm-edit";
    }

    @PostMapping(value = {"", "/{id}"})
    public String saveGSM(@PathVariable(required = false) Long id,
                          @ModelAttribute @Valid GSMDto gsmDto,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "gsm-edit";
        }
        if (id == null || id <= 0) {
            GSMService.addGSM(gsmDto.getName());
        } else {
            GSMService.updateGSM(id, gsmDto.getName());
        }
        return "redirect:/gsm";
    }

    @PostMapping("/delete/{id}")
    public String deleteGSM(@PathVariable Long id) {
        GSMService.deleteGSM(id);
        return "redirect:/gsm";
    }
}