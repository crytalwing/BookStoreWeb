<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<link rel="stylesheet" href="<c:url value="templates/css/funds.css"/>">
<title>Bootstrap 4 Funds Form</title>
</head>
<body>
	<div id="funds">
		<div class="container">
			<div id="funds-row"
				class="row justify-content-center align-items-center">
				<div id="funds-column" class="col-md-6">
					<div id="funds-box" class="col-md-12">
						<form id="funds-form" class="form" action="" method="post">
							<h3 class="text-center text-info">Add Funds</h3>
							<div class="form-group">
								<div class="row">
									<div class="col">
										<label for="cardnumber" class="text-info">Card
											Numbers:</label><br> <input type="tel" inputmode="numeric"
											pattern="[0-9\s]{13,19}" autocomplete="cc-number"
											maxlength="19" placeholder="xxxx xxxx xxxx xxxx"
											name="cardnumber" id="cardnumber" class="form-control" required
								data-validation-required-message="Please enter your card number.">
									</div>
								</div>
								<div class="row">
									<div class="col">
										<label for="cvc" class="text-info">CVC:</label><br> <input
											type="tel" inputmode="numeric" pattern="[0-9\s]{3}" name="cvc" id="cvc"
											maxlength="3" placeholder="xxx" class="form-control" required
								data-validation-required-message="Please enter your cvc number.">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="amount" class="text-info">Amount:</label><br>
								<input type="number" name="amount" id="amount" value ="${amount}"
									class="form-control">
							</div>
							<div class="row">
								<div class="col">
									<input type="submit" name="submit"
										class="text-white btn btn-info btn-md" value="Submit"
										style="float: right;">
										<p class="text-danger pd-l">${mess}</p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>