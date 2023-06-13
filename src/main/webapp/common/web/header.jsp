<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-expand-sm bg-info navbar-dark">
	<a class="navbar-brand" href="#">Eclipse</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="row" style="width: 100%">
		<div class="column" style="width: 30%">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<c:choose>
						<c:when test="${sessionScope.checkuser == null}">

							<li class="nav-item  "><a class="nav-link " href="home">Home
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item  "><a class="nav-link " href="book">Book
									<span class="sr-only">(current)</span>
							</a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/login">Login </a></li>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/register">Register</a></li>
						</c:when>
						<c:otherwise>
							<c:if test="${sessionScope.checkuser == 1}">
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/admin/book">Book </a></li>
									<li><a class="nav-link"
									href="${pageContext.request.contextPath}/admin/publisher">Publisher </a></li>
									<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/admin/category">Categories </a></li>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/admin/salecalc">Sales
								</a></li>
							</c:if>
							<c:if test="${sessionScope.checkuser == 2}">
								<li class="nav-item  "><a class="nav-link " href="home">Home
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="nav-item  "><a class="nav-link " href="book">Book
										<span class="sr-only">(current)</span>
								</a></li>
								<!-- 								<div class="colum my-2 my-sm-0" style="width: 3%"> -->
								<!-- 									<form action="cart" class="form-inline my-2 my-lg-0"> -->
								<!-- 										<button class="btn btn-info my-2 my-sm-0" type="submit" -->
								<%-- 											formaction="${pageContext.request.contextPath}/cart"> --%>
								<!-- 											<i class="fa fa-shopping-cart"></i> -->
								<!-- 										</button> -->
								<!-- 									</form> -->
								<!-- 								</div> -->
							</c:if>
							<li class="nav-item"><a class="nav-link"
								href="${pageContext.request.contextPath}/logout">Logout </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<c:if test="${sessionScope.checkuser != 1}">
			<div class="column" style="width: 55%">
				<form action="search" method="post" class="form-inline my-2 my-lg-0">
					<input type="text" name="txtsearch" value="${txt}"
						class="form-control mr-sm-2" style="width: 80%"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-outline-light my-2 my-sm-0" type="submit"
						formaction="<c:url value="/search"/>">Search</button>
				</form>
			</div>
		</c:if>
		<c:if test="${sessionScope.checkuser ==null}">
			<div class="colum mr-sm-2" style="width: 3%">
				<form action="addcart" class="form-inline my-2 my-lg-0">

					<button class="btn btn-info my-2 my-sm-0" type="submit"
						formaction="<c:url value="/login"/>">
						<i class="fa fa-shopping-cart"></i>
					</button>

				</form>
			</div>
		
			<div class="col" style="width: 7%">
				<form action="user" class="form-inline my-2 my-lg-0">
					<button class="btn btn-info my-2 my-sm-0" type="submit"
						formaction="<c:url value="/login"/>">
						<i class="fa fa-user"></i>
					</button>
				</form>
			</div>
		</c:if>
		<c:if test="${sessionScope.checkuser == 2}">
			<div class="colum mr-sm-2" style="width: 3%">
				<form action="addcart" class="form-inline my-2 my-lg-0">

					<button class="btn btn-info my-2 my-sm-0" type="submit"
						formaction="<c:url value="/cart"/>">
						<i class="fa fa-shopping-cart" ></i>
					
						
					</button>
			
				</form>
			</div>
			<div class="colum mr-sm-2" style="width: 3%">
			<div class ="text-white">${sessionScope.detaillist.size()}</div></div>
<!-- 			<input hidden=true type="text" name="user" id="user" -->
<%-- 				class="form-control" value="${user}" name="user"> --%>
			<div class="col" style="width: 3%">
				<form action="user" class="form-inline my-2 my-lg-0">
					<button class="btn btn-info my-2 my-sm-0" type="submit"
						formaction="<c:url value="/user"/>">
						<i class="fa fa-user"></i>
					</button>
				</form>
			</div>
		</c:if>

	</div>
</nav>

<section class="jumbotron text-center">
	<div class="container">
		<h1 class="jumbotron-heading">ECLIPSE BOOK STORE</h1>
		<p class="lead text-muted mb-0">This is the most fantasy book store in Earth 616</p>
	</div>
</section>
</html>