package cz.incad.nkp.inprove.permonikapi.user.dto;

public record UserDTO (
        String id,
        Boolean active,
        String email,
        String name,
        String owner,
        String note,
        String role,
        String userName
) { }
