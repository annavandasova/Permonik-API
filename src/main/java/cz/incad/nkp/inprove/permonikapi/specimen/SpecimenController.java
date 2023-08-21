package cz.incad.nkp.inprove.permonikapi.specimen;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimensWithFacetsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("v1/specimen")
public class SpecimenController {

    private final SpecimenService specimenService;

    @Autowired
    public SpecimenController(SpecimenService specimenService) {
        this.specimenService = specimenService;
    }

    @PostMapping("/{idTitle}")
    public SpecimensWithFacetsDTO getSpecimensWithFacetsByMetaTitle(
            @PathVariable String idTitle,
            @RequestParam Integer offset,
            @RequestParam Integer rows,
            @RequestParam String facets,
            @RequestParam String view
    ) throws JsonProcessingException {
        return specimenService.getSpecimensWithFacetsByMetaTitle(idTitle, offset, rows, facets, view);
    }

    @GetMapping("/start_date/{idTitle}")
    public Object getSpecimensStartDate(@PathVariable String idTitle){
        return specimenService.getSpecimensStartDateByMetaTitle(idTitle);
    }



}
