package cz.incad.nkp.inprove.permonikapi.entities.metatitle.manager;

import cz.incad.nkp.inprove.permonikapi.base.api.ApiResource;
import cz.incad.nkp.inprove.permonikapi.base.api.ManagerApi;
import cz.incad.nkp.inprove.permonikapi.entities.metatitle.NewMetaTitle;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cz.incad.nkp.inprove.permonikapi.security.permission.ResourcesConstants.META_TITLE;

@ApiResource(META_TITLE)
@RestController
@RequestMapping("/api/v2/meta-title")
@Tag(name = "Meta Title Manager API", description = "API for managing meta titles")
@RequiredArgsConstructor
public class MetaTitleManagerApi implements ManagerApi<NewMetaTitle> {

    @Getter
    private final MetaTitleManagerService service;

}
