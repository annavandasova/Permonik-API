package cz.incad.nkp.inprove.permonikapi.specimen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/specimen")
public class SpecimenController {

    private final SpecimenService specimenService;

    @Autowired
    public SpecimenController(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }


//    @PostMapping("/stats/overviews")
//    public List<SpecimensOverviewDTO> getOverviews(@RequestBody List<String> titles){
//        return specimenService.getOverviews(titles);
//    }

//    @GetMapping("/stats/overview/{idTitle}")
//    public SpecimensOverviewDTO getOverview(@PathVariable String idTitle){
//        return specimenService.getOverview(idTitle);
//    }

}
