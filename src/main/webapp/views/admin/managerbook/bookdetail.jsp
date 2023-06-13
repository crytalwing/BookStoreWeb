<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container">
	<div class="row">
		<!-- Image -->
		<div class="col-12 col-lg-6">
			<div class="  mb-3">
				<div class="card-body " style="text-align: center;">
					<a href="" data-toggle="modal"> <img class="img-fluid"
						width="50%" src="${book.images}" />
					</a>
				</div>
			</div>
		</div>

		<div class="col-12 col-lg-6 add_to_cart_block">
			<div class="card  mb-3">
				<div class="card-body text-muted ">
					<h4 class="product-title text-uppercase  text-info">${book.name}</h4>
					<div class="stars text-warning">
						<span class="fa fa-star checked"></span> <span
							class="fa fa-star checked"></span> <span
							class="fa fa-star checked"></span> <span class="fa fa-star"></span>
						<span class="fa fa-star"></span> <span class="review-no ml-3">
							${book.rating}/10</span>

					</div>
					<p class="product-description  ">${book.description}</p>
					<h5 class="price text-uppercase ">
						current price: <span>${book.price} VND</span>
					</h5>
					
					<h6>
						<span>Language: ${book.language}</span>
					</h6>
					<h6>
						<span>Category:</span>
					</h6>
					<c:forEach var="cate" items="${lstCate}">
						<h6>
							<span style="margin-left: 20px">- ${cate.category.name}</span>
						</h6>
					</c:forEach>
					<h6>
						<span>Author: ${book.author}</span>
					</h6>
					<h6>
						<span>Publisher: ${book.publisher.name}</span>
					</h6>
				</div>
			</div>
		</div>
	</div>


</div>
