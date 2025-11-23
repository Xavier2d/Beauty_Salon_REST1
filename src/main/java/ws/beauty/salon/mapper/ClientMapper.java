package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.ClientRequest;
import ws.beauty.salon.dto.ClientResponse;
import ws.beauty.salon.model.Client;

public class ClientMapper {

   public static ClientResponse toResponse(Client entity) {
        ClientResponse res = new ClientResponse();
        res.setId(entity.getId());
        res.setFirstName(entity.getFirstName());
        res.setLastName(entity.getLastName());
        res.setEmail(entity.getEmail());
        res.setPhone(entity.getPhone());
        res.setRegistrationDate(entity.getRegistrationDate());
        res.setPreferences(entity.getPreferences());
        res.setSatisfactionHistory(entity.getSatisfactionHistory());
        return res;
    }

    public static Client toEntity(ClientRequest req) {
        Client entity = new Client();
        entity.setFirstName(req.getFirstName());
        entity.setLastName(req.getLastName());
        entity.setEmail(req.getEmail());
        entity.setPhone(req.getPhone());
        entity.setPreferences(req.getPreferences());
        entity.setSatisfactionHistory(req.getSatisfactionHistory());
        return entity;
    }

    // 🔹 ESTE ES EL MÉTODO QUE TE FALTA
    public static void copyToEntity(ClientRequest req, Client entity) {
        entity.setFirstName(req.getFirstName());
        entity.setLastName(req.getLastName());
        entity.setEmail(req.getEmail());
        entity.setPhone(req.getPhone());
        entity.setPreferences(req.getPreferences());
        entity.setSatisfactionHistory(req.getSatisfactionHistory());
    }
}
