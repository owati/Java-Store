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

        <a href="./cart" class="p-2 bg-slate-700 rounded-md text-white">Go Back</a>
            
            <form action="./checkout" method="POST" class="grid gap-3 max-w-2xl mx-auto">
                <h1 class="text-2xl font-semibold col-span-full">Checkout</h1>
                <input required placeholder="Name" name="name"/>                 
                
                <div class="flex col-span-full justify-between">
                    <input required placeholder="Card Number" type="number" minlength="10" maxlength="10" /> 
                </div>
                <button class="p-2 bg-slate-700 rounded-md text-white w-full col-span-full" type="submit">Place Order</button>
            </form>
    </main>

</body>
</html>