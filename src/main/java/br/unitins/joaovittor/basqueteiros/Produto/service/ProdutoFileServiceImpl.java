package br.unitins.joaovittor.basqueteiros.Produto.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.joaovittor.basqueteiros.File.service.FileService;
import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;
import br.unitins.joaovittor.basqueteiros.Produto.repository.ProdutoRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoFileServiceImpl implements FileService{

    public final String PATH_USER = System.getProperty("user.home")
                                    + File.separator + "quarkus"
                                    + File.separator + "images"
                                    + File.separator + "produtos" + File.separator;

    @Inject
    public ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public void upload(Long id, String nomeImagem, byte[] imagem) {
        Produto produto = produtoRepository.findById(id);
        try {
            produto.setNomeImagem(salvarImagem(nomeImagem, imagem));
        } catch (IOException e) {
            throw new ValidationException("imagem", e.getMessage());
        }
    }

    @Override
    public File download(String nomeImagem) {
        return new File(PATH_USER + nomeImagem);
    }

    private String salvarImagem(String nomeImagem, byte[] imagem) throws IOException{
        // verificar o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/gif", "image/png", "image/jpeg");
        if (!listMimeType.contains(mimeType)) 
            throw new IOException("Tipo de imagem não suportado.");

        // verificar o tamanho do arquivo - nao permitir maior que 10mb
        if (imagem.length > 1024 * 1024 * 10) {
            throw new IOException("Arquivo muito grande, tamanho máximo 10mb.");
        }

        // criar pasta quando nao existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists()) 
            diretorio.mkdirs();

        // gerar nome do arquivo 
        String nomeArquivo = LocalDateTime.now()
                             .format(DateTimeFormatter.ofPattern("yyyy-MM-dd-'T'-HH'h'-mm'm'-ss's'")) 
                                + "_"
                                + UUID.randomUUID()
                                + "." 
                                + mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String path = PATH_USER + nomeArquivo;

        // salvar o arquivo
        File file = new File(path);
        if (file.exists())
          throw new IOException("Este arquivo ja existe.");

        // criar o arquivo no SO
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        fos.flush();
        fos.close();

        return nomeArquivo;
    }
    
}
