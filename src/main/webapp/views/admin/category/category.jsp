<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="page-content-wrapper">

	<div class="page-content">

		<div class="row" style="margin: 10px;">

			<div class="col-md-12">

				<!-- BEGIN EXAMPLE TABLE PORTLET-->

				<div class="portlet box grey-cascade">

					<div class="portlet-title">

						<div class="caption text-info">

							<h1>Category Manager</h1>

						</div>

						<div class="tools">

							<a href="javascript:;" class="collapse"> </a> <a
								href="#portlet-config" data-toggle="modal" class="config"> </a>

							<a href="javascript:;" class="reload"> </a> <a
								href="javascript:;" class="remove"> </a>

						</div>

					</div>

					<div class="portlet-body">

						<div class="table-toolbar">

							<!-- Hiển thị thông báo -->

							<%@include file="/common/info.jsp"%>

							<!-- Kết thúc hiển thị thông báo -->

							<div class="row">


								<div class="col-md-3">


									<form action="#" method="post">

										<br />

										<div class="form-group" hidden="hidden">

											<label for="UserID">CategoryID:</label> <input type="text"
												name="categoryID" value="${category.categoryID }"
												class="form-control" readonly />

										</div>

										<div class="form-group">

											<label for="categoryname">Category Name:</label> <input
												type="text" class="form-control" name="name"
												value="${category.name }" />

										</div>

										<div class="form-group">

											<label for="categorycode">Description:</label> <input
												type="text" class="form-control" name="description"
												value="${category.description}" />

										</div>
										<br />

										<hr>

										<div class="form-group">

											<button class="btn btn-primary"
												formaction="<c:url value="/admin-category/create"/>">

												Create <i class="fa fa-plus"></i>

											</button>

											<button class="btn btn-warning"
												formaction="<c:url value="/admin-category/update"/>">

												Update <i class="fa fa-edit"></i>

											</button>
											<button class="btn btn-success"
												formaction="<c:url value="/admin-category/reset"/>">

												Reset <i class="fa fa-undo"></i>

											</button>

										</div>
									</form>
								</div>

								<div class="col-md-9" style="padding-right: 25px">
									<table class="table table-hover">
										<thead>

											<tr>
												<th>No</th>
												<th>Name</th>
												<th>Description</th>
												<th>Action</th>
											</tr>
										</thead>

										<tbody>

											<c:forEach items="${categoryList}" var="category"
												varStatus="STT">
												<tr class="odd gradeX">
													<td>${STT.index+1 }</td>
													<td>${category.name }</td>
													<td>${category.description }</td>
													<td><a
														href="<c:url value='/admin-category/edit?id=${category.categoryID}'/>"
														class="center">Edit</a> | <a
														href="<c:url value='/admin-category/delete?id=${category.categoryID}'/>"
														class="center">Remove</a>
												</tr>
											</c:forEach>
										</tbody>

									</table>

								</div>

							</div>

						</div>

					</div>

				</div>

				<!-- END EXAMPLE TABLE PORTLET-->

			</div>

		</div>

	</div>

</div>
