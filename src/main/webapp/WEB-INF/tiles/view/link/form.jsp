<%@ include file="/WEB-INF/tiles/include-for-forms.jsp"%>

    <form:form id="link-form" class="form-horizontal" method="POST" commandName="linkForm" role="form" >
        <fieldset>
            <legend>Link details</legend>
            <div class="row">
                <div class="col-md-9">
                    <c:if test="${not empty param.error}">
                        <p class="text-error">Please correct the errors below!</p>
                    </c:if>
                    <form:hidden path="id"/>
                    <form:hidden path="version"/>
                    <mvm:input label="Name" type="text" id="inputName" path="name" placeholder="Name" maxlength="40"/>
                    <mvm:input label="Description" type="text" id="inputDescription" path="description" placeholder="Description" maxlength="200"/>
                    <mvm:input label="URL" type="text" id="inputUrl" path="url" placeholder="Url" maxlength="100"/>
                    <mvm:readonly label="Created by" id="inputCreatedBy" path="createdBy.name" />
                    <mvm:readonly label="Date created" id="inputCreateDate" path="createDate" />
                    <mvm:readonly label="Updated by" id="inputUpdatedBy" path="updatedBy.name" />
                    <mvm:readonly label="Date updated" id="inputUpdateDate" path="lastUpdatedDate" />
                    <mvm:multiselect id="inputTags" path="tags" items="${tagNames}" label="Tags" multiple="true"></mvm:multiselect>
                    <mvm:submit submitText="Submit" cancelText="Cancel"/>
                </div>
            </div>
        </fieldset>
    </form:form>

