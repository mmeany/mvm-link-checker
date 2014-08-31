<%@ include file="/WEB-INF/tiles/include.jsp"%>

<h1>Link</h1>

<div class="row">
    <div class="col-md-2">
        <p><strong>Id</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.id}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Version</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.version}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Name</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.name}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Description</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.description}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>URL</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.url}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Date created</strong></p>
    </div>
    <div class="col-md-4">
        <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${link.createDate.time}" />&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Created by</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.createdBy.name}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Last updated</strong></p>
    </div>
    <div class="col-md-4">
        <p><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${link.lastUpdatedDate.time}" />&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Updated by</strong></p>
    </div>
    <div class="col-md-4">
        <p>${link.updatedBy.name}&nbsp;</p>
    </div>
</div>
<div class="row">
    <div class="col-md-2">
        <p><strong>Tags</strong></p>
    </div>
    <div class="col-md-4">
        <p>
        <c:forEach var="tag" items="${link.tags}">
           <a href="<c:url value="/admin/tag/view/${tag.tag}"></c:url>"><span class="label label-success">${tag.tag}</span></a>
        </c:forEach>
        </p>
    </div>
</div>
