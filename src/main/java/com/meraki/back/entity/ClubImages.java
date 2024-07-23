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
@Table(name = "club_images")
@ApiModel("Model club images")
public class ClubImages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of club images", example = "1")
    @Column(name = "CI_ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "CI_IDCLUB", foreignKey = @ForeignKey(name = "FK_CLUBIMAGE_IDCLUB"))
    private Club club;
    @NotNull(message = "Url is obligatory")
    @Size(min = 30, max = 300, message = "The url must be between 30 and 300 characters")
    @Column(name = "CI_URL", length = 300, nullable = false)
    @ApiModelProperty(dataType = "String", value = "Url image of club", example = "www.club...")
    private String url;
    @Column(name = "CI_BANNER")
    private Boolean banner;
    @Column(name = "CI_LOGO")
    private Boolean logo;
    @Column(name = "CI_OTHER")
    private Boolean other;
    @Column(name = "CI_STATE", columnDefinition = "boolean default true")
    private Boolean state;

}
