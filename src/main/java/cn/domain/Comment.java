package cn.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
/**
 * Comment
 */
@Data
@Entity
@Table
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String content;
    private String avatar;
    private LocalDateTime createTime;
    private Boolean adminComment;
    @ManyToOne
    private Blog blog;
    @OneToMany(mappedBy = "parentComment")
    List<Comment>replyComments=new ArrayList<>();
    @ManyToOne
    Comment parentComment;
}