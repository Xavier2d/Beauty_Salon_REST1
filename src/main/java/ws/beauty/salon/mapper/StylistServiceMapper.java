package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.StylistServiceRequest;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.Stylist;
import ws.beauty.salon.model.StylistService;

public final class StylistServiceMapper {

    private StylistServiceMapper() {}

    public static StylistServiceResponse toResponse(StylistService ss) {
        if (ss == null) return null;

        return StylistServiceResponse.builder()
                .idStylist(ss.getStylist() != null ? ss.getStylist().getId() : null)
                .stylistName(ss.getStylist() != null ? ss.getStylist().getFirstName() + " " + ss.getStylist().getLastName() : null)
                .idService(ss.getService() != null ? ss.getService().getId() : null)
                .serviceName(ss.getService() != null ? ss.getService().getServiceName() : null)
                .build();
    }

    public static StylistService toEntity(Stylist stylist, Service service) {
        if (stylist == null || service == null) return null;

        StylistService ss = new StylistService();
        ss.setStylist(stylist);
        ss.setService(service);
        return ss;
    }

    public static void copyToEntity(StylistServiceRequest dto, StylistService ss, Stylist stylist, Service service) {
        if (dto == null || ss == null) return;

        ss.setStylist(stylist != null ? stylist : ss.getStylist());
        ss.setService(service != null ? service : ss.getService());
    }
}


