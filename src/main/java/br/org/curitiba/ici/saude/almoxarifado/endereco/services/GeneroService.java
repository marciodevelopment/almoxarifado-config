package br.org.curitiba.ici.saude.almoxarifado.endereco.services;


import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.GeneroEntity;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view.GeneroPesquisaView;
import br.org.curitiba.ici.saude.almoxarifado.endereco.repositories.GeneroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;



                	    	    	                    	    	    	            		    	
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GeneroService {
  private final GeneroRepository generoRepository;

  @Transactional
  public GeneroEntity salvar(GeneroEntity genero) {
    return generoRepository.save(genero);
  }

  @Transactional
  public void deletarPorId(Integer cdGenero) {
    generoRepository.deleteById(cdGenero);
  }

  public GeneroEntity buscarPorIdOuThrow(Integer cdGenero) {
    return generoRepository.findById(cdGenero).orElseThrow(() -> new EntityNotFoundException(
        "Genero não encontrado para o código enviado %s".formatted(cdGenero)));
  }

  public Page<GeneroPesquisaView> pesquisarView(Pageable pageable,
      Map<String, Object> parametros) {
    return generoRepository.search(parametros, pageable);
  }

}

