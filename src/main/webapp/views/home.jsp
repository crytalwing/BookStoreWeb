<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<link href="<c:url value="/templates/css/common.css"/>" rel="stylesheet"
	type="text/css">
<div class="container">
	<div class="row">
		<div class="col">
			<div id="carouselExampleIndicators" class="carousel slide"
				data-ride="carousel">
				<ol class="carousel-indicators">
					<li data-target="#carouselExampleIndicators" data-slide-to="0"
						class="active"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
					<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block w-100 h-100"
							src="https://cdn.oneesports.gg/cdn-data/2022/06/Anime_KonosubaOP2-6299a0ae4c3c5-1024x576.webp"
							alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100"
							src="https://cdn-4.ohay.tv/imgs/fe5f49038957422a8b9b/728.jpg"
							alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100"
							src="https://sixdegreesfromdave.com/wp-content/uploads/2022/10/witch5.jpg"
							alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>
		</div>
		<div class="col-12 col-md-3">
			<div class="card">
				<div class="card-header bg-success text-white text-uppercase">
					<i class="fa fa-heart"></i> Top product
				</div>
				<c:forEach items="${listBestPro1}" var="bp">
					<div class="col-sm">
						<div class="card nowrap">
							<div class="card-top">
								<a href="<c:url value="/details?bookId=${bp.bookID}"/>"> <img
									class="card-img-top" src="${bp.images}" alt="Card image cap" />
								</a>
								<div class="card-body">
									<h4 class="card-title text-center">
										<a class="text-st" href="<c:url value="/details?bookId=${bp.bookID}"/>"
											title="View Product">${bp.name}</a>
									</h4>
									<p class="nowrap card-text">${bp.description}</p>
									<div class="row">
										<div class="col">
											<p class="btn btn-danger btn-block">${bp.price}</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
		<div class="container mt-3">
			<div class="row">
				<div class="col-sm">
					<div class="card">
						<div class="card-header bg-primary text-white text-uppercase">
							<i class="fa fa-star"></i> Last products
						</div>
						<div class="card-body">
							<div class="row">

								<c:forEach items="${listLastPro}" var="p">
									<div class="col-sm pt-3">
										<div class="card "
											style="display: block; 
											       text-overflow: ellipsis; 
											       width: 250px; 
											       overflow: hidden; 
											       white-space: nowrap;">
											<div class="card-top">

												<a href="<c:url value="/details?bookId=${p.bookID}"/>">
													<img class="card-img-top" src="${p.images}"
													alt="Card image cap" />
												</a>

											</div>
											<div class="card-body">
												<h4 class="card-title">
													<a class="nowrap"href="<c:url value="/details?bookId=${p.bookID}"/>"
														title="View Product">${p.name}</a>
												</h4>
												<p class="nowrap card-text">${p.description}</p>
												<div class="row">
													<div class="col">
														<p class="btn btn-info btn-block">${p.price}$VNĐ</p>
													</div>
												</div>
											</div>
										</div>
									</div>

								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="container mt-3 mb-4">
			<div class="row">
				<div class="col-sm">
					<div class="card">
						<div class="card-header bg-primary text-white text-uppercase">
							<i class="fa fa-trophy"></i> Best products
						</div>
						<div class="card-body">
							<div class="row">

								<c:forEach items="${listBestPro}" var="bp">
									<div class="col-sm">
										<div class="card"
											style="display: block; text-overflow: ellipsis; width: 250px; overflow: hidden; white-space: nowrap;">
											<div class="card-top">

												<a href="<c:url value="/details?bookId=${bp.bookID}"/>">
													<img class="card-img-top" src="${bp.images}"
													alt="Card image cap" />
												</a>

											</div>
											<div class="card-body">
												<h4 class="card-title">
													<a class="nowrap" href="<c:url value="/details?bookId=${bp.bookID}"/>"
														title="View Product">${bp.name}</a>
												</h4>
												<p class="nowrap card-text">${bp.description}</p>
												<div class="row">
													<div class="col">
														<p class="btn btn-info btn-block">${bp.price} VNDĐ</p>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>