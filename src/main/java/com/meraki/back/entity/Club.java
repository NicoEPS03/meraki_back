package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "club")
@ApiModel("Model club")
public class Club implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of club", example = "1")
    @Column(name = "CB_ID")
    private Integer id;

    @NotNull(message = "Name is obligatory")
    @Size(min = 5, max = 30, message = "The name must be between 5 and 30 characters")
    @Column(name = "CB_NAME", length = 300, nullable = false)
    @ApiModelProperty(dataType = "String", value = "Name of club", example = "Balones del...")
    private String name;
    @NotNull(message = "Description is obligatory")
    @Size(min = 30, max = 300, message = "The description must be between 30 and 300 characters")
    @Column(name = "CB_DESCRIPTION", length = 300, nullable = false)
    @ApiModelProperty(dataType = "String", value = "Description of club", example = "Un club que pertenece...")
    private String description;
    @ManyToOne
    @JoinColumn(name = "CB_IDSPORT", foreignKey = @ForeignKey(name = "FK_CLUB_IDSPORT"))
    private Sport sport;
    @ManyToOne
    @JoinColumn(name = "CB_IDCITY", foreignKey = @ForeignKey(name = "FK_CLUB_IDCITY"))
    private City city;
    @Size(min = 20, max = 50, message = "The instagram link must be between 20 and 50 characters")
    @Column(name = "CB_INSTAGRAM_LINK", length = 50)
    @ApiModelProperty(dataType = "String", value = "Instagram link of club", example = "www.instagram.com")
    private String instragramLink;
    @Size(min = 20, max = 50, message = "The facebook link must be between 20 and 50 characters")
    @Column(name = "CB_FACEBOOK_LINK", length = 50)
    @ApiModelProperty(dataType = "String", value = "Facebook link of club", example = "www.facebook.com")
    private String facebookLink;
    @Size(min = 20, max = 50, message = "The twitter link must be between 20 and 50 characters")
    @Column(name = "CB_TWITTER_LINK", length = 50)
    @ApiModelProperty(dataType = "String", value = "Twitter link of club", example = "www.twitter.com")
    private String twitterLink;
    @Size(min = 20, max = 50, message = "The tiktok link must be between 20 and 50 characters")
    @Column(name = "CB_TIKTOK_LINK", length = 50)
    @ApiModelProperty(dataType = "String", value = "Tiktok link of club", example = "www.tiktok.com")
    private String tiktokLink;
    @Size(min = 20, max = 50, message = "The youtube link must be between 20 and 50 characters")
    @Column(name = "CB_YOUTUBE_LINK", length = 50)
    @ApiModelProperty(dataType = "String", value = "Youtube link of club", example = "www.youtube.com")
    private String youtubeLink;
    @Size(min = 20, max = 50, message = "The whatsapp link must be between 20 and 50 characters")
    @Column(name = "CB_WHATSAPP_LINK", length = 50)
    @ApiModelProperty(dataType = "String", value = "Whatsapp link of club", example = "www.whatsapp.com")
    private String whatsappLink;
    @Column(name = "CB_STATE", columnDefinition = "boolean default false")
    private Boolean state;

    public Club() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getInstragramLink() {
        return instragramLink;
    }

    public void setInstragramLink(String instragramLink) {
        this.instragramLink = instragramLink;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    public String getTiktokLink() {
        return tiktokLink;
    }

    public void setTiktokLink(String tiktokLink) {
        this.tiktokLink = tiktokLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getWhatsappLink() {
        return whatsappLink;
    }

    public void setWhatsappLink(String whatsappLink) {
        this.whatsappLink = whatsappLink;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
