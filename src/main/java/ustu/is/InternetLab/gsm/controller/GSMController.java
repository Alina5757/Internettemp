package ustu.is.InternetLab.gsm.controller;

import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.gsm.service.GSMService;
import ustu.is.InternetLab.gsm.model.GSM;

import java.util.List;

@RestController
@RequestMapping("/gsm")
public class GSMController {
    private  final GSMService gsmService;


    public GSMController(GSMService gsmService) {
        this.gsmService = gsmService;
    }

    @GetMapping("/{id}")
    public GSMDto getGSM(@PathVariable Long id){
        return new GSMDto(gsmService.findGSM(id));
    }

    @GetMapping("/")
    public List<GSMDto> getGSMs(){
        return gsmService.findAllGSMs().stream()
                .map(GSMDto::new)
                .toList();
    }

    @PostMapping("/")
    public GSMDto createGSM(@RequestParam("Name") String Name){
        return new GSMDto(gsmService.addGSM(Name));
    }

    @PatchMapping("/{id}")
    public GSMDto updateGSM(@PathVariable Long id,
                         @RequestParam("Name") String Name){
        return new GSMDto(gsmService.updateGSM(id, Name));
    }

    @DeleteMapping("/{id}")
    public GSMDto deleteGSM(@PathVariable Long id){
        return new GSMDto(gsmService.deleteGSM(id));
    }
}