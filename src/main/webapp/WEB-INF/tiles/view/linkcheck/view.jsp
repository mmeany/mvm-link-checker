<%@ include file="/WEB-INF/tiles/include.jsp"%>

<h1>Link Check</h1>

<div class="row">
    <div class="col-md-2">
        <p><strong>When run</strong></p>
    </div>
    <div class="col-md-4">
        <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${linkCheck.launchedDate.time}" />&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Run by</strong></p>
    </div>
    <div class="col-md-4">
        <p>${linkCheck.launchedBy.name}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Tags</strong></p>
    </div>
    <div class="col-md-4">
        <p>
        <c:forEach var="tag" items="${linkCheck.tags}">
           <a href="<c:url value="/admin/tag/view/${tag.tag}"></c:url>"><span class="label label-success">${tag.tag}</span></a>
        </c:forEach>
        </p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Links checked</strong></p>
    </div>
    <div class="col-md-4">
        <p>${linkCheck.totalLinksChecked}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Number of errors</strong></p>
    </div>
    <div class="col-md-4">
        <p>${linkCheck.totalFinalErrors}&nbsp;</p>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">Links</div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Url</th>
                <th>Success</th>
                <th>Status</th>
                <th>Size</th>
                <th>Duration</th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="result" items="${linkCheck.results}">
            <tr>
                <td>${result.link.name}</td>
                <td>${result.link.url}</td>
                <td>${result.success}</td>
                <td>${result.responseCode}</td>
                <td>${result.responseSize}</td>
                <td>${result.timeTakenMillis}</td>

                <td>
                    <a href="<c:url value="/admin/link/view/${result.link.id}"/>">view</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


