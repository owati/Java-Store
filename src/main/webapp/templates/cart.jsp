<%@page import="com.stores.db.models.CartItem"%>
<%@page import="com.stores.db.models.Sku"%>
<%@page session="true"%>
<%@page import="com.stores.db.models.Cart"%>

<% Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./css/style.css" rel="stylesheet" type="text/css"/>
    <title>Clothes For Men</title>
</head>


<body>
    <jsp:include page="./components/navbar.jsp" />

    <main class="container mx-auto p-4 mt-5">
             <a href="./home" class="p-2 bg-slate-700 rounded-md text-white">Go Back</a>

             <% if (cart.size() == 0) {%>
                <div class="flex flex-col items-center gap-3">    
                    <h1 class="text-xl text-center">There are no items in the cart</h1>
                    <a href="./home" class="p-2 bg-slate-700 rounded-md text-white">Go Back</a>
                </div>
             <%} else {%>

                <div class="mx-auto max-w-xl grid gap-4">
                    <% for (CartItem item : cart) {%>
                        <% Sku product = item.getProduct();%>
                        <div class="shadow-md rounded-lg h-28 flex overflow-hidden">
                            <img  src="/images/<%=product.getImage()%>" class="object-cover w-1/2 h-full"/>
                            <div class="w-1/2 h-full justify-between flex  flex-col p-3">
                                <h1 class="text-3xl"><%=product.getName()%> (x<%=item.getQuantity()%>)</h1>

                                <h1 class="text-xl">Price: $<%=item.getTotalCost()%></h1>
                            </div>
                        </div>
                    <%}%>


                    <h1 class="text-2xl"> Total : $<%=cart.getTotalCost()%></h1>

                    <a href="./checkout" class="p-2 bg-slate-700 rounded-md text-white w-full col-span-full">Proceed to checkout</button>
                </div>
             <%}%>
    </main>

</body>
</html>