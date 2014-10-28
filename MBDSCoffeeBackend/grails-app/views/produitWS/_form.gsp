<%@ page import="com.Produit" %>



<div class="fieldcontain ${hasErrors(bean: produitInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="produit.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${produitInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: produitInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="produit.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="nombre" type="number" value="${produitInstance.nombre}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: produitInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="produit.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="type" required="" value="${produitInstance?.type}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: produitInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="produit.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${security.User.list()}" optionKey="id" required="" value="${produitInstance?.user?.id}" class="many-to-one"/>

</div>

