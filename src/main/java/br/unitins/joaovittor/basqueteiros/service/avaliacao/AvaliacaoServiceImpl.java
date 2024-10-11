package br.unitins.joaovittor.basqueteiros.service.avaliacao;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.model.avaliacao.Avaliacao;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.AvaliacaoRepository;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository, UsuarioRepository usuarioRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Avaliacao criarAvaliacao(AvaliacaoDTO avaliacaoDTO, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setTitulo(avaliacaoDTO.titulo());
        avaliacao.setTexto(avaliacaoDTO.texto());
        avaliacao.setNota(avaliacaoDTO.nota());
        avaliacao.setDataCriacao(LocalDateTime.now());
        avaliacao.setUsuario(usuario);
        avaliacaoRepository.persist(avaliacao);
        return avaliacao;
    }

    @Override
    public Avaliacao buscarAvaliacaoPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    @Override
    public List<Avaliacao> buscarAvaliacoesPorUsuarioId(Long usuarioId) {
        return avaliacaoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Avaliacao> listarTodasAvaliacoes() {
        return avaliacaoRepository.findAllAvaliacoes();
    }

    @Override
    @Transactional
    public void excluirAvaliacao(Long id) {
        Avaliacao avaliacao = buscarAvaliacaoPorId(id);
        if (avaliacao != null) {
            avaliacaoRepository.delete(avaliacao);
        }
    }
}
