package br.unitins.joaovittor.basqueteiros.service.hash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HashServiceImpl implements HashService {

    private String salt = "#blahxyz22";

    private Integer iterationCount = 405;

    private Integer keyLength = 512;

    @Override
    public String getHashSenha(String senha) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(senha.toCharArray(),
                            salt.getBytes(), iterationCount, keyLength))
                    .getEncoded();

            return Base64.getEncoder().encodeToString(result);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao criar um hash");
        }
    }

    @Override
    public boolean verificarSenha(String senhaOriginal, String senhaHashArmazenada) {
        // Gera o hash da senha original usando os mesmos parâmetros
        String novoHash = getHashSenha(senhaOriginal);
        
        // Compara o novo hash gerado com o hash armazenado
        return novoHash.equals(senhaHashArmazenada);
    }

    public static void main(String[] args) {
        HashService service = new HashServiceImpl();
        
        // Demonstração da verificação de senha
        String senha = "123";
        String hash = service.getHashSenha(senha);
        
        System.out.println("Senha original: " + senha);
        System.out.println("Hash gerado: " + hash);
        System.out.println("Verificação de senha correta: " + service.verificarSenha(senha, hash));
        System.out.println("Verificação de senha incorreta: " + service.verificarSenha("senhaErrada", hash));
    }
}