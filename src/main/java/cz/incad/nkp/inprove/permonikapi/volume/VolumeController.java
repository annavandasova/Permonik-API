package cz.incad.nkp.inprove.permonikapi.volume;

import cz.incad.nkp.inprove.permonikapi.volume.dto.VolumeDTO;
import cz.incad.nkp.inprove.permonikapi.volume.dto.VolumeDetailDTO;
import cz.incad.nkp.inprove.permonikapi.volume.dto.VolumeOverviewStatsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
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

    @GetMapping("/stats/{volumeId}")
    // Endpoint used for modal popup, where all stats are showed, also with defects of specimens
    public Optional<VolumeOverviewStatsDTO> getVolumeOverviewStats(@PathVariable String volumeId){
        return volumeService.getVolumeOverviewStats(volumeId);
    }
}
