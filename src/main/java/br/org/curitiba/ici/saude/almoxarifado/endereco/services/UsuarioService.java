package br.org.curitiba.ici.saude.almoxarifado.endereco.services;


import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.UsuarioEntity;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view.UsuarioPesquisaView;
import br.org.curitiba.ici.saude.almoxarifado.endereco.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UsuarioService {
  private final UsuarioRepository usuarioRepository;


  public UsuarioEntity salvar(UsuarioEntity usuario) {
    return usuarioRepository.save(usuario);
  }


  public UsuarioEntity atualizar(UsuarioEntity usuario) {
    if (usuarioRepository.existsByDsEmailAndCdUsuarioNot(usuario.getDsEmail(),
        usuario.getCdUsuario())) {
      throw new EntityNotFoundException("Entidade não encontrada");
    }
    return usuarioRepository.save(usuario);
  }


  public void deletarPorId(Integer cdUsuario) {
    usuarioRepository.deleteById(cdUsuario);
  }

  public UsuarioEntity buscarPorIdOuThrow(Integer cdUsuario) {
    return usuarioRepository.findById(cdUsuario).orElseThrow(() -> new EntityNotFoundException(
        "Usuario não encontrado para o código enviado %s".formatted(cdUsuario)));
  }

  public Page<UsuarioPesquisaView> pesquisarView(Pageable pageable,
      Map<String, Object> parametros) {
    return usuarioRepository.search(parametros, pageable);
  }

}

