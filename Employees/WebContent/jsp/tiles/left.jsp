<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Setting SideBar -->
<div id="sidebar">
	<div id="logo">
		<h1>
			<a href="${pageContext.request.contextPath}/controller"> <bean:message
					key="common.side_bar.emblem" /></a>
		</h1>
	</div>
	<div id="menu" class="boxed">
		<div class="content">
			<ul>
				<li class="active"><a
					href="${pageContext.request.contextPath}/controller"> <bean:message
							key="common.side_bar.menu.homepage" />
				</a></li>
				<li><a href="files/agreement.txt"> <bean:message
							key="common.side_bar.menu.agreement" />
				</a></li>
				<li><a href="mailto:mmfbahmutsk@gmail.com"> <bean:message
							key="common.side_bar.menu.support" />
				</a></li>
			</ul>
		</div>
	</div>

	<!-- SideBar > Login -->
	<div id="login" class="boxed">
		<h2 class="title">Client Account</h2>
		<div class="content">

			<form name="loginForm" method="get">
				<fieldset>
					<legend>Sign-In</legend>
					<c:remove var="buttonInscription" />
					<c:choose>
						<c:when test="${empty userRole}">
							<label for="inputtext1"> <bean:message
									key="common.side_bar.login_form.login" />
							</label>
							<input id="inputtext1" type="text" name="login" value="" />
							<label for="inputtext2"> <br /> <bean:message
									key="common.side_bar.login_form.password" />
							</label>
							<input id="inputtext2" type="password" name="password" value="" />
							<br />
							<button id="inputsubmit2" class='click' type="button"
								name="inputsubmit2">
								<bean:message key="common.side_bar.login_form.enter_button" />
							</button>
						</c:when>
						<c:otherwise>
							<c:set var="buttonInscription" value="Log Out" scope="session" />
							<input type="hidden" name="command" value="${buttonInscription}">
						</c:otherwise>
					</c:choose>
				</fieldset>
			</form>
		</div>
	</div>
</div>