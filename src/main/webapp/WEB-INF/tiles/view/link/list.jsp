<%@ include file="/WEB-INF/tiles/include.jsp"%>
<div id="ajaxload">
<h1>Link List<small> - viewing page ${page.number + 1} of ${page.totalPages}</small></h1>
<div>
	<div class="panel panel-default">
		<div class="panel-heading">Links</div>
	    <table class="table table-hover">
	        <thead>
	            <tr>
	                <th>Name</th>
	                <th>Url</th>
	                <th>Tags</th>
	                <th>&nbsp;</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:forEach var="link" items="${page.content}">
	            <tr>
	                <td>${link.name}</td>
	                <td>${link.url}</td>
	                <td>
	                <c:forEach var="tag" items="${link.tags}">
	                    <span class="label label-success">${tag.tag}</span>
	                </c:forEach>
	                </td>
	                <td>
	                    <a href="<c:url value="/admin/link/view/${link.id}"/>">view</a> |
	                    <a href="<c:url value="/admin/link/edit/${link.id}"/>">edit</a> |
	                    <a href="<c:url value="/admin/link/check/${link.id}"/>">check now</a> |
	                    <a href="<c:url value="/admin/link/results/${link.id}"/>">results</a>
	                </td>
	            </tr>
	            </c:forEach>
	        </tbody>
	    </table>
	    <div class="panel-footer mvm-padding-5">
		    <div class="row">
              <div class="col-sm-8">
				<mvm:pagination surrounding="2" showSearch="false"/>
              </div>
              <div class="col-sm-4 mvm-top-margin-2">
                <div>
	                Page ${page.number + 1} of ${page.totalPages}
                </div>
              </div>
		    </div>
	    </div>
	</div>
</div>

</div>
