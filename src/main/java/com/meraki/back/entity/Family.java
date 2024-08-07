package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "family")
@ApiModel("Model family")
@NamedQueries({
        @NamedQuery(name = "Family.searchDocument", query = "SELECT COUNT(f) FROM Family f WHERE NOT f.id = :id AND f.document = :document"),
})
public class Family implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of club images", example = "1")
    @Column(name = "FA_ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "FA_IDATHLETE", foreignKey = @ForeignKey(name = "FK_FAMILY_IDATHLETE"))
    private Athlete athlete;
    @NotNull(message = "Names are obligatory")
    @Size(min = 5, max = 70, message = "The names must be between 5 and 70 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Names of minimum 5 and maximum 70 characters", example = "Jose Tomas Perez Rojjas", allowableValues = "range[5,70]")
    @Column(name = "FA_NAME", length = 70, nullable = false)
    private String name;
    @NotNull(message = "Document is obligatory")
    @Size(min = 5, max = 13, message = "The document must be between 5 and 13 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Phone of minimum 5 and maximum 13 characters", example = "2345673", allowableValues = "range[5,13]")
    @Column(name = "FA_DOCUMENT", length = 13, nullable = false)
    private String document;
    @ManyToOne
    @JoinColumn(name = "FA_IDDOCUMENTTYPE", foreignKey = @ForeignKey(name = "FK_FAMILY_IDDOCUMENTTYPE"))
    private DocumentType documentType;
    @NotNull(message = "Phone is obligatory")
    @Size(min = 5, max = 11, message = "The phone must be between 5 and 11 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Phone of minimum 5 and maximum 11 characters", example = "3124567896", allowableValues = "range[5,11]")
    @Column(name = "FA_PHONE", length = 11, nullable = false)
    private String phone;
    @NotNull(message = "Email is obligatory")
    @Email(message = "Incorrect email")
    @ApiModelProperty(required = true, dataType = "String", value = "Email with @ and valid direction", example = "rojovio@hotmail.com")
    @Column(name = "FA_EMAIL", length = 50, nullable = false, unique = true)
    private String email;
    @NotNull(message = "Company is obligatory")
    @Size(min = 5, max = 30, message = "The company must be between 5 and 30 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Company of minimum 5 and maximum 30 characters", example = "Sodexo", allowableValues = "range[5,30]")
    @Column(name = "FA_COMPANY", length = 30, nullable = false)
    private String company;
    @NotNull(message = "Occupation is obligatory")
    @Size(min = 5, max = 25, message = "The occupation must be between 5 and 25 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Occupation of minimum 5 and maximum 25 characters", example = "Operario", allowableValues = "range[5,25]")
    @Column(name = "FA_OCCUPATION", length = 25, nullable = false)
    private String occupation;
    @NotNull(message = "Relationship is obligatory")
    @Size(min = 5, max = 15, message = "The relationship must be between 5 and 15 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Relationship of minimum 5 and maximum 15 characters", example = "Padre", allowableValues = "range[5,15]")
    @Column(name = "FA_RELATIONSHIP", length = 15, nullable = false)
    private String relationship;
    @Column(name = "FA_STATE", columnDefinition = "boolean default true")
    private Boolean state;

}
