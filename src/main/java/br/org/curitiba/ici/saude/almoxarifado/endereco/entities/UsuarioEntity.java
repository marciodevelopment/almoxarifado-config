
package br.org.curitiba.ici.saude.almoxarifado.endereco.entities;



import java.io.Serializable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoConverter;
import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoType;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoConverter;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Where(clause = " idSituacao <> 0 ")
@SQLDelete(sql = "UPDATE Usuario SET idSituacao = 0 WHERE cdUsuario = ? ")
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "USUARIO")
public class UsuarioEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  @Getter
  @Id
  @Column(name = "cdUsuario", nullable = false)
  @SequenceGenerator(name = "Usuario", sequenceName = "Usuario_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario")
  private Integer cdUsuario;


  @Getter
  @NotNull
  @Size(min = 5, max = 255, message = "Nome")
  private String nmUsuario;

  @Getter
  @NotNull
  @Size(min = 5, max = 255, message = "Nome Mãe")
  private String nmMae;

  @Getter
  @Setter
  private String nmPai;

  @Getter
  @Email(message = "Enail")
  private String dsEmail;

  @Getter
  @NotNull(message = "Sexo")
  @Convert(converter = SexoConverter.class)
  private SexoType idSexo;

  @NotNull(message = "Situação")
  @Convert(converter = SituacaoConverter.class)
  private SituacaoType idSituacao;

  @Getter
  @NotNull(message = "Documento")
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
  private DocumentoCpfEntity documento;
  @Getter
  @NotNull(message = "Gernero")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cdGenero", referencedColumnName = "cdGenero")
  private GeneroEntity genero;

  public UsuarioEntity(String nmUsuario, String nmMae, SexoType idSexo, String nrDocumento,
      String dsEmail, GeneroEntity genero) {
    this.nmUsuario = nmUsuario;
    this.nmMae = nmMae;
    this.idSexo = idSexo;
    this.documento = new DocumentoCpfEntity(nrDocumento, this);
    this.idSituacao = SituacaoType.ATIVO;
    this.genero = genero;
    this.dsEmail = dsEmail;
  }

  public UsuarioEntity atualizar(String nmUsuario, String nmMae, SexoType idSexo, String dsEmail) {
    this.nmUsuario = nmUsuario;
    this.nmMae = nmMae;
    this.idSexo = idSexo;
    this.dsEmail = dsEmail;
    return this;
  }

}
