package com.skindow.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

/**
 * Created by Administrator on 2019/8/20.
 */
@Slf4j
public class RedisSerialUtil implements RedisSerializer<Object> {
    /**
     * 定义序列化和反序列化转化类
     */
    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();
    /**
     * 定义转换空字节数组
     */
    private static final byte[] EMPTY_ARRAY = new byte[0];

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object o) throws SerializationException {
        byte[] byteArray = null;
        if (null == o) {
            log.info("----------------------------->:Redis待序列化的对象为空.");
            byteArray = EMPTY_ARRAY;
        } else {
            try {
                byteArray = serializer.convert(o);
            } catch (Exception e) {
                log.error("----------------------------->Redis序列化对象失败,异常：",e);
                byteArray = EMPTY_ARRAY;
            }
        }
        return byteArray;
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        Object obj = null;
        if((null == bytes)|| (bytes.length == 0)){
            log.info("---------------------------------->Redis待反序列化的对象为空.");
        }else{
            try {
                obj = deserializer.convert(bytes);
            } catch (Exception e) {
                log.error("------------------------------------->Redis反序列化对象失败,异常：",e);
            }
        }
        return obj;
    }
}
