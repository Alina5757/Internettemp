package ustu.is.InternetLab.gsm.controller;

import org.springframework.web.bind.annotation.*;
import ustu.is.InternetLab.configuration.WebConfiguration;
import ustu.is.InternetLab.gsm.service.GSMService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/gsm")
public class GSMController {
    private  final GSMService gsmService;


    public GSMController(GSMService gsmService) {
        this.gsmService = gsmService;
    }

    @GetMapping("/{id}")
    public GSMDto getGSM(@PathVariable Long id){
        return new GSMDto(gsmService.findGSM(id));
    }

    @GetMapping
    public List<GSMDto> getGSMs(){
        return gsmService.findAllGSMs().stream()
                .map(GSMDto::new)
                .toList();
    }

    @PostMapping
    public GSMDto createGSM(@RequestParam("name") String name){
        return new GSMDto(gsmService.addGSM(name));
    }

    @PutMapping("/{id}")
    public GSMDto updateGSM(@PathVariable Long id,
                         @RequestBody @Valid GSMDto gsmDto){
        return new GSMDto(gsmService.updateGSM(id, gsmDto.getName()));
    }

    @DeleteMapping("/{id}")
    public GSMDto deleteGSM(@PathVariable Long id){
        return new GSMDto(gsmService.deleteGSM(id));
    }
}