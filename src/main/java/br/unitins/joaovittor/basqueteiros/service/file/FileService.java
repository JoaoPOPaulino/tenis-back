package br.unitins.joaovittor.basqueteiros.service.file;

import java.io.File;
import java.io.IOException;

public interface FileService {

    void salvar(Long id, String nomeArquivo, byte[] arquivo) throws IOException;

    File obter(String nomeArquivo);

}
