package cz.incad.nkp.inprove.permonikapi.originentities.volume.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.dto.VolumeDTO;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.Volume;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.VolumePeriodicity;
import cz.incad.nkp.inprove.permonikapi.originentities.volume.dto.VolumePeriodicityDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Service
public class VolumeDTOMapper implements Function<Volume, VolumeDTO> {

    private List<VolumePeriodicityDTO> getPeriodicity(String periodicity) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();


        List<VolumePeriodicity> volumePeriodicityList = Arrays.asList(objectMapper.readValue(periodicity, VolumePeriodicity[].class));

        return volumePeriodicityList.stream().map(volumePeriodicity -> new VolumePeriodicityDTO(
                volumePeriodicity.getActive(),
                volumePeriodicity.getVydani(),
                volumePeriodicity.getDen(),
                volumePeriodicity.getPocet_stran(),
                volumePeriodicity.getNazev(),
                volumePeriodicity.getPodnazev()
        )).toList();
    }

    @Override
    public VolumeDTO apply(Volume volume){
        try {
            return new VolumeDTO(
                    volume.getId(),
                    volume.getBarCode(),
                    volume.getDateFrom(),
                    volume.getDateTo(),
                    volume.getMetaTitleId(),
                    volume.getMutation(),
                    getPeriodicity(volume.getPeriodicity()),
                    volume.getPagesCount(),
                    volume.getFirstNumber(),
                    volume.getLastNumber(),
                    volume.getNote(),
                    volume.getShowAttachmentsAtTheEnd(),
                    volume.getSignature(),
                    volume.getOwner(),
                    volume.getYear(),
                    volume.getPublicationMark()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
