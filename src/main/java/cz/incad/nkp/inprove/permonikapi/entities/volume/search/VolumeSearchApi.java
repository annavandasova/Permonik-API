package cz.incad.nkp.inprove.permonikapi.entities.volume.search;

import cz.incad.nkp.inprove.permonikapi.base.api.ApiResource;
import cz.incad.nkp.inprove.permonikapi.base.api.SearchApi;
import cz.incad.nkp.inprove.permonikapi.entities.volume.NewVolume;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cz.incad.nkp.inprove.permonikapi.security.permission.ResourcesConstants.VOLUME;

@ApiResource(VOLUME)
@RestController
@RequestMapping("/api/v2/volume")
@Tag(name = "Volume Search API", description = "API for retrieving volumes")
@RequiredArgsConstructor
public class VolumeSearchApi implements SearchApi<NewVolume> {

    @Getter
    private final VolumeSearchService service;
}
