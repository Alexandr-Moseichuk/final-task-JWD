package by.moseichuk.final_task_JWD.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    // сколько ссылок отображается начиная с самой первой (не может быть установлено в 0)
    private final int N_PAGES_FIRST = 2;
    // сколько ссылок отображается слева от текущей (может быть установлено в 0)
    private final int N_PAGES_PREV = 2;
    // сколько ссылок отображается справа от текущей (может быть установлено в 0)
    private final int N_PAGES_NEXT = 2;
    // сколько ссылок отображается в конце списка страниц (не может быть установлено в 0)
    private final int N_PAGES_LAST = 2;
    // показывать ли полностью все ссылки на страницы слева от текущей, или вставить многоточие
    private boolean showAllPrev;
    // показывать ли полностью все ссылки на страницы справа от текущей, или вставить многоточие
    private boolean showAllNext;

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
        showAllPrev = N_PAGES_FIRST >= (currentPage - N_PAGES_PREV);
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
                if (currentPage > 0) {
                    for (int i = 1; i < currentPage - 1; i++) {
                        writer.write(getLinkElement(i, String.valueOf(i)));
                    }
                } else {
                    for (int i = 1; i < N_PAGES_FIRST; i++) {
                        writer.write(getLinkElement(i, String.valueOf(i)));
                    }
                    writer.write("<li><span>...</span></li>");
                    for (int i = currentPage + 1 + N_PAGES_PREV; i < currentPage - 1; i++) {
                        writer.write(getLinkElement(i, String.valueOf(i)));
                    }
                }
            }
            //show current page
            writer.write(String.format("<li class=\"active\"><a href=\"${url}?currentPage=%d\">%d</a></li>",
                    currentPage, currentPage));
            //show last pages
            if (showAllNext) {
                if (currentPage > 0) {
                    for (int i = currentPage + 1; i < lastPage; i++) {
                        writer.write(getLinkElement(i, String.valueOf(i)));
                    }
                } else {
                    for (int i = currentPage + 1; i < currentPage + N_PAGES_NEXT - 1; i++) {
                        writer.write(getLinkElement(i, String.valueOf(i)));
                    }
                    writer.write("<li><span>...</span></li>");
                    for (int i = lastPage - N_PAGES_LAST - 1; i < lastPage; i++) {
                        writer.write(getLinkElement(i, String.valueOf(i)));
                    }
                }
            }
            int nextPage = Math.min(currentPage + 1, lastPage);
            writer.write(getLinkElement(nextPage, "Next &gt;"));
            writer.write("</ul>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLinkElement(int i, String text) {
        return String.format("<li><a href=\"%s?currentPage=%d\">%s</a></li>",url, i, text);
    }
    /**
     * <c:set var="url" value="${url}"/>
     *         <ul class="pagination">
     *             <li><a href="${url}?currentPage=${currentPage-1 > 0 ? currentPage - 1 : 1}" class="prev">&lt; Prev</a></li>
     *             <%-- show left pages --%>
     *             <c:choose>
     *                 <c:when test="${showAllPrev}">
     *                     <c:if test="${currentPage > 0}">
     *                         <c:forEach begin="1" end="${currentPage - 1}" var="p">
     *                             <li><a href="${url}?currentPage=${p}">${p}</a></li>
     *                         </c:forEach>
     *                     </c:if>
     *                 </c:when>
     *                 <c:otherwise>
     *                     <c:forEach begin="1" end="${N_PAGES_FIRST}" var="p">
     *                         <li><a href="${url}?currentPage=${p}">${p}</a></li>
     *                     </c:forEach>
     *                     <li><span>...</span></li>
     *                     <c:forEach begin="${currentPage + 1 - N_PAGES_PREV}" end="${currentPage - 1}" var="p">
     *                         <li><a href="${url}?currentPage=${p}">${p}</a></li>
     *                     </c:forEach>
     *                 </c:otherwise>
     *             </c:choose>
     *             <%-- show current page --%>
     *                 <li class="active"><a href="${url}?currentPage=${currentPage}">${currentPage}</a></li>
     *             <%-- show right pages --%>
     *             <c:choose>
     *                 <c:when test="${showAllNext}">
     *                     <c:forEach begin="${currentPage + 1}" end="${lastPage}" var="p">
     *                         <li><a href="${url}?currentPage=${p}">${p}</a></li>
     *                     </c:forEach>
     *                 </c:when>
     *                 <c:otherwise>
     *                     <c:forEach begin="${currentPage + 1}" end="${currentPage + (N_PAGES_NEXT - 1)}" var="p">
     *                         <li><a href="${url}?currentPage=${p}">${p}</a></li>
     *                     </c:forEach>
     *                     <li><span>...</span></li>
     *                     <c:forEach begin="${lastPage - (N_PAGES_LAST - 1)}" end="${lastPage}" var="p">
     *                         <li><a href="${url}?currentPage=${p}">${p}</a><li>
     *                     </c:forEach>
     *                 </c:otherwise>
     *             </c:choose>
     *             <li><a href="${url}?currentPage=${currentPage + 1 > lastPage ? lastPage : currentPage + 1}">Next &gt;</a><li>
     *         </ul>
     * @return
     * @throws JspException
     */

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
