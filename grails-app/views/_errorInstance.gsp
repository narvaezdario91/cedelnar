<g:hasErrors bean="${errorInstance}">
	<ul>
		<g:eachError bean="${errorInstance}" var="error">
			<li><g:message
					error="${error}" /></li>
		</g:eachError>
	</ul>
</g:hasErrors>