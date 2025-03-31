package ru.kustarevvv.web.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.spring.hibernate.entity.OpenQuestionCardEntity;
import ru.kustarevvv.web.api.dto.OpenQuestionCardCreateDto;
import ru.kustarevvv.web.api.dto.OpenQuestionCardDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionDtoMapper {
    OpenQuestionCard mapToModel(OpenQuestionCardDto entity);
    OpenQuestionCardDto mapToDto(OpenQuestionCard question);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCardDto> entities);
    List<OpenQuestionCardDto> mapToDto(List<OpenQuestionCard> questions);
    OpenQuestionCard mapToModel(OpenQuestionCardCreateDto entity);
}
