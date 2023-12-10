package com.dev.GymForDevelopers.listner;

import com.dev.GymForDevelopers.exceptions.GdRuntimeException;
import com.dev.GymForDevelopers.repositories.GdAnswerRepository;
import com.dev.GymForDevelopers.repositories.GdNoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class AppStopListener implements ApplicationListener<ContextClosedEvent> {

    private final GdAnswerRepository gdAnswerRepository;
    private final GdNoteRepository gdNoteRepository;


    @Autowired
    public AppStopListener(GdAnswerRepository gdAnswerRepository, GdNoteRepository gdNoteRepository) {
        this.gdAnswerRepository = gdAnswerRepository;
        this.gdNoteRepository = gdNoteRepository;
    }

    @Override
    public void onApplicationEvent(@Nullable ContextClosedEvent event) {
        try {
            List<Long> list = new ArrayList<>();
            AppStartedListener.mapAnswerLikes.forEach((id, likes) -> {
                        Long endValue = likes.getEndValue();
                        if (likes.notEq()) {
                            gdAnswerRepository.saveLikes(id, endValue);
                            list.add(id);
                        }
                    }
            );
            log.info("Количество ответов у которых были обновлены лайки: {}", list.size());
        } catch (Exception e) {
            log.error("Ошибка при сохранении кэша ответов");
            throw new GdRuntimeException("Ошибка при сохранении кэша ответов", "answer.cache.save.failed");
        }


        try {
            AtomicInteger counter = new AtomicInteger();
            AppStartedListener.mapNoteLikes.forEach((id, likes) -> {
                Long endValueLikes = likes.getEndValue();
                if (likes.notEq()) {
                    gdNoteRepository.saveNoteLikes(id, endValueLikes);
                    counter.getAndIncrement();
                }
            });
            log.info("Количество заметок у которых были обновлены дизлайки: {}", counter);

            List<Long> listDislikes = new ArrayList<>();
            AppStartedListener.mapNoteDislikes.forEach((id, dislikes) -> {
                Long endValueDislikes = dislikes.getEndValue();
                if (dislikes.notEq()){
                    gdNoteRepository.saveNoteDislikes(id, endValueDislikes);
                    listDislikes.add(id);
                }
            });
            log.info("Количество заметок у которых были обновлены дизлайки: {}", listDislikes.size());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GdRuntimeException(e.getMessage(), "note.cache.save.failed");
        }
    }
}
