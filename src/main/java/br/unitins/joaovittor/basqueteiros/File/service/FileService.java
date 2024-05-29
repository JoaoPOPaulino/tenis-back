package br.unitins.joaovittor.basqueteiros.File.service;

import java.io.File;

public interface FileService {
    
    void upload(Long id, String nomeImage, byte[] imagem);
    File download(String nomeImagem);
}
