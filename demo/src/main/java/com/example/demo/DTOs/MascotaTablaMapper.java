package com.example.demo.DTOs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entidades.Mascota;

@Mapper
public interface MascotaTablaMapper {
    MascotaTablaMapper INSTANCE = Mappers.getMapper(MascotaTablaMapper.class);

    MascotaTablaDto convert(Mascota mascota);
}
