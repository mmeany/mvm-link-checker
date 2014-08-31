<%@ include file="/WEB-INF/tags/include-for-tags.jsp"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="path" required="true" %>
<%@ attribute name="items" required="true" type="java.util.List" %>
<%@ attribute name="multiple" required="true"  %>

    <c:set var="mvmInputClass" value="" />
    <c:set var="domainNameErrors"><form:errors path="${path}"/></c:set>
    <c:if test="${not empty domainNameErrors}">
        <c:set var="mvmInputClass" value="has-error" />
    </c:if>

    <c:set var="multipleClass" value="" />
    <c:if test="${multiple}">
        <c:set var="multipleClass" value="multiselect" />
    </c:if>

    <div class="form-group ${mvmInputClass}">
      <label for="${id}" class="col-sm-2 control-label">${label}</label>
      <div class="col-sm-3">
        <form:select id="${id}" path="${path}" items="${items}" multiple="${multiple}" class="${multipleClass}"/>
        <c:if test="${not empty domainNameErrors}"><span class="help-inline">${domainNameErrors}</span></c:if>
      </div>
    </div>
    