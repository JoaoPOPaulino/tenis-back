package br.unitins.joaovittor.basqueteiros.service.hash;

public interface HashService {
    // Método existente para gerar hash
    String getHashSenha(String senha);
    
    // Novo método para verificar senha
    boolean verificarSenha(String senhaOriginal, String senhaHashArmazenada);
}