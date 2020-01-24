package cn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cn.domain.Type;

/**
 * TypeService
 */
public interface TypeService {

    Type saveType(Type type);
    Type getType(Long id);
    Type getTypeByName(String name);
    Page<Type> listType(Pageable pageable);
    void deleteType(Long id);
}