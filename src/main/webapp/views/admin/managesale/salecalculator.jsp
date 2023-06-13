<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
	<div class="row">
		<div class="col-12 col-sm-3">
			<div class="card bg-light mb-3">
				<ul class="list-group category_block">
					<li class="list-group-item"><a class="text-dark"
						href="<c:url value='/admin/book'/>">Manager Book</a></li>
					<li class="list-group-item"><a class="text-dark"
						href="<c:url value='/admin/salecalc'/>">Display Sales</a></li>
					<li class="list-group-item"><a class="text-dark"
						href="<c:url value='/admin/updateorder'/>">Update Order</a></li>
				</ul>
			</div>
		</div>
		<div class="col">
			<form action="" method="post">
			
				<div class="d-flex mb-2">
					<label class="form-label" style="height: 15px; width: 200px">Enter Month : </label>
					<input type="tel" inputmode="numeric" pattern="[0-9\s]{1-2}"
						maxlength="2" name="selectedmonth" id="selectedmonth"
						class="form-control" value="${selectedmonth}" name="selectedmonth">
					 <input type="submit" name="submit" class="btn btn-primary ms-2" style="width: 200px" value="Display">
					

				</div>
				 <p class="text-danger pd-l">${mess}</p>
				<table class="table" id="table">
					<thead>
						<tr>
							<th scope="col">Book</th>
							<th scope="col">Date</th>
							<th scope="col">Price</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listofsale}" var="prod" varStatus="STT">
							<tr>
								<td>${prod.getBook().getName()}</td>
								<td><fmt:formatDate value="${prod.getOrder().getOrderDate()}" pattern="yyyy-MM-dd" /></td>
								<td>${prod.getOrder().getTotalPrice()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!--  
				<div class="row">
					<div class="col">
						<p class="p-2 mb-2 bg-dark text-white" style="text-align: center">Book</p>
					</div>

					<div class="col">
						<p class="p-2 mb-2 bg-dark text-white" style="text-align: center">Date</p>
					</div>

					<div class="col">
						<p class="p-2 mb-2 bg-dark text-white" style="text-align: center">Total
							price</p>
					</div>
				</div>

				<div class="row">
					<c:forEach items="${listoforder}" var="prod" varStatus="STT">
						<div class="col">
							<div class="p-2 mb-2 bg-secondary text-white">${prod.getBook().getName()}</div>
						</div>

						<div class="col">
							<div class="p-2 mb-2 bg-secondary text-white"><fmt:formatDate value="${prod.getOrder().getOrderDate()}" pattern="yyyy-MM-dd" /></div>
						</div>

						<div class="col">
							<div class="p-2 mb-2 bg-secondary text-white">${prod.getOrder().getTotalPrice()}</div>
						</div>
					</c:forEach>
				</div>
				-->

			</form>
		</div>

	</div>

	</div>