package com.cecs571.spaqrlqueryengine;

import com.webfirmframework.wffweb.tag.html.Body;
import com.webfirmframework.wffweb.tag.html.Html;
import com.webfirmframework.wffweb.tag.html.tables.Table;
import com.webfirmframework.wffweb.tag.html.tables.Td;
import com.webfirmframework.wffweb.tag.html.tables.Th;
import com.webfirmframework.wffweb.tag.html.tables.Tr;
import com.webfirmframework.wffweb.tag.htmlwff.NoTag;
public class HTMLFactory {
    private final Html html;
    private final Body body;
    public HTMLFactory() {
        html = new Html(null);
        html.setPrependDocType(true);
        body = new Body(html);
    }
    public Table addTable() {
        return new Table(body);
    }
}
