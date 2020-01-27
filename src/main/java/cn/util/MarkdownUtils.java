package cn.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;

/**
 * MarkdownUtils
 */
public class MarkdownUtils {

    public static String markdownToText(String markdown) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(markdown);
        return TextContentRenderer.builder().build().render(node);
    }

    public static String markdownToHTML(String markdown) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(markdown);
        return HtmlRenderer.builder().build().render(node);
    }

    public static String markdownToHTMLExtensions(String markdown) {
        // 为标题生成id以便于前端插件自动生成目录
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        // 将markdown中的table转换为html
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tableExtension).build();
        Node node = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(headingAnchorExtensions).extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                }).build();
        return null;
    }

    //处理标签属性
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if(node instanceof Link)
                attributes.put("target", "_blank");
            if(node instanceof TableBlock)
                attributes.put("class", "ui celled table");
        }
    }

}