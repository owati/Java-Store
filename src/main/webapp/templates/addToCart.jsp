<%@page import="com.stores.db.models.Sku"%>

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

        <%-- <%=request.getAttribute("product")%> --%>

        <% Sku product = (Sku) request.getAttribute("product"); %>
        <a href="./home" class="p-2 bg-slate-700 rounded-md text-white">Go Back</a>
            
            <div  class="mx-auto max-w-4xl ">
                <h1 class="text-3xl mb-3"><%=product.getName()%></h1>
                <img class="w-full h-60 rounded-xl object-cover"  src="/images/<%=product.getImage()%>"/>

                <form method="POST" action="./addtocart" class="grid lg:grid-cols-2 mt-4 items-center gap-4 ">
                    <h1>
                        Unit price
                        <span>$<%=product.getPrice()%></span> <br>
                        Amount in store
                        <span><%=product.getStockQuantity()%></span>
                    </h1> 
                    <input type="number" name="quantity" placeholder="quantity" min="0" max="10">
                    <input type="hidden" name="id" value="<%=product.getId()%>">
                    <button class="p-2 bg-slate-700 rounded-md text-white w-full col-span-full" type="submit">Add to Cart</button>
                </form>
                
                
            </div>
    </main>

</body>
</html>