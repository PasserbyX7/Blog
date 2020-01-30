package cn.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import lombok.Data;
/**
 * Tag
 */
@Data
@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "标签名不能为空")
    @Max(value = 7,message = "标签名长度不能超过七个字")
    private String name;
    @ManyToMany(mappedBy = "tags")
    private List<Blog>blogs=new ArrayList<>();
}