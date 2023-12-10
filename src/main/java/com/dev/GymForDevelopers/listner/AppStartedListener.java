package com.dev.GymForDevelopers.listner;

import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.repositories.GdAnswerRepository;
import com.dev.GymForDevelopers.repositories.GdNoteRepository;
import com.dev.GymForDevelopers.util.ValueLikes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class AppStartedListener implements ApplicationListener<ApplicationStartedEvent> {
    public static Map<Long, ValueLikes> mapAnswerLikes = new ConcurrentHashMap<>();
    public static Map<Long, ValueLikes> mapNoteLikes = new ConcurrentHashMap<>();
    public static Map<Long, ValueLikes> mapNoteDislikes = new ConcurrentHashMap<>();


    private final GdAnswerRepository gdAnswerRepository;
    private final GdNoteRepository gdNoteRepository;


    @Autowired
    public AppStartedListener(GdAnswerRepository gdAnswerRepository, GdNoteRepository gdNoteRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
        this.gdNoteRepository = gdNoteRepository;
    }


    @Override
    public void onApplicationEvent(@Nullable ApplicationStartedEvent event) {

        try {
            List<Long[]> list = gdAnswerRepository.loadLikes();
            for (Long[] x : list) {
                mapAnswerLikes.put(x[0], new ValueLikes(x[1], x[1]));
            }
            log.info("Кэш ответов загружен успешно");
        } catch (Exception e) {
            log.error("Ошибка при загрузке кэша ответов: {}", e.getMessage());
            throw new GdRuntimeException("Ошибка при загрузке кэша ответов", "answer.cache.load.failed");
        }

        try {
            List<Long[]> noteList = gdNoteRepository.loadNoteLikes();
            for (Long[] a : noteList) {
                mapNoteLikes.put(a[0], new ValueLikes(a[1], a[1]));
            }

            List<Long[]> dislikeList = gdNoteRepository.loadNoteDislikes();
            for (Long[] q : dislikeList) {
                mapNoteDislikes.put(q[0], new ValueLikes(q[1], q[1]));
            }

            log.info("Кэш заметок загружен успешно");
        } catch (Exception e) {
            log.error("Ошибка при загрузке кэша заметок: {}", e.getMessage());
            throw new GdRuntimeException("Ошибка при загрузке кэша заметок", "note.cache.load.failed");
        }
    }

}

