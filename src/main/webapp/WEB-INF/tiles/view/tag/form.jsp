<%@ include file="/WEB-INF/tiles/include-for-forms.jsp"%>

    <form:form id="tag-form" class="form-horizontal" method="POST" commandName="tagForm" >
        <fieldset>
            <legend>Tag details</legend>
            <div class="row">
                <div class="col-md-9">
                    <c:if test="${not empty param.error}">
                        <p class="text-error">Please correct the errors below!</p>
                    </c:if>

                    <mvm:input label="Tag" type="text" id="inputTag" path="tag" placeholder="Tag" maxlength="40"/>
                    <mvm:input label="Description" type="text" id="inputDescription" path="description" placeholder="Description" maxlength="200"/>
                    <mvm:multiselect id="inputImplied" path="implied" items="${tagNames}" label="Implied tags" multiple="true"></mvm:multiselect>
                    <mvm:submit submitText="Submit" cancelText="Cancel"/>
                    <form:hidden path="originalTag"/>
                </div>
            </div>
        </fieldset>
    </form:form>
<div class="row">
    <div class="col-md-6">
        <p>Note that implied tags will be applied when this tag is applied.</p>
    </div>
</div>

