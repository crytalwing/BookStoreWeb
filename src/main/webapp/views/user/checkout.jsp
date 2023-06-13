<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>



<!-- Block content - Đục lỗ trên giao diện bố cục chung, đặt tên là `content` -->
<div class="container mt-4">
	<form class="needs-validation" name="frmthanhtoan" method="post"
		action="#">
		<input type="hidden" name="kh_tendangnhap" value="dnpcuong">

		<div class="py-5 text-center">
			<i class="fa fa-credit-card fa-4x" aria-hidden="true"></i>
			<h2>Purchase</h2>
			<p class="lead">Please check customer information as well as cart before ordering</p>
		</div>

		<div class="row">
			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Customer's info</h4>

				<div class="row">
					<div class="col-md-12">
						<label for="kh_ten">Name</label> <input type="text"
							class="form-control" name="kh_ten">
					</div>

					<div class="col-md-12">
						<label for="kh_diachi">Addressỉ</label> <input type="text"
							class="form-control" name="kh_diachi">
					</div>
					<div class="col-md-12">
						<label for="kh_dienthoai">Phone</label> <input type="text"
							class="form-control">
					</div>
					<div class="col-md-12">
						<label for="kh_email">Email</label> <input type="text"
							class="form-control">
					</div>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit"
					name="btnDatHang">Order</button>
			</div>
		</div>
	</form>

</div>
<!-- End block content -->
