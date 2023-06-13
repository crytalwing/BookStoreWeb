
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<div class="container">
	<div class="row">
		<div class="col">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="home">Home</a></li>
					<li class="breadcrumb-item active"
						aria-current="page">Admin Info: ${admin.username}</li>
				</ol>
			</nav>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div id="info-box" class="col-md-12">
				<form  class="form" action="" method="post">
					<div class="form-group">
						<div class="row">
						
							<input hidden=true type="text"
								class="form-control" value="${admin.username}" name="username"> 
							
							<div class="col">
								<label class="text-info">Full Name:</label><br>
								<input class="form-control" type="text" 
									value="${admin.fullName}" name="fullName" required
									data-validation-required-message="Please enter your full name.">
								<div class="help-block text-danger"></div>
							</div>
							
							<div class="col">
								<label  class="text-info">Phone:</label><br>
								<input type="tel" inputmode="numeric" pattern="[0-9\s]{10}"
									maxlength="10" name="phoneNumber"
									class="form-control" value="${admin.phoneNumber}" required
									data-validation-required-message="Please enter your phone number.">
								<div class="help-block text-danger"></div>
							</div>
							
						</div>
						<div class="form-group">
							<label for="address" class="text-info">Birthdate: </label><br>
							<input type="text" name="birthDate"
								class="form-control" value="${birthdate}"  required
								data-validation-required-message="Please enter your birthDate">
							<div class="help-block text-danger"></div>
						</div>
						
						<div class="row">
						<div class="col">
								<input type="submit" name="submit"
									class="text-white btn btn-info btn-md" value="Submit">
									

							</div>
							<p >${messa}</p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>