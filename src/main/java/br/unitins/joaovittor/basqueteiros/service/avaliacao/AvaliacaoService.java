package br.unitins.joaovittor.basqueteiros.service.avaliacao;

import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.model.avaliacao.Avaliacao;
import java.util.List;

public interface AvaliacaoService {

    Avaliacao criarAvaliacao(AvaliacaoDTO avaliacaoDTO, Long usuarioId);

    Avaliacao buscarAvaliacaoPorId(Long id);

    List<Avaliacao> buscarAvaliacoesPorUsuarioId(Long usuarioId);

    List<Avaliacao> listarTodasAvaliacoes();

    void excluirAvaliacao(Long id);
}
