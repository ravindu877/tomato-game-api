package com.sliit.tomatogameapi.dto;

import com.sliit.tomatogameapi.entity.SuperEntity;
import org.modelmapper.ModelMapper;

public abstract class SuperDto <E extends SuperEntity> {
    public abstract E toEntity(ModelMapper modelMapper);
}
