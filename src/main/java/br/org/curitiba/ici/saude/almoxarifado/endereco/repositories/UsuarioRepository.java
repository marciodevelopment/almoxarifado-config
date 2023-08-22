package br.org.curitiba.ici.saude.almoxarifado.endereco.repositories;

import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.org.curitiba.ici.saude.almoxarifado.common.query.QuerySearch;
import br.org.curitiba.ici.saude.almoxarifado.common.query.SearchRepository;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.UsuarioEntity;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view.InformacaoUsuarioDto;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view.UsuarioPesquisaView;



public interface UsuarioRepository
    extends JpaRepository<UsuarioEntity, Integer>, SearchRepository<UsuarioPesquisaView> {
  @QuerySearch(columns = "e.cdUsuario, e.nmUsuario, e.nmMae, e.idSexo, e.dsEmail", query = """
      from UsuarioEntity e
      where  1 = 1
      and e.cdUsuario = :cdUsuario
      and e.nmUsuario = :nmUsuario
      and e.nmMae = :nmMae
      and e.idSexo = :idSexo
          """, viewResult = UsuarioPesquisaView.class)
  @Override
  public Page<UsuarioPesquisaView> search(Map<String, Object> parameters, Pageable pageable);

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public boolean existsByDsEmailAndCdUsuarioNot(String dsEmail, Integer cdUsuario);

  @Query(value = "select * from get_user_info() u", nativeQuery = true)
  public List<InformacaoUsuarioDto> getUserInfo();
}
