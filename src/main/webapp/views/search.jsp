<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="home">Home</a></li>
					<li class="breadcrumb-item"><a href="book">Book</a></li>
					<li class="breadcrumb-item active" aria-current="page">Search</li>
				</ol>
			</nav>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col">
			<%-- 					<c:forEach items="${listCate}" var="c">
 --%>
			<div class="card bg-light mb-3">
				<%-- 								<div class="card-header bg-primary text-white text unppercase">${c.categoryName}</div>
 --%>
				<div class="card-body">
					<div class="row">
						<p class="text">${found}</p>
						<c:forEach items="${searchlist}" var='b'>
							<%-- 											<c:if test="${lallp.categoryId == c.categoryId}">
 --%>
							<div class="col-12 col-md-6 col-lg-4">
								<div class="card"
									style="display: block; text-overflow: ellipsis; width: 200px; overflow: hidden; white-space: nowrap;">
									<div class="card-top">
										<a href="<c:url value="/search/${b.bookID}"/>"> <a
											href="<c:url value="./details?bookId=${b.bookID}"/>"> <img
												class="card-img-top" src="${b.images}" alt="Card image cap" />
										</a>
										</a>
									</div>
									<div class="card-body">
										<h4 class="card-title">
											<a>${b.name}</a>
										</h4>
									</div>
									<div class="card-footer">
										<p class="card-text">${b.description}</p>
										<div class="row">
											<div class="col">
												<p class="btn btn-danger btn-block">${b.price}VNĐ</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

			</div>

			<%--  					</c:forEach>
 --%>
		</div>
	</div>



</div>