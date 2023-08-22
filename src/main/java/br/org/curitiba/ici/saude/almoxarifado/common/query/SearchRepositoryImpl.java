package br.org.curitiba.ici.saude.almoxarifado.common.query;

import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class SearchRepositoryImpl<T> implements SearchRepository<T> {
  @Override
  public Page<T> search(Map<String, Object> parameters, Pageable pageable) {
    return null;
  }
}
