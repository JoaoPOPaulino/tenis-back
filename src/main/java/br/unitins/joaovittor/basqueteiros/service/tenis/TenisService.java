package br.unitins.joaovittor.basqueteiros.service.tenis;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;

import java.util.List;

public interface TenisService {

    Tenis criarTenis(TenisDTO tenisDTO);

    Tenis buscarTenisPorId(Long id);

    List<Tenis> buscarTenisPorNome(String nome);

    List<Tenis> buscarTenisPorMarca(String marcaNome);

    List<Tenis> buscarTenisPorPrecoRange(float minPreco, float maxPreco);

    List<TenisResponseDTO> listarTodosTenis();

    void excluirTenis(Long id);
}
