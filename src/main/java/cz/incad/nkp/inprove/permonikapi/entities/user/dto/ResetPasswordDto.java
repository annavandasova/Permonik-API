package cz.incad.nkp.inprove.permonikapi.entities.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {

    private String id;

    private String oldheslo;

    private String newheslo;
}
