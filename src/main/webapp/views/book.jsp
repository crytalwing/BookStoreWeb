<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>


<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="home">Home</a></li>
					<li class="breadcrumb-item active" aria-current="page">Book</li>
				</ol>
			</nav>
		</div>
	</div>
	<p>${messadd}</p>
</div>

<form action="" method="post">
	<div class="container">
		<div class="col">
			<div class="row">
				<div class="col">
					<div class="card bg-light mb-3">
						<div class="card-body">
							<div class="row">
								<c:forEach items="${listAllBook}" var='b'>
									<div class="col-12 col-md-6 col-lg-4">
										<div class="card"
											style="display: block; text-overflow: ellipsis; width: 200px; overflow: hidden; white-space: nowrap;">
											<div class="card-top">

												<a href="<c:url value="/details?bookId=${b.bookID}"/>">
													<img class="card-img-top" src="${b.images}"
													alt="Card image cap" />
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
					<div class="col-12">
						<nav aria-label="...">
							<ul class="pagination">
								<c:if test="${tag>1 }">
									<li class="page-item "><a class="page-link"
										href="product?index=${tag-1}&cid=${tagactive}">Previous</a></li>
								</c:if>
								<c:forEach begin="1" end="${endP}" var="i">
									<li class="page-item ${tag==i ? "active":""}" ><a
										class="page-link" href="product?index=${i}&cid=${tagactive}">${i}</a></li>
								</c:forEach>
								<c:if test="${tag<endP}">
									<li class="page-item"><a class="page-link"
										href="product?index=${tag+1}&cid=${tagactive}">Next</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				</div>
			</div>
		</div>
		</div>
</form>