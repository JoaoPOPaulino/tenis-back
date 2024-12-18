package br.unitins.joaovittor.basqueteiros.service.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class FileServiceImpl implements FileService {

    @Inject
    TenisRepository tenisRepository;

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "quarkus"
            + File.separator + "images"
            + File.separator + "tenis" + File.separator;

    @Override
    @Transactional
    public void salvar(Long id, String nomeArquivo, byte[] imagem) throws IOException {
        Tenis tenis = tenisRepository.findById(id);
        try {
            String novoNomeImagem = salvarImagem(imagem, nomeArquivo);
            tenis.setNomeImagem(novoNomeImagem);
        } catch (IOException e) {
            //throw new ValidationException("imagem", e.getMessage());
            throw e;
        }
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {
        // verificar o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/gif", "image/png", "image/jpeg");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportado.");
        }

        // verificar o tamanho do arquivo - nao permitir maior que 10mb
        if (imagem.length > 1024 * 1024 * 10) {
            throw new IOException("Arquivo muito grande, tamanho máximo 10mb.");
        }

        // criar pasta quando nao existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

        // gerar nome do arquivo 
        String nomeArquivo = UUID.randomUUID()
                + "."
                + mimeType.substring(mimeType.lastIndexOf("/") + 1);
        String path = PATH_USER + nomeArquivo;

        // salvar o arquivo
        File file = new File(path);
        if (file.exists()) {
            throw new IOException("Este arquivo ja existe. Programador, tu deve melhorar esse codigo");
        }

        // criar o arquivo no SO
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        fos.flush();
        fos.close();

        return nomeArquivo;
    }

    @Override
    public File obter(String nomeImagem) {
        File file = new File(PATH_USER + nomeImagem);
        return file;
    }

}
