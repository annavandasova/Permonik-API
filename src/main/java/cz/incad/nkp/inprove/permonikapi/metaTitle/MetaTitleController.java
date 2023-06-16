package cz.incad.nkp.inprove.permonikapi.metaTitle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/metatitle")
public class MetaTitleController {

    private final MetaTitleService metaTitleService;
    @Autowired
    public MetaTitleController(MetaTitleService metaTitleService) {
        this.metaTitleService = metaTitleService;
    }

    @GetMapping("/all")
    public List<MetaTitle> getAll(){
        return metaTitleService.getAll();
    }
}
