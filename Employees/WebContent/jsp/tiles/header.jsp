<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Welcome Part -->
<div id="welcome" class="post">
	<h2 class="title">
		<bean:message key="common.top.main.welcome" />
	</h2>
	<h3 class="date">
		<span> <jsp:useBean id="now" class="java.util.Date" /> <fmt:setLocale
				value="EN_US" /> <c:set var="date" value="${java.util.Date}">

			</c:set> <fmt:formatDate value="${now}" type="both" dateStyle="medium"
				timeStyle="medium" /><br /> <fmt:formatDate value="${date}"
				type="both" dateStyle="medium" timeStyle="medium" />
			${java.util.Date}
		</span>
	</h3>
	<div class="meta">&nbsp;</div>
</div>