package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.ServiceRequest;
import ws.beauty.salon.dto.ServiceResponse;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.ServiceCategory;

public class ServiceMapper {

    public static ServiceResponse toResponse(Service entity) {
        ServiceResponse res = new ServiceResponse();
        res.setId(entity.getId());
        res.setServiceName(entity.getServiceName());
        res.setDescription(entity.getDescription());
        res.setPrice(entity.getPrice());
        res.setEstimatedDuration(entity.getEstimatedDuration());
        res.setCategoryName(
            entity.getCategory() != null ? entity.getCategory().getCategoryName() : null
        );
        res.setCreatedAt(entity.getCreatedAt());
        return res;
    }

    public static Service toEntity(ServiceRequest req, ServiceCategory category) {
        Service entity = new Service();
        entity.setServiceName(req.getServiceName());
        entity.setDescription(req.getDescription());
        entity.setPrice(req.getPrice());
        entity.setEstimatedDuration(req.getEstimatedDuration());
        entity.setCategory(category);
        return entity;
    }

    public static void copyToEntity(ServiceRequest req, Service entity, ServiceCategory category) {
        entity.setServiceName(req.getServiceName());
        entity.setDescription(req.getDescription());
        entity.setPrice(req.getPrice());
        entity.setEstimatedDuration(req.getEstimatedDuration());
        entity.setCategory(category);
    }
}
