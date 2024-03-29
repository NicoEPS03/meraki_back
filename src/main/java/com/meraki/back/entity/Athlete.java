package com.meraki.back.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "athlete")
@ApiModel("Model athlete")
public class Athlete implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(dataType = "Integer", value = "Id of club images", example = "1")
    @Column(name = "AT_ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "AT_IDCLUB", foreignKey = @ForeignKey(name = "FK_ATHLETE_IDCLUB"))
    private Club club;
    @NotNull(message = "Names are obligatory")
    @Size(min = 5, max = 50, message = "The names must be between 5 and 50 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Names of minimum 5 and maximum 50 characters", example = "Jose Tomas", allowableValues = "range[5,50]")
    @Column(name = "AT_NAME", length = 50, nullable = false)
    private String name;
    @NotNull(message = "Last name is obligatory")
    @Size(min = 5, max = 50, message = "The last name must be between 5 and 50 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Last name of minimum 5 and maximum 50 characters", example = "Perez Amalla", allowableValues = "range[5,50]")
    @Column(name = "AT_LASTNAME", length = 50, nullable = false)
    private String lastName;
    @NotNull(message = "EPS are obligatory")
    @Size(min = 5, max = 30, message = "The eps must be between 5 and 50 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "EPS of minimum 5 and maximum 30 characters", example = "Nueva EPS", allowableValues = "range[5,30]")
    @Column(name = "AT_EPS", length = 30, nullable = false)
    private String eps;
    @ManyToOne
    @JoinColumn(name = "AT_BORNIDCITY", foreignKey = @ForeignKey(name = "FK_ATHLETE_BORNIDCITY"))
    private City bornCity;
    @NotNull(message = "Born date are obligatory")
    @ApiModelProperty(required = true, dataType = "LocalDate", value = "2017-01-13", example = "2017-01-13")
    @Column(name = "AT_BORNDATE", nullable = false)
    private LocalDate bornDate;
    @NotNull(message = "Gender is obligatory")
    @Size(max = 1, message = "The gender must be 1 character")
    @ApiModelProperty(required = true, dataType = "String", value = "Gender must be 1 character", example = "H")
    @Column(name = "AT_GENDER", length = 1, nullable = false)
    private String gender;

    @NotNull(message = "Phone is obligatory")
    @Size(min = 5, max = 11, message = "The phone must be between 5 and 11 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Phone of minimum 5 and maximum 11 characters", example = "3124567896", allowableValues = "range[5,11]")
    @Column(name = "AT_PHONE", length = 11, nullable = false)
    private String phone;
    @NotNull(message = "Direction is obligatory")
    @Size(min = 5, max = 50, message = "The direction must be between 5 and 50 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Direction of minimum 5 and maximum 50 characters", example = "Cll 3 #23c21", allowableValues = "range[5,50]")
    @Column(name = "AT_DIRECTION", length = 50, nullable = false)
    private String direction;
    @NotNull(message = "Document is obligatory")
    @Size(min = 5, max = 13, message = "The document must be between 5 and 13 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Phone of minimum 5 and maximum 13 characters", example = "2345673", allowableValues = "range[5,13]")
    @Column(name = "AT_DOCUMENT", length = 13, nullable = false)
    private String document;
    @ManyToOne
    @JoinColumn(name = "AT_IDDOCUMENTTYPE", foreignKey = @ForeignKey(name = "FK_ATHLETE_IDDOCUMENTTYPE"))
    private DocumentType documentType;
    @NotNull(message = "RH is obligatory")
    @Size(min = 2, max = 4, message = "The RH must be between 2 and 4 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "RH of minimum 2 and maximum 4 characters", example = "O+")
    @Column(name = "AT_RH", length = 4, nullable = false)
    private String rh;
    @NotNull(message = "Neighborhood is obligatory")
    @Size(min = 5, max = 50, message = "The neighborhood must be between 5 and 50 characters")
    @ApiModelProperty(required = true, dataType = "String", value = "Neighborhood of minimum 5 and maximum 50 characters", example = "Amarilo", allowableValues = "range[5,50]")
    @Column(name = "AT_NEIDHBOTHOOD", length = 50, nullable = false)
    private String neighborhood;
    @NotNull(message = "Email is obligatory")
    @Email(message = "Incorrect email")
    @ApiModelProperty(required = true, dataType = "String", value = "Email with @ and valid direction", example = "rojovio@hotmail.com")
    @Column(name = "AT_EMAIL", length = 50, nullable = false, unique = true)
    private String email;
    @Size(min = 5, max = 50, message = "The school grade must be between 5 and 50 characters")
    @ApiModelProperty(dataType = "String", value = "School grade of minimum 5 and maximum 50 characters", example = "Septimo", allowableValues = "range[5,50]")
    @Column(name = "AT_SCHOOLGRADE", length = 50)
    private String schoolGrade;
    @Size(min = 5, max = 20, message = "The school grade must be between 5 and 20 characters")
    @ApiModelProperty(dataType = "String", value = "School grade of minimum 5 and maximum 20 characters", example = "Septimo", allowableValues = "range[5,20]")
    @Column(name = "AT_GRADE", length = 20)
    private String grade;
    @Size(min = 5, max = 50, message = "The institution must be between 5 and 50 characters")
    @ApiModelProperty(dataType = "String", value = "Institution of minimum 5 and maximum 50 characters", example = "Colegio Apostolico", allowableValues = "range[5,50]")
    @Column(name = "AT_INSTITUTION", length = 50)
    private String institution;
    @Size(min = 5, max = 30, message = "The institution must be between 5 and 30 characters")
    @ApiModelProperty(dataType = "String", value = "Institution of minimum 5 and maximum 30 characters", example = "Ingeniero", allowableValues = "range[5,30]")
    @Column(name = "AT_PROFESION", length = 30)
    private String profession;
    @Size(min = 5, max = 30, message = "The other study must be between 5 and 30 characters")
    @ApiModelProperty(dataType = "String", value = "Other study of minimum 5 and maximum 30 characters", example = "Especializacioón", allowableValues = "range[5,30]")
    @Column(name = "AT_OTHERSTUDY", length = 30)
    private String otherStudy;
    @ApiModelProperty(dataType = "Boolean", value = "false", example = "false")
    @Column(name = "CB_DISABILITY")
    private Boolean disability;
    @Size(min = 5, max = 100, message = "The disability description must be between 5 and 100 characters")
    @ApiModelProperty(dataType = "String", value = "Disability description of minimum 5 and maximum 100 characters", example = "Ingeniero", allowableValues = "range[5,100]")
    @Column(name = "AT_DISABILITYDESCRIPTION", length = 100)
    private String disabilityDescription;
    @Size(min = 5, max = 20, message = "The disability type must be between 5 and 20 characters")
    @ApiModelProperty(dataType = "String", value = "Disability type of minimum 5 and maximum 20 characters", example = "Física", allowableValues = "range[5,20]")
    @Column(name = "AT_DISABILITYTYPE", length = 20)
    private String disabilityType;
    @Size(min = 5, max = 15, message = "The population type must be between 5 and 15 characters")
    @ApiModelProperty(dataType = "String", value = "Population type of minimum 5 and maximum 15 characters", example = "Victima", allowableValues = "range[5,15]")
    @Column(name = "AT_POPULATIONTYPE", length = 15)
    private String populationType;
    @Size(min = 5, max = 25, message = "The other population type must be between 5 and 25 characters")
    @ApiModelProperty(dataType = "String", value = "Other population type of minimum 5 and maximum 25 characters", allowableValues = "range[5,25]")
    @Column(name = "AT_OTHERPOPULATIONTYPE", length = 25)
    private String otherPopulationType;
    @NotNull(message = "Nationality is obligatory")
    @Size(min = 5, max = 20, message = "The nationality must be between 5 and 20 characters")
    @ApiModelProperty(dataType = "String", value = "Nationality of minimum 5 and maximum 20 characters", example = "Colombiano", allowableValues = "range[5,20]")
    @Column(name = "AT_NATIONALITY", length = 20)
    private String nationality;
    @NotNull(message = "Stratum is obligatory")
    @Size(max = 1, message = "The stratum must be 1 characters")
    @ApiModelProperty(dataType = "String", value = "Stratum of 1 character", example = "2")
    @Column(name = "AT_STRATUM", length = 1)
    private String stratum;
    @NotNull(message = "Sisben is obligatory")
    @Size(max = 1, message = "The sisben must be 1 characters")
    @ApiModelProperty(dataType = "String", value = "Sisben of minimum 1 character", example = "a")
    @Column(name = "AT_SISBEN", length = 1)
    private String sisben;
    @NotNull(message = "Uniform size is obligatory")
    @Size(min = 1, max = 3, message = "The uniform size must be between 1 and 3 characters")
    @ApiModelProperty(dataType = "String", value = "Uniform size of minimum 1 and maximum 3 characters", example = "S", allowableValues = "range[1,3]")
    @Column(name = "AT_UNIFORMSIZE", length = 3)
    private String uniformSize;
    @NotNull(message = "Shoe size is obligatory")
    @Size(min = 1, max = 2, message = "The shoe size must be between 1 and 2 characters")
    @ApiModelProperty(dataType = "String", value = "Shoe size of minimum 1 and maximum 2 characters", example = "34", allowableValues = "range[1,2]")
    @Column(name = "AT_SHOEMSIZE", length = 2)
    private String shoeSize;
    @ApiModelProperty(dataType = "Boolean", value = "false", example = "false")
    @Column(name = "AT_DISEASE")
    private Boolean disease;
    @Size(min = 5, max = 50, message = "The nationality must be between 5 and 50 characters")
    @ApiModelProperty(dataType = "String", value = "Nationality of minimum 5 and maximum 50 characters", example = "Problemas de respiración", allowableValues = "range[5,50]")
    @Column(name = "AT_DISEASEDESCRIPTION", length = 50)
    private String diseaseDescription;
    @Column(name = "AT_STATE", columnDefinition = "boolean default false")
    private Boolean state;

    public Athlete() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public City getBornCity() {
        return bornCity;
    }

    public void setBornCity(City bornCity) {
        this.bornCity = bornCity;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getOtherStudy() {
        return otherStudy;
    }

    public void setOtherStudy(String otherStudy) {
        this.otherStudy = otherStudy;
    }

    public Boolean getDisability() {
        return disability;
    }

    public void setDisability(Boolean disability) {
        this.disability = disability;
    }

    public String getDisabilityDescription() {
        return disabilityDescription;
    }

    public void setDisabilityDescription(String disabilityDescription) {
        this.disabilityDescription = disabilityDescription;
    }

    public String getDisabilityType() {
        return disabilityType;
    }

    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    public String getPopulationType() {
        return populationType;
    }

    public void setPopulationType(String populationType) {
        this.populationType = populationType;
    }

    public String getOtherPopulationType() {
        return otherPopulationType;
    }

    public void setOtherPopulationType(String otherPopulationType) {
        this.otherPopulationType = otherPopulationType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getStratum() {
        return stratum;
    }

    public void setStratum(String stratum) {
        this.stratum = stratum;
    }

    public String getSisben() {
        return sisben;
    }

    public void setSisben(String sisben) {
        this.sisben = sisben;
    }

    public String getUniformSize() {
        return uniformSize;
    }

    public void setUniformSize(String uniformSize) {
        this.uniformSize = uniformSize;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public Boolean getDisease() {
        return disease;
    }

    public void setDisease(Boolean disease) {
        this.disease = disease;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
