package br.unitins.joaovittor.basqueteiros.Pedido.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cliente.repository.ClienteRepository;
import br.unitins.joaovittor.basqueteiros.ItemPedido.dto.ItemPedidoDTO;
import br.unitins.joaovittor.basqueteiros.ItemPedido.model.ItemPedido;
import br.unitins.joaovittor.basqueteiros.Pedido.dto.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.Pedido.dto.PedidoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Pedido.model.Pedido;
import br.unitins.joaovittor.basqueteiros.Pedido.repository.PedidoRepository;
import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;
import br.unitins.joaovittor.basqueteiros.Produto.repository.ProdutoRepository;
import br.unitins.joaovittor.basqueteiros.Produto.service.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService{

    @Inject
    public PedidoRepository repository;

    @Inject
    public ProdutoRepository produtoRepository;

    @Inject
    public ProdutoService produtoService;

    @Inject
    public ClienteRepository clienteRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(@Valid PedidoDTO dto) {
        
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setCliente(clienteRepository.findById(dto.idCliente()));
        pedido.setValorTotal(0d); // vai atualizar no "for"

        List<ItemPedido> itens = new ArrayList<ItemPedido>();

        for(ItemPedidoDTO itemDTO : dto.itens()){
            
            Produto produto = produtoRepository.findById(itemDTO.idProduto());

            if(produto.getQuantidade() >= itemDTO.quantidade()){
                ItemPedido itemUnidade = new ItemPedido();

                itemUnidade.setDesconto(itemDTO.desconto());
                itemUnidade.setQuantidade(itemDTO.quantidade());
                itemUnidade.setProduto(produtoRepository.findById(itemDTO.idProduto()));

                // calcular valor total sem desconto
                itemUnidade.setValor(produto.getPrecoVenda());
                // valor com desconto, mas nao armazenado:
                // produto.getPrecoVenda() - itemDTO.desconto();

                itens.add(itemUnidade);
                
                // valor total do pedido
                pedido.setValorTotal(pedido.getValorTotal() + itemUnidade.getValor());

                // estoque do produto
                ProdutoDTO produtoDTO = new ProdutoDTO(
                    produto.getNome(), 
                    produto.getDescricao(), 
                    (produto.getQuantidade() - itemDTO.quantidade()), 
                    produto.getPrecoCompra(), 
                    produto.getPrecoVenda(), 
                    produto.getFornecedor().getId(), 
                    produto.getMarca().getId());

                // depois mudar para um PATH, e nao PUT(update) !!!!!!!!    
                produtoService.update(itemDTO.idProduto(), produtoDTO);

                // SALDO DO CLIENTE = CUIDAR DO TIPO DE PAGAMENTO ETC = IMPLEMENTAR

            } else{
                // quantidade insuficiente daquele item
            }
            
        }

        pedido.setItens(itens);

        repository.persist(pedido);
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = repository.findById(id);
        if (pedido != null)
            return PedidoResponseDTO.valueOf(pedido);
        return null;
    }

    @Override
    public List<PedidoResponseDTO> findAll() {
        return repository
        .listAll()
        .stream()
        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<PedidoResponseDTO> findByCliente(Long idCliente) {
        List<PedidoResponseDTO> lista = repository.findByCliente(idCliente)
                                        .stream()
                                        .map(e -> PedidoResponseDTO.valueOf(e)).toList();
        if(lista != null)
            return lista;
        return null;                                        
    }
    
}
