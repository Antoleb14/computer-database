package com.excilys.computerdatabase.ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class LocalDateTimeTag extends TagSupport {

    private static final long serialVersionUID = -7083597133106329343L;
    private String pattern = "dd/MM/yyyy";
    private LocalDateTime date;

    @Override
    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            if (date != null) {
                out.println(date.format(DateTimeFormatter.ofPattern(pattern)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}
