package cn.service;

import java.util.List;

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
    List<Type> listType();
    List<Type>listTopType(Integer num);
    Long getTotalNum();
    void deleteType(Long id);
    boolean containsType(String typeName);
}