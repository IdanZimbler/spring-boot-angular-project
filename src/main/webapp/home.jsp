<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="addItem">
		Add Item<br>
		<input type="text" name="name" placeholder="Name"><br>
		<input type="text" name="amount"placeholder="Amount"><br>
		<input type="text" name="inventoryCode"placeholder="Inventory Code"><br>
		<input type="submit"><br>
	</form>
		<br>
		<br>
		Get Item <br>
		<form action="getItem">
		<input type="text" name="itemID"placeholder="Item ID"><br>
		<input type="submit"><br>
	</form>
	
	<form action="withdrawalItem">
		Withdrawal Item<br>
		<input type="text" name="itemID" placeholder="Item ID"><br>
		<input type="text" name="amountToWithdrawal" placeholder="Amount To Withdrawal"><br>
		<input type="submit"><br>
	</form>
	${message}

</body>
</html>