package com.example.podofarm.utils;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownConverter {

    public static String markdownToHtml(String markdown) {
        System.out.println(markdown + " markdown 언어좀 볼까?");
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlContent = renderer.render(document);

        // 첫 줄이 public으로 시작하는지 확인
        String[] lines = markdown.split("\n");
        boolean isFirstLinePublic = lines.length > 0 && lines[0].trim().startsWith("public");

        // 전체를 <pre>와 <code>로 감싸거나 첫 줄만 <p>로 감싸기
        if (isFirstLinePublic) {
            // 전체 내용을 <pre>와 <code>로 감싸지 않음
            return htmlContent;
        } else {
            // 첫 줄만 <p>로 감싸지 않음
            htmlContent = htmlContent.replaceFirst("^(<p>)(.*)(</p>)$", "$2");
            return htmlContent;
        }
    }
}
