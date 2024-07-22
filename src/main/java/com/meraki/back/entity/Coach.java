package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "coach")
@ApiModel("Model coach")
public class Coach implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of coach", example = "1")
    @Column(name = "CH_ID")
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CH_IDUSER", foreignKey = @ForeignKey(name = "FK_COACH_IDUSER"))
    private User user;
    @ManyToOne
    @JoinColumn(name = "CH_IDCLUB", foreignKey = @ForeignKey(name = "FK_COACH_IDCLUB"))
    private Club club;
    @NotNull(message = "Name is obligatory")
    @Size(min = 5, max = 30, message = "The name must be between 5 and 30 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Name of minimum 5 and maximum 30 characters", example = "Jose", allowableValues = "range[5,30]")
    @Column(name = "CH_NAME", length = 30, nullable = false)
    private String name;
    @NotNull(message = "Last name is obligatory")
    @Size(min = 5, max = 30, message = "The last name must be between 5 and 30 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Last name of minimum 5 and maximum 30 characters", example = "Perez", allowableValues = "range[5,30]")
    @Column(name = "CH_LASTNAME", length = 30, nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "CH_IDDOCUMENTTYPE", foreignKey = @ForeignKey(name = "FK_COACH_IDDOCUMENTTYPE"))
    private DocumentType documentType;

}
