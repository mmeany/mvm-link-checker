<%@ include file="/WEB-INF/tiles/include.jsp"%>

<h1>Tag</h1>
<div class="row">
    <div class="col-md-2">
        <p><strong>Tag</strong></p>
        <p><strong>Description</strong></p>
        <p><strong>Implied tags</strong></p>
    </div>
    <div class="col-md-4">
        <p>${tag.tag}&nbsp;</p>
        <p>${tag.description}&nbsp;</p>
        <p><c:forEach var="itag" items="${tag.implied}">
	       <a href="<c:url value="/admin/tag/view/${itag.tag}"></c:url>"><span class="label label-success">${itag.tag}</span></a>
        </c:forEach></p>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <p>Note that implied tags will be applied when this tag is applied.</p>
    </div>
</div>