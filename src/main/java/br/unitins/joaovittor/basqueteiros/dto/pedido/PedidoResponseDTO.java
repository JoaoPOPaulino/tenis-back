package br.unitins.joaovittor.basqueteiros.dto.pedido;

import java.time.format.DateTimeFormatter;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.item.ItemResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;

public record PedidoResponseDTO(
        Long id,
        String data,
        UsuarioResponseDTO usuario,
        Float preco,
        List<ItemResponseDTO> itens,
        EnderecoDTO endereco,
        CartaoResponseDTO cartao) {

    public static PedidoResponseDTO valueOf(Pedido pedido) {
        String formattedData = pedido.getData() != null
                ? pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
                : null;

        return new PedidoResponseDTO(
                pedido.getId(),
                formattedData,
                UsuarioResponseDTO.valueOf(pedido.getUsuario()),
                pedido.getTotal(),
                ItemResponseDTO.valueOf(pedido.getItens()),
                EnderecoDTO.valueOf(pedido.getEndereco()),
                CartaoResponseDTO.valueOf(pedido.getCartao()));
    }

}
