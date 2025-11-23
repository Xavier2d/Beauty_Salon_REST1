package ws.beauty.salon.mapper;
import ws.beauty.salon.dto.StylistRequest;
import ws.beauty.salon.dto.StylistResponse;
import ws.beauty.salon.model.Stylist;
public final class StylistMapper {

    public static StylistResponse toResponse(Stylist stylist) {
        if (stylist == null) return null;
        return StylistResponse.builder()
                .id(stylist.getId())
                .firstName(stylist.getFirstName())
                .lastName(stylist.getLastName())
                .specialty(stylist.getSpecialty())
                .workSchedule(stylist.getWorkSchedule())
                .available(stylist.getAvailable())
                .build();
    }

    public static Stylist toEntity(StylistRequest dto) {
        if (dto == null) return null;
        Stylist stylist = new Stylist();
        stylist.setFirstName(dto.getFirstName());
        stylist.setLastName(dto.getLastName());
        stylist.setSpecialty(dto.getSpecialty());
        stylist.setWorkSchedule(dto.getWorkSchedule());
        stylist.setAvailable(dto.getAvailable() != null ? dto.getAvailable() : true);
        return stylist;
    }

    public static void copyToEntity(StylistRequest dto, Stylist stylist) {
        if (dto == null || stylist == null) return;
        stylist.setFirstName(dto.getFirstName());
        stylist.setLastName(dto.getLastName());
        stylist.setSpecialty(dto.getSpecialty());
        stylist.setWorkSchedule(dto.getWorkSchedule());
        stylist.setAvailable(dto.getAvailable() != null ? dto.getAvailable() : stylist.getAvailable());
    }
}
