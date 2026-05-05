// com/example/users/mapper/UsedBookMapper.java
package com.example.users.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsedBookMapper {
    @Select("SELECT stock FROM used_books WHERE used_id = #{usedId}")
    Integer selectStockById(@Param("usedId") Integer usedId);
}