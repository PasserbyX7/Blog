package cn.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
/**
 * Type
 */
@Data
@Entity
@Table
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "分类名不能为空")
    @Size(max = 7,message = "分类名长度不能超过七个字")
    private String name;
    @OneToMany(mappedBy = "type")
    private List<Blog>blogs=new ArrayList<>();
}