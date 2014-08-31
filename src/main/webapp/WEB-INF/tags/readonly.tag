<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="path" required="true" %>

    <c:set var="mvmInputClass" value="" />
    <c:set var="domainNameErrors"><form:errors path="${path}"/></c:set>
    <c:if test="${not empty domainNameErrors}">
        <c:set var="mvmInputClass" value="has-error" />
    </c:if>

    <div class="form-group ${mvmInputClass}">
      <label for="${id}" class="col-sm-2 control-label">${label}</label>
      <div class="col-sm-10">
        <form:input type="text" id="${id}" path="${path}" readonly="true" class="form-control"/>
        <c:if test="${not empty domainNameErrors}"><span class="help-inline">${domainNameErrors}</span></c:if>
      </div>
    </div>
