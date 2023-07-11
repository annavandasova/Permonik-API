package cz.incad.nkp.inprove.permonikapi.volume;

import cz.incad.nkp.inprove.permonikapi.volume.dto.VolumeDTO;
import cz.incad.nkp.inprove.permonikapi.volume.dto.VolumeDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/volume")
public class VolumeController {

    private final VolumeService volumeService;

    @Autowired
    public VolumeController(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @GetMapping("/{volumeId}")
    public Optional<VolumeDTO> getVolumeById(@PathVariable String volumeId){
        return volumeService.getVolumeById(volumeId);
    }

    @GetMapping("/detail/{volumeId}")
    public Optional<VolumeDetailDTO> getVolumeDetailById(@PathVariable String volumeId){
        return volumeService.getVolumeDetailById(volumeId);
    }
}
