package ru.kustarevvv.spring.hibernate.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kustarevvv.domain.model.OpenQuestionCard;
import ru.kustarevvv.spring.hibernate.entity.OpenQuestionCardEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    OpenQuestionCard mapToModel(OpenQuestionCardEntity entity);
    OpenQuestionCardEntity mapToEntity(OpenQuestionCard task);
    List<OpenQuestionCard> mapToModel(List<OpenQuestionCardEntity> entities);
    List<OpenQuestionCardEntity> mapToEntity(List<OpenQuestionCard> tasks);
}
