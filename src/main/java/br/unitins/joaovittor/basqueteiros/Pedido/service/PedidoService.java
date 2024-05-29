package br.unitins.joaovittor.basqueteiros.Pedido.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Pedido.dto.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.Pedido.dto.PedidoResponseDTO;
import jakarta.validation.Valid;

public interface PedidoService {

    public PedidoResponseDTO create(@Valid PedidoDTO dto);
    public PedidoResponseDTO findById(Long id);
    public List<PedidoResponseDTO> findAll();
    public List<PedidoResponseDTO> findByCliente(Long idCliente);

}
