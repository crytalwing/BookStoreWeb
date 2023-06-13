<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="page-content-wrapper">

	<div class="page-content">

		<div class="row" style="margin: 10px;">

			<div class="col-md-12">

				<div class="portlet-title">

					<div class="caption text-info">

						<h1>Book Manager</h1>

					</div>

					<div class="tools">

						<a href="javascript:;" class="collapse"> </a> <a
							href="#portlet-config" data-toggle="modal" class="config"> </a> <a
							href="javascript:;" class="reload"> </a> <a href="javascript:;"
							class="remove"> </a>

					</div>

				</div>



				<%@include file="/common/info.jsp"%>
				<!-- row2 -->
				<form action="#" method="post">
					<br />
					<!-- col1 -->
					<div class="row">
						<div class="col">
							<div class="form-group" hidden="hidden">

								<label for="UserID">BookID:</label> <input type="text"
									name="bookID" value="${book.bookID }" class="form-control"
									readonly />

							</div>

							<div class="form-group">

								<label for="categoryname">Book Name:</label> <input type="text"
									class="form-control" name="name" value="${book.name }" />

							</div>

							<div class="form-group">

								<label for="categorycode">Description:</label> <input
									type="text" class="form-control" name="description"
									value="${book.description}" />

							</div>
							<div class="form-group">

								<label for="categorycode">Price:</label> <input type="text"
									class="form-control" name="price" value="${book.price}" />

							</div>
							<div class="form-group">

								<label for="categorycode">Language:</label> <input type="text"
									class="form-control" name=language value="${book.language}" />

							</div>

						</div>
						<!-- end col1 -->
						<div class="col">

							<div class="form-group">

								<label for="categorycode">Author:</label> <input type="text"
									class="form-control" name=author value="${book.author}" />

							</div>
							<div class="form-group">

								<label for="categorycode">Rating:</label> <input type="text"
									class="form-control" name=rating value="${book.rating}" />

							</div>
							<label>Pushlisher: </label>
							<div class="form-group">
								<select name="pubname" class="form-control">
									<c:forEach items="${pubList}" var="pub">
										<option>${pub.name}</option>
									</c:forEach>
								</select>
							</div>



						</div>
						<!-- 						endcol2 -->
						<div class="col">
							<div class="form-group">

								<label>Images:</label> <input type="text" class="form-control"
									name="images" value="${book.images}" />

							</div>


							<div class="form-group">

								<button class="btn btn-primary"
									formaction="<c:url value="/admin-book/create"/>">

									Create <i class="fa fa-plus"></i>

								</button>

								<button class="btn btn-warning"
									formaction="<c:url value="/admin-book/update"/>">

									Update <i class="fa fa-edit"></i>

								</button>

								<button class="btn btn-success"
									formaction="${pageContext.request.contextPath }/admin-book/reset">

									Reset <i class="fa fa-undo"></i>

								</button>

							</div>
							<label>Category: </label>
							<div class="form-group">
								<select name="catename" class="form-control">
									<c:forEach items="${cateList}" var="cate">
										<option>${cate.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">

								<button class="btn btn-primary"
									formaction="<c:url value="/admin-book/category"/>">

									Add <i class="fa fa-plus"></i>

								</button>


							</div>

						</div>
						<!-- endcol -->
					</div>
				</form>

				<!-- end row1 -->

				<!-- row2 -->
				<div class="row mt-4" style="padding-right: 25px">

					<table class="table table-hover">
						<thead>
							<tr>
								<th>No</th>
								<th>Name</th>
								<th>Author</th>
								<th>Publisher</th>
								<th>Language</th>
								<th>Price</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bookList}" var="book" varStatus="STT">
								<tr class="odd gradeX">
									<td>${STT.index+1 }</td>
									<td>${book.name }</td>
									<td>${book.author }</td>
									<td>${book.publisher.name}</td>
									<td>${book.language }</td>
									<td>${book.price }</td>
									<td><a
										href="<c:url value='/admin-book/edit?id=${book.bookID}'/>"
										class="center">Edit</a> | <a
										href="<c:url value='/admin-book/delete?id=${book.bookID}'/>"
										class="center">Delete</a> | <a
										href="<c:url value='/admin-book/detail?id=${book.bookID}'/>"
										class="center">Detail</a>|<a
										href="<c:url value='/admin-book/category?id=${book.bookID}'/>"
										class="center">Add Category</a></td>
								</tr>
							</c:forEach>
						</tbody>

					</table>

				</div>
						<div class="row">
						<nav aria-label="...">
							<ul class="pagination">
								<c:if test="${tag>1 }">
									<li class="page-item "><a class="page-link"
										href="book?index=${tag-1}">Previous</a></li>
								</c:if>
								<c:forEach begin="1" end="${endP}" var="i">
									<li class="page-item ${tag==i ? "active":""}" ><a
										class="page-link" href="book?index=${i}">${i}</a></li>
								</c:forEach>
								<c:if test="${tag<endP}">
									<li class="page-item"><a class="page-link"
										href="book?index=${tag+1}">Next</a></li>
								</c:if>
							</ul>
						</nav>
					</div>
				<!-- end row2 -->
			</div>
		</div>
	</div>

</div>



