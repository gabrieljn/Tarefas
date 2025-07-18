package com.tarefas.service;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.tarefas.domain.Tarefa;
import com.tarefas.dto.TarefaDto;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
	TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);
	
    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void DTOToTarefa(TarefaDto dto, @MappingTarget Tarefa entity);
}