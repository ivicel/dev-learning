package cc.ryanc.halo.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.commonmark.Extension;
import org.commonmark.ext.front.matter.YamlFrontMatterExtension;
import org.commonmark.ext.front.matter.YamlFrontMatterVisitor;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownUtils {

    private static final Set<Extension> EXTENSION_YAML = Collections.singleton(YamlFrontMatterExtension.create());

    private static final Set<Extension> EXTENSION_TABLE = Collections.singleton(TablesExtension.create());

    private static final Parser PARSER = Parser.builder().extensions(EXTENSION_YAML).extensions(EXTENSION_TABLE).build();

    private static final HtmlRenderer HTML_RENDERER = HtmlRenderer.builder().extensions(EXTENSION_YAML)
            .extensions(EXTENSION_TABLE).build();

    public static String render(String content) {
        final Node document = PARSER.parse(content);
        return HTML_RENDERER.render(document);
    }

    public static Map<String, List<String>> getFrontMatter(String content) {
        final YamlFrontMatterVisitor visitor = new YamlFrontMatterVisitor();
        Node document = PARSER.parse(content);
        document.accept(visitor);
        return visitor.getData();
    }
}
