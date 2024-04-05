package com.meraki.back.service;

import com.meraki.back.entity.ClubImages;

import java.util.List;

public interface IClubImagesService extends ICrud<ClubImages, Integer> {
    List<ClubImages> retonarPorIdClub(int idClub);
}
