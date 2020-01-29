package cn;

import org.junit.jupiter.api.Test;

import cn.util.MarkdownUtils;

/**
 * Test1
 */
public class Test1 {

    @Test
    public void testUtil() {
        String text1=MarkdownUtils.markdownToText("# This is *Sparta*");
        String text2=MarkdownUtils.markdownToHtmlExtensions("# This is *Sparta*");
        System.out.println(text1);
        System.out.println(text2);
    }
}