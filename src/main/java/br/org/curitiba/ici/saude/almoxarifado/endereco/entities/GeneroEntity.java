
package br.org.curitiba.ici.saude.almoxarifado.endereco.entities;

                                                                                                                                      
import java.io.Serializable;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoConverter;
import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
@Where(clause = " idSituacao <> 0 ")
@SQLDelete(sql = "UPDATE Genero SET idSituacao = 0 WHERE cdGenero = ? ")
@Accessors(chain = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="Genero" )
public class GeneroEntity implements Serializable {
  private static final long serialVersionUID = 1L;
   @Getter
     @Id
  @Column(name="cdGenero", nullable=false)
         @SequenceGenerator(name = "Genero", sequenceName = "Genero_sq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Genero")
 private Integer    cdGenero ;

    
             @Getter
                     @Size( min = 5, max = 255 , message="Genero")
        private String     dsGenero;
          
             @Getter
          @Setter 
                 @Size( min = 5, max = 255 , message="Genero")
        private String     sgGenero;
          
    @NotNull(message = "Situação")
  @Convert(converter = SituacaoConverter.class)
  private SituacaoType idSituacao;
  

  public GeneroEntity (  String dsGenero,   String sgGenero  ){
this.dsGenero = dsGenero;
this.sgGenero = sgGenero;

this.idSituacao = SituacaoType.ATIVO;  }

  public GeneroEntity atualizar(  String dsGenero,   String sgGenero  ) {
this.dsGenero = dsGenero;
this.sgGenero = sgGenero;
   return this;
  }

  

}