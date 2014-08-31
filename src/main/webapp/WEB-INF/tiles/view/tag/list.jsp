<%@ include file="/WEB-INF/tiles/include.jsp"%>
<div id="ajaxload">
<h1>Tag List<small> - viewing page ${page.number + 1} of ${page.totalPages}</small></h1>
<div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="tag" items="${page.content}">
                <c:choose>
                    <c:when test="${not user.enabled}">
                       <c:set var="clazz" value="class='warning'"/>
                    </c:when>
                    <c:when test="${user.administrator}">
                       <c:set var="clazz" value="class='info'"/>
                    </c:when>
                    <c:otherwise>
                       <c:set var="clazz" value=""/>
                    </c:otherwise>
                </c:choose>
                
            <tr>
                <td>${tag.tag}</td>
                <td>${tag.description}</td>
                <td>
                    <a href="<c:url value="/admin/tag/view/${tag.tag}"/>">view</a> |
                    <a href="<c:url value="/admin/tag/edit/${tag.tag}"/>">edit</a> |
                    <a href="<c:url value="/admin/link/list/tagged/${tag.tag}"/>">view links</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<mvm:pagination surrounding="2" showSearch="false"/>

</div>
