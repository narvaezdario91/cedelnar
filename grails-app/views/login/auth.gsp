<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Gentelella Alela! |</title>

<asset:stylesheet src="application.css" />
<asset:javascript src="application.js" />
<asset:stylesheet src="pnotify/pnotify.custom.min.css"/>
<asset:javascript src="pnotify/pnotify.custom.min.js"/>
</head>

<body class="login">
	<g:hiddenField name="urlBase" value="${createLink(uri:'/')}" />
	<div>
		<a class="hiddenanchor" id="signup"></a> <a class="hiddenanchor"
			id="signin"></a>

		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<g:if test='${flash.message}'>
						<div class='login_message'>
							${flash.message}
						</div>
					</g:if>
					<form action="${request.contextPath}/j_spring_security_check"
						method="POST" id='loginForm' autocomplete='off'>
						<h1>Login Form</h1>
						<div>
							<input type="text" class="form-control" placeholder="Username"
								required name="j_username" />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="Password" required name="j_password" />
						</div>
						<div class="checkbox">
							<label for='remember_me'><input type='checkbox'
								class='chk' name='${rememberMeParameter}' id='remember_me'
								<g:if test='${hasCookie}'>checked='checked'</g:if> /> <g:message
									code="springSecurity.login.remember.me.label" /></label>

						</div>
						<div>
							<button type="submit" class="btn btn-default submit">Log
								in</button>
							<a class="reset_pass" href="#">Lost your password?</a>
						</div>

						<div class="clearfix"></div>

						<div class="separator">
							<p class="change_link">
								New to site? <a href="#signup" class="to_register"> Create
									Account </a>
							</p>

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>
									<i class="fa fa-paw"></i> Gentelella Alela!
								</h1>
								<p>©2016 All Rights Reserved. Gentelella Alela! is a
									Bootstrap 3 template. Privacy and Terms</p>
							</div>
						</div>
					</form>
				</section>
			</div>

			<div id="register" class="animate form registration_form">
				<section class="login_content">
					<g:form controller="register" action="create" id="formUser" name="formUser">
						<h1>Crear Cuenta</h1>
						<div>
							<input type="text" class="form-control"
								placeholder="Nombre Completo" required="" name="fullName"
								value="" />
						</div>
						<div>
							<input type="text" class="form-control" placeholder="Cédula"
								required="" name="username" value="" />
						</div>
						<div>
							<input type="email" class="form-control" placeholder="Email"
								required="" name="email" value="" />
						</div>
						<div>
							<input type="password" class="form-control"
								placeholder="Password" required="" name="password"
								value="" />
						</div>
						<div>
							<button type="submit" class="btn btn-default submit"
								href="index.html">Enviar</button>
						</div>

						<div class="clearfix"></div>

						<div class="separator">
							<p class="change_link">
								Ya estas registrado? <a href="#signin" class="to_register">
									Iniciar sesión </a>
							</p>

							<div class="clearfix"></div>
							<br />

							<div>
								<h1>
									<i class="fa fa-paw"></i> Gentelella Alela!
								</h1>
								<p>©2016 All Rights Reserved. Gentelella Alela! is a
									Bootstrap 3 template. Privacy and Terms</p>
							</div>
						</div>
					</g:form>
				</section>
			</div>
		</div>
	</div>
	<script type='text/javascript'>
		(function() {
			document.forms['loginForm'].elements['j_username'].focus();
		})();
	</script>
	<asset:javascript src="register/create.js" />
</body>
</html>
