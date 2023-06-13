<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="home">Home</a></li>
					<li id="userName" class="breadcrumb-item active"
						aria-current="page">User Info: ${userName}</li>
				</ol>
			</nav>
		</div>
	</div>
	
	<%@include file="/common/info.jsp"%>
	
	<div class="container">
		<div class="row">
			<div id="info-box" class="col-md-12">
				<form id="info-form" class="form" action="" method="post">
					<div class="form-group">
						<div class="row">
							<input hidden=true type="text" name="userN" id="userN"
								class="form-control" value="${userN}" name="userN"> <input
								hidden=true type="text" name="balance" id="balance"
								class="form-control" value="${balance}" name="balance">
							<div class="col">
								<label for="fullName" class="text-info">Full Name:</label><br>
								<input class="form-control" type="text" id="fullName"
									value="${fullName}" name="fullName" required
									data-validation-required-message="Please enter your full name.">
								<div class="help-block text-danger"></div>
							</div>
							<div class="col">
								<label for="phoneNumber" class="text-info">Phone:</label><br>
								<input type="tel" inputmode="numeric" pattern="[0-9\s]{10}"
									maxlength="10" name="phoneNumber" id="phoneNumber"
									class="form-control" value="${phoneNumber}" required
									data-validation-required-message="Please enter your phone number.">
								<div class="help-block text-danger"></div>
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="text-info">Address: ${newaa}</label><br>
							<input type="text" name="address" id="address"
								class="form-control" value="${address}" name="address" required
								data-validation-required-message="Please enter your address.">
							<div class="help-block text-danger"></div>
						</div>
						<div class="form-group">
							<label for="password" class="text-info">Password:
								${newpass}</label><br> <input type="text" name="password"
								id="password" class="form-control" value="${password}"
								name="password" required
								data-validation-required-message="Please enter your password.">
							<div class="help-block text-danger"></div>
						</div>
						<div class="row">
							<div class="col">
								<input type="submit" name="submit"
									class="text-white btn btn-info btn-md" value="Submit">
									
								<p class="text-danger pd-l">${mess}</p>

							</div>
							<div class="col">
								<a href="${pageContext.request.contextPath}/funds"
									class="btn btn-primary" style="float: right;" >Balance: ${balance} VND</a>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>