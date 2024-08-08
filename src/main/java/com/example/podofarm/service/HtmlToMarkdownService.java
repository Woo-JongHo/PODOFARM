package com.example.podofarm.service;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.stereotype.Service;
@Service
public class HtmlToMarkdownService {

    public String convertHtmlToMarkdown(String s) {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(s);
        String html = renderer.render(document);
        System.out.println(html + " HTML 확인");
        return html;
    }
}
