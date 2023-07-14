package cz.incad.nkp.inprove.permonikapi.specimen.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.incad.nkp.inprove.permonikapi.specimen.Specimen;
import cz.incad.nkp.inprove.permonikapi.specimen.SpecimenPages;
import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimenDTO;
import cz.incad.nkp.inprove.permonikapi.specimen.dto.SpecimenPagesDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SpecimenDTOMapper implements Function<Specimen, SpecimenDTO> {

    private SpecimenPagesDTO getPages (String pages) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        SpecimenPages specimenPages = objectMapper.readValue(pages, SpecimenPages.class);

        return new SpecimenPagesDTO(
                specimenPages.getDamaged(),
                specimenPages.getMissing()
        );

    }

    @Override
    public SpecimenDTO apply (Specimen specimen) {
        try {
            return new SpecimenDTO(
                    specimen.getId(),
                    specimen.getIdIssue(),
                    specimen.getIdMetaTitle(),
                    specimen.getBarCode(),
                    specimen.getNumExists(),
                    specimen.getNumMissing(),
                    specimen.getSignature(),
                    specimen.getOwner(),
                    specimen.getStates(),
                    specimen.getState(),
                    specimen.getStateDescription(),
                    getPages(specimen.getPages()),
                    specimen.getNote(),
                    specimen.getName(),
                    specimen.getSubName(),
                    specimen.getPublication(),
                    specimen.getMutation(),
                    specimen.getPublicationMark(),
                    specimen.getPublicationDate(),
                    specimen.getPublicationDay(),
                    specimen.getPeriodicity(),
                    specimen.getNumber(),
                    specimen.getMetaTitleName(),
                    specimen.getPagesCount(),
                    specimen.getAttachment()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
