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
    public GSM getGSM(@PathVariable Long id){
        return gsmService.findGSM(id);
    }

    @GetMapping("/")
    public List<GSM> getGSMs(){
        return gsmService.findAllGSMs();
    }

    @PostMapping("/")
    public GSM createGSM(@RequestParam("Name") String Name){
        return gsmService.addGSM(Name);
    }

    @PatchMapping("/{id}")
    public GSM updateGSM(@PathVariable Long id,
                         @RequestParam("Name") String Name){
        return gsmService.updateGSM(id, Name);
    }

    @DeleteMapping("/{id}")
    public GSM deleteGSM(@PathVariable Long id){
        return gsmService.deleteGSM(id);
    }
}