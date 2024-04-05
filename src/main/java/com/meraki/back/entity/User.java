package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "user")
@ApiModel("Model user")
@NamedQueries({
        @NamedQuery(name = "User.searchDocument", query = "SELECT COUNT(m) FROM User m WHERE NOT m.id = :id AND m.document = :document"),
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of user", example = "1")
    @Column(name = "USR_ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "USR_IDROL", foreignKey = @ForeignKey(name = "FK_USER_IDROL"))
    private Rol rol;
    @NotNull(message = "Document is obligatory")
    @Size(min = 5, max = 15, message = "The document must be between 5 and 15 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Description of minimum 5 and maximum 15 characters", example = "12312321", allowableValues = "range[5,15]")
    @Column(name = "USR_DOCUMENT", length = 15, nullable = false, unique = true)
    private String document;
    @NotNull(message = "Password is obligatory")
    @Size(min = 8, max = 30, message = "The password must be between 8 and 30 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Description of minimum 8 and maximum 30 characters", example = "contraseña", allowableValues = "range[8,30]")
    @Column(name = "USR_PASSWORD", length = 30, nullable = false)
    private String password;
    @Column(name = "USR_STATE", columnDefinition = "boolean default 1")
    private Boolean state;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
