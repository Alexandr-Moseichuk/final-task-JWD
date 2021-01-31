package by.moseichuk.adlinker.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(PaginationTag.class);
    // сколько ссылок отображается начиная с самой первой (не может быть установлено в 0)
    private final int N_PAGES_FIRST = 1;
    // сколько ссылок отображается слева от текущей (может быть установлено в 0)
    private final int N_PAGES_PREV = 2;
    // сколько ссылок отображается справа от текущей (может быть установлено в 0)
    private final int N_PAGES_NEXT = 2;
    // сколько ссылок отображается в конце списка страниц (не может быть установлено в 0)
    private final int N_PAGES_LAST = 1;
    // показывать ли полностью все ссылки на страницы слева от текущей, или вставить многоточие
    private boolean showAllPrev;
    // показывать ли полностью все ссылки на страницы справа от текущей, или вставить многоточие
    private boolean showAllNext;
    //многоточие
    private static final String DISABLED_LINK = "<li><a class=\"page-link disabled\">...</a></li>";

    private Integer currentPage;
    private Integer lastPage;
    private String url;

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int doStartTag() throws JspException {
        showAllPrev = (N_PAGES_FIRST + N_PAGES_PREV + 1) >= currentPage;
        showAllNext = currentPage + N_PAGES_NEXT >= lastPage - N_PAGES_LAST;

        printPagination();

        return SKIP_BODY;
    }


    private void printPagination() {
        JspWriter writer = pageContext.getOut();
        try {
            int prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
            writer.write("<ul class=\"pagination\">");
            writer.write(getLinkElement(prevPage, "&lt; Prev"));
            //show left pages
            if (showAllPrev) {
                for (int i = 1; i <= currentPage - 1; i++) {
                    writer.write(getLinkElement(i, String.valueOf(i)));
                }

            } else {
                for (int i = 1; i <= N_PAGES_FIRST; i++) {
                    writer.write(getLinkElement(i, String.valueOf(i)));
                }
                writer.write(DISABLED_LINK);
                for (int i = currentPage - N_PAGES_PREV; i <= currentPage - 1; i++) {
                    writer.write(getLinkElement(i, String.valueOf(i)));
                }
            }
            //show current pageclass="page-item"
            writer.write(String.format("<li class=\"active page-item\"><a class=\"page-link\" href=\"%s?currentPage=%d\">%d</a></li>",
                    url, currentPage, currentPage));
            //show last pages
            if (showAllNext) {
                for (int i = currentPage + 1; i <= lastPage; i++) {
                    writer.write(getLinkElement(i, String.valueOf(i)));
                }
            } else {
                for (int i = currentPage + 1; i <= currentPage + N_PAGES_NEXT; i++) {
                    writer.write(getLinkElement(i, String.valueOf(i)));
                }
                writer.write(DISABLED_LINK);
                for (int i = lastPage - N_PAGES_LAST + 1; i <= lastPage; i++) {
                    writer.write(getLinkElement(i, String.valueOf(i)));
                }
            }
            int nextPage = Math.min(currentPage + 1, lastPage);
            writer.write(getLinkElement(nextPage, "Next &gt;"));
            writer.write("</ul>");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    private String getLinkElement(int i, String text) {
        return String.format("<li class=\"page-item\"><a class=\"page-link\" href=\"%s?currentPage=%d\">%s</a></li>",url, i, text);
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
