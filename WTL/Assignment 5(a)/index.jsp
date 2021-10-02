<%@ taglib uri="/struts-tags" prefix="s" %>

<s:form action="register">
<s:textfield name="name" label="UserName"></s:textfield>
<s:password name="password" label="Password"></s:password>
<s:textfield name="email" label="Email"></s:textfield>
<s:radio list="{'male','female'}" name="gender"></s:radio>
<s:select cssStyle="width:155px;" list="{'india','pakistan','nepal','bhutan'}" name="country" label="Country"></s:select>

<s:submit value="register"></s:submit>

</s:form>