package br.unitins.joaovittor.basqueteiros.service.marca;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.repository.MarcaRepository;
import jakarta.transaction.Transactional;

public class MarcaServiceImpl implements MarcaService {

    private final MarcaRepository marcaRepository;

    // Injeção de dependência do repositório
    public MarcaServiceImpl(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    @Override
    public MarcaResponseDTO createMarca(MarcaDTO marcaDTO) {
        Marca marca = new Marca();
        marca.setNome(marcaDTO.nome());
        marcaRepository.persist(marca);

        return new MarcaResponseDTO(marca.getId(), marca.getNome());
    }

    @Override
    public MarcaResponseDTO getMarcaById(Long id) {
        Marca marca = marcaRepository.findById(id);
        if (marca != null) {
            return new MarcaResponseDTO(marca.getId(), marca.getNome());
        } else {
            // Lançar uma exceção ou retornar um erro na resposta
            return new MarcaResponseDTO(null, "Marca não encontrada.");
        }
    }

    @Override
    public MarcaResponseDTO getMarcaByNome(String nome) {
        List<Marca> marcas = marcaRepository.findByNome(nome);
        if (marcas != null && !marcas.isEmpty()) {
            // Pegando a primeira marca encontrada, ou você pode modificar para retornar todas
            Marca marca = marcas.get(0);  // Aqui retornamos apenas a primeira marca
            return new MarcaResponseDTO(marca.getId(), marca.getNome());
        } else {
            // Lançar uma exceção ou retornar um erro na resposta
            return new MarcaResponseDTO(null, "Marca não encontrada.");
        }
    }

}
