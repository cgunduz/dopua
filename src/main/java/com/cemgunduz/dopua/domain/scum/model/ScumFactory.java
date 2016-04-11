package com.cemgunduz.dopua.domain.scum.model;

import com.cemgunduz.dopua.domain.scum.persistence.ScumDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cgunduz on 3/29/2016.
 */
public class ScumFactory {

    public static Scum fromDto(ScumDto scumDto)
    {
        return new Scum(scumDto.getId(),scumDto.getUsername(), scumDto.getPassword(), scumDto.getContainer());
    }

    public static List<Scum> fromDto(List<ScumDto> scumDtoList)
    {
        List<Scum> result = new ArrayList<>();
        for(ScumDto scumDto : scumDtoList)
        {
            result.add(fromDto(scumDto));
        }

        return result;
    }

    public static ScumDto toDto(Scum scum)
    {
        return new ScumDto(scum.getId(),scum.getUsername(), scum.getPassword(), scum.getContainer());
    }
}
