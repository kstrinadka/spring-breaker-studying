package com.example.springbreaker.sceensaver;



import ch.qos.logback.core.joran.sanity.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.time.LocalTime;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalTime.now;


/**
 * Scope, чтобы цвет окна менялся раз в 3 секунды
 * Этот Scope еще нужно зарегистрировать. Надо будет сказать контексту,
 * что мы хотим в него новый вид scope добавить (до создания контекста)
 *
 * Такой scope подходит для того, чтобы раз в какое-то время создавать новый бин для обновления информации.
 */
public class PeriodicalScopeConfigurer implements Scope {
    /**
     * String - имя бина
     * LocalTime - сколько уже существует бин
     * Object - сам бин (тут это java.awt.Color)
     */
    Map<String, Map.Entry<LocalTime, Object>> map = new HashMap<>();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (map.containsKey(name)) {
            Map.Entry<LocalTime, Object> entry = map.get(name);
            int secondsSinceLastRequest = now().getSecond() - entry.getKey().getSecond();

            if (secondsSinceLastRequest > 3) {
                entry = new AbstractMap.SimpleEntry<>(now(), objectFactory.getObject());
                map.put(name, entry);
            }
        }
        else {
            map.put(name, new AbstractMap.SimpleEntry<>(now(), objectFactory.getObject()));
        }

        return map.get(name).getValue();
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
