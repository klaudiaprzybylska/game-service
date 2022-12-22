package com.example.demo;

import com.example.demo.dto.SummaryDto;
import com.example.demo.game.Game;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationMapper {

    private final ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void init() {
        TypeMap<Game, SummaryDto> propertyMapper = mapper.createTypeMap(Game.class, SummaryDto.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getPlayer().getBalance(), SummaryDto::setBalance)
        );
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }
}
