package com.sliit.tomatogameapi.entity;

import com.sliit.tomatogameapi.dto.SuperDto;
import org.modelmapper.ModelMapper;

public abstract class SuperEntity <D extends SuperDto>{
    public abstract D toDto(ModelMapper modelMapper);
}
