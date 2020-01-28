package cn.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Blog
 */
@Data
@Entity
@Table
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;
    private String picture;
    @NotNull
    private String flag;
    private String description;
    private Integer viewNum;
    private boolean reward;
    private boolean recommend;
    private boolean reproduction;
    private boolean publish;
    private boolean commentable;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    @ManyToOne
    private Type type;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Tag>tags=new ArrayList<>();
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog")
    private List<Comment>comments=new ArrayList<>();
}