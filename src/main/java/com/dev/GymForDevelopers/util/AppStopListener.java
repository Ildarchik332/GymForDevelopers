package com.dev.GymForDevelopers.util;

import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.repositories.GdAnswerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppStopListener implements ApplicationListener<ContextClosedEvent> {
    private final GdAnswerRepository gdAnswerRepository;

    @Autowired
    public AppStopListener(GdAnswerRepository gdAnswerRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
    }

    @Override
    public void onApplicationEvent(@Nullable ContextClosedEvent event) {
        try {
            AppStartedListener.mapLikes.forEach((id, likes) -> gdAnswerRepository.saveLikes(id, likes));
            log.info("Кэш ответов успешно сохранен");
        } catch (Exception e) {
            log.error("Ошибка при сохранении кэша ответов");
            throw new GdRuntimeException("Ошибка при сохранении кэша ответов", "answer.cache.save.failed");
        }
    }
}
