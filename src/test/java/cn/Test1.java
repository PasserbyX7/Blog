package cn;

import org.junit.jupiter.api.Test;

import cn.util.MarkdownUtils;

/**
 * Test1
 */
public class Test1 {

    @Test
    public void testUtil() {
        String text=MarkdownUtils.markdownToText("#小明");
        String t=MarkdownUtils.markdownToHTMLExtensions("#小明");
        System.out.println(t);
    }
}