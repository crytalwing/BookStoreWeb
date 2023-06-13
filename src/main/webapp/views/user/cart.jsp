<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="page-content-wrapper">

	<div class="page-content">

		<div class="row" style="margin: 10px;">

			<div class="col-md-12">

				<div class="portlet-title">

					<div class="caption text-info">

						<!-- end row1 -->

						<!-- row2 -->
						<div class="row mt-4" style="padding-right: 25px">

							<table class="table table-hover">
								<thead>
									<tr>
										<th>No</th>
										<th>Name</th>
										<th>Image</th>
										<th>Publisher</th>
										<th>Language</th>
										<th>Price</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${detaillist}" var="dl" varStatus="STT">
										<tr class="odd gradeX">
											<td>${STT.index+1 }- ${detaillist.size()}</td>
											<td>${dl.book.name}</td>
											<td>${dl.book.images}</td>
											<td>${dl.book.publisher.name}</td>
											<td>${dl.book.language }</td>
											<td>${dl.book.price }</td>
											<td><form action ="" method="post">
													<a
														href="<c:url value='/cart-delete?id=${dl.orderDetailID}'/>"
														class="center">Remove</a>
														
												</form></td>
										</tr>
									</c:forEach>
								</tbody>

							</table>
							<div class="row">
								<div class="col">
									<%@include file="/common/info.jsp"%>
									<form method="post">
										<a href="<c:url value='/cart-checkout'/>" class="btn btn-info"><i
											class="fa fa-shopping-cart" aria-hidden="true"></i>Purchase</a>
									</form>
								</div>
							</div>
						</div>
						<!-- end row2 -->
					</div>
				</div>
			</div>

		</div>

	</div>
</div>

