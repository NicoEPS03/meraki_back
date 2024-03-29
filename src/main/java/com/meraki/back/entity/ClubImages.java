package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
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
    @Size(min = 30, max = 100, message = "The url must be between 30 and 100 characters")
    @Column(name = "CI_URL", length = 100, nullable = false)
    @ApiModelProperty(dataType = "String", value = "Url image of club", example = "www.club...")
    private String url;
    @Column(name = "CI_BANNER")
    private Boolean banner;
    @Column(name = "CI_LOGO")
    private Boolean logo;
    @Column(name = "CI_OTHER")
    private Boolean other;

    public ClubImages() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getBanner() {
        return banner;
    }

    public void setBanner(Boolean banner) {
        this.banner = banner;
    }

    public Boolean getLogo() {
        return logo;
    }

    public void setLogo(Boolean logo) {
        this.logo = logo;
    }

    public Boolean getOther() {
        return other;
    }

    public void setOther(Boolean other) {
        this.other = other;
    }
}
