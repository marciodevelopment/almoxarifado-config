
package br.org.curitiba.ici.saude.almoxarifado.endereco.entities;


import java.io.Serializable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoConverter;
import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Where(clause = " idSituacao <> 0 ")
@SQLDelete(sql = "UPDATE DocumentoCpf SET idSituacao = 0 WHERE cdDocumentoCpf = ? ")
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "DOCUMENTOCPF")
public class DocumentoCpfEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Getter
  @Id
  @Column(name = "cdDocumentoCpf", nullable = false)
  @SequenceGenerator(name = "DocumentoCpf", sequenceName = "DocumentoCpf_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DocumentoCpf")
  private Integer cdDocumentoCpf;

  @Getter
  @NotNull
  @Size(min = 1, max = 50, message = "Número Cpf")
  private String nrDocumento;

  @NotNull(message = "Situação")
  @Convert(converter = SituacaoConverter.class)
  private SituacaoType idSituacao;


  @NotNull(message = "Usuario")
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cdUsuario", referencedColumnName = "cdUsuario")
  private UsuarioEntity usuario;

  public DocumentoCpfEntity(String nrDocumento, UsuarioEntity usuario) {
    this.nrDocumento = nrDocumento;
    this.usuario = usuario;
    this.idSituacao = SituacaoType.ATIVO;
  }



}
