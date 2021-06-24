$(document).ready(function()
{
	
	$(".minusButton").on("click",function(evt) 
	{
		evt.preventDefault();
		decreaseQuantity($(this));
	});
	
	$(".plusButton").on("click",function(evt) 
	{
		evt.preventDefault();
		increaseQuantity($(this));
	});

	updateTotal();

});

function decreaseQuantity(link)
{
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) - 1;
	if (newQty > 0)
	{
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}

function increaseQuantity(link)
{
	productId = link.attr("pid");
	qtyInput = $("#quantity" + productId);
	newQty = parseInt(qtyInput.val()) + 1;
	if (newQty < 11)
	{
		qtyInput.val(newQty);
		updateQuantity(productId, newQty);
	}
}

function updateQuantity(productId, quantity)
{
	url = contextPath + "cart/update/" + productId + "/" + quantity;
	
	$.ajax(
	{
		type: "POST",
		url: url,
		beforeSend: function(xhr)
		{
			xhr.setRequestHeader(crsfHeaderName, csrfValue);
		}	
	}).done(function(newSubtotal) {
		updateSubtotal(newSubtotal, productId);
		updateTotal();
	}).fail(function() {
		$("#modalTitle").text("Shopping Cart");
		$("#modalBody").text("Error while adding product to cart.");
	});		
};

function updateSubtotal(newSubtotal, productId)
{
	$("#subtotal" + productId).text(newSubtotal);
}

function updateTotal()
{
	total = 0.0;
	
	$(".productSubTotal").each(function(index, element)
	{
		total = total + parseFloat(element.innerHTML);
	});
	
	$("#totalAmount").text("Rs." + total);
}