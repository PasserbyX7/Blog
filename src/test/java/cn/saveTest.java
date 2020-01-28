// package cn;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.commonmark.node.Node;
// import org.commonmark.parser.Parser;
// import org.commonmark.renderer.NodeRenderer;
// import org.commonmark.renderer.Renderer;
// import org.commonmark.renderer.html.HtmlRenderer;
// import org.commonmark.renderer.text.TextContentNodeRendererContext;
// import org.commonmark.renderer.text.TextContentNodeRendererFactory;
// import org.commonmark.renderer.text.TextContentRenderer;
// import org.commonmark.renderer.text.TextContentRenderer.Builder;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.data.domain.Sort;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.util.StringUtils;

// import cn.dao.BlogDao;
// import cn.dao.CommentDao;
// import cn.dao.TagDao;
// import cn.domain.Blog;
// import cn.domain.Comment;
// import cn.domain.Tag;
// import cn.service.BlogService;
// import cn.service.CommentService;
// import cn.service.TagService;
// import cn.service.UserService;
// import cn.util.MD5Utils;
// import cn.util.MarkdownUtils;

// /**
//  * saveTest
//  */

// @SpringBootTest
// @WebAppConfiguration
// public class saveTest {

//     @Test
//     public void test() {
//         System.out.println("-----------------------------------");
//         Sort sort=Sort.by(Sort.Direction.ASC, "createTime");
//         List<Comment>list=commentDao.findByBlogId(19L, sort);
//         List<Comment>result=new ArrayList<>();
//         list.forEach(comment->{
//             if(comment.getParentComment()==null){
//                 comment.setReplyComments(new ArrayList<>());
//                 result.add(comment);
//             }
//             else{
//                 Comment parent=comment.getParentComment();
//                 if(parent.getParentComment()==null)//parent是顶级节点
//                     parent.getReplyComments().add(comment);
//                 else//parent的parent是顶级节点
//                     parent.getParentComment().getReplyComments().add(comment);
//             }
//         });
//         result.forEach(comment->{
//             System.out.println(comment.getContent());
//             System.out.println("*********");
//             comment.getReplyComments().forEach(item->{
//                 System.out.println(item.getContent());
//             }
//             );
//         });
//     }
//     //A
//     //BCDE孙
//     //F
//     //G
//     //H孙
//     @Value("${comment.avatar}")
//     private String avatar;
//     // public static String render(String text) {
//     //     Parser parser = Parser.builder().build();
//     //     Node document = parser.parse(text);
//     //     Renderer renderer = TextContentRenderer.builder().build();
//     //     return renderer.render(document);
//     // }
//     // @Autowired
//     // TagDao tagDao;
//     // @Autowired
//     // BlogDao blogDao;
//     // @Autowired
//     // BlogService blogService;

//     // @Autowired
//     // UserService userService;
//     // @Autowired
//     // TagService tagService;
//     // @Autowired
//     // CommentService commentService;
//     @Autowired
//     CommentDao commentDao;
// }