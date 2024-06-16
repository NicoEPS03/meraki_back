package com.meraki.back.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class ClubImagesDto implements Serializable {

    private Integer id;
    private String url;
    private Boolean banner;
    private Boolean logo;
    private Boolean other;
    private Boolean state;

}
