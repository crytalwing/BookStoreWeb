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
					<li class="breadcrumb-item active" aria-current="page">Details</li>
				</ol>
			</nav>
		</div>
	</div>
</div>
<div class="container">


	<form action="#" method="post">
		<div class="row">
			<div class="col-sm-5">
				<div class="form-group">
					<img class="card-img-top img-fluid" src="${book.images}"
						alt="Card image cap"></img>
				</div>
			</div>
			<div class="col">
				<div class="form-group" hidden="hidden">

					<label>BookID:</label> <input type="text" name="bookId"
						value="${book.bookID }" class="form-control" readonly />

				</div>
				<h1>${book.name}</h1>
				<h3 class="text" style="color: #0033FF">Price: ${book.price}
					VNĐ</h3>
				<h5>
					Categories:
					<c:forEach var="cate" items="${lstCate}">
						<h6>
							<span style="margin-left: 20px">- ${cate.category.name}</span>
						</h6>
					</c:forEach>
				</h5>
				<h5>Rate: ${book.rating}/10</h5>
				<h5>Author: ${book.author}</h5>
				<h5>Publisher: ${book.publisher.name}</h5>
				<h5>Language: ${book.language}</h5>
				<p>Desc: ${book.description}</p>
				<button class="btn btn-primary"
					formaction="<c:url value="/add-cart"/>">
					Add <i class="fa fa-plus"></i>
				</button>
				<p class="text-danger pd-l">${mess}</p>
			</div>
		</div>
	</form>
</div>