package com.tarefas.service;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.tarefas.controller.dto.TarefaDTO;
import com.tarefas.domain.Tarefa;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
	TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);
	
    @BeanMapping(nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
    void DTOToTarefa(TarefaDTO dto, @MappingTarget Tarefa entity);
}
