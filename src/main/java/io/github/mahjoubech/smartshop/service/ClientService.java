package io.github.mahjoubech.smartshop.service;

import io.github.mahjoubech.smartshop.dto.request.ClientRequestDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ClientOrderStatsResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.basic.ClientResponseBasicDTO;
import io.github.mahjoubech.smartshop.dto.response.detail.ClientResponseDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    ClientResponseDetailDTO createClient(ClientRequestDTO clientRequestDTO);
    Page<ClientResponseDetailDTO> getAllClients(Pageable pageable);
    ClientResponseDetailDTO getClientDetailById(String id);
    ClientResponseBasicDTO getClientBasicByid(String id);
    ClientResponseDetailDTO updateClient(String id, ClientRequestDTO clientRequestDTO);
    void deleteClient(String id);
    ClientOrderStatsResponseBasicDTO getClientOrderStats(String clientId);
}
