package com.joy.joyorder.adapters.out.alarm.cache;

import com.joy.joyorder.adapters.out.alarm.AlarmChannel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.afterPropertiesSet();

        RedisTemplate<?, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(StringRedisSerializer.UTF_8);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisMessageListenerContainer redisAlarmMessageListenerContainer(LettuceConnectionFactory lettuceConnectionFactory, MessageListener notificationMessageSubscriber) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.addMessageListener(notificationMessageSubscriber, new ChannelTopic(AlarmChannel.ADMIN_ORDER.name()));
        redisMessageListenerContainer.setConnectionFactory(lettuceConnectionFactory);

        return redisMessageListenerContainer;
    }
}
