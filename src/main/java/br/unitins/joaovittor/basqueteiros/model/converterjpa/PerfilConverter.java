package br.unitins.joaovittor.basqueteiros.model.converterjpa;

import br.unitins.joaovittor.basqueteiros.model.tipo_usuario.TipoUsuario;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PerfilConverter implements AttributeConverter<TipoUsuario, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoUsuario tipoUsuario) {
        return (tipoUsuario == null ? null : tipoUsuario.getId());
    }

    @Override
    public TipoUsuario convertToEntityAttribute(Integer id) {
        return TipoUsuario.fromId(id);
    }
}
