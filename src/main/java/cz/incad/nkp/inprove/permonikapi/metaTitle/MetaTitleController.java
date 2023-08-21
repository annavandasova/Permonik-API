package cz.incad.nkp.inprove.permonikapi.metaTitle;


import cz.incad.nkp.inprove.permonikapi.metaTitle.dto.MetaTitleWithSpecimensStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("v1/metatitle")
public class MetaTitleController {

    private final MetaTitleService metaTitleService;
    @Autowired
    public MetaTitleController(MetaTitleService metaTitleService) {
        this.metaTitleService = metaTitleService;
    }


    @GetMapping("/{metaTitleId}")
    /* Endpoint used for specimens result table */
    public Optional<MetaTitle> getMetaTitleById(@PathVariable String metaTitleId){
        return metaTitleService.getMetaTitleById(metaTitleId);
    }


    @GetMapping("/all/stats")
    /* Endpoint used for home page */
    public List<MetaTitleWithSpecimensStatsDTO> getOverviewsWithStats(){
        return metaTitleService.getOverviewsWithStats();
    }
}
