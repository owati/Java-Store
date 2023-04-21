<%@page import="com.stores.db.models.Sku"%>
<%@page import="java.util.List"%>

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
        <h1 class="text-lg text-slate-700 font-semibold">Categories</h1>
        <div class="flex flex-wrap gap-5 mt-2">
            <a href="./home" class="p-2 bg-slate-700 rounded-md text-white">All</a>
            <% for (String cat : (List<String>) request.getAttribute("categories")) {%>
                <a href="./home?cat=<%=cat%>" class="p-2 bg-slate-700 rounded-md text-white"><%=cat%></a>
            <%}%>
            
        </div>
        
        <hr class="border my-4">
        <%-- <%=request.getAttribute("error")%> --%>
        <%List<Sku> products =  (List<Sku>) request.getAttribute("products");%>
        <%if (products.size() == 0) {%>
                        <h1 class="text-xl text-center">There are not products in this category</h1>
        <%} else {%>
            <div class="grid md:grid-cols-2 xl:grid-cols-6 gap-10">
                <% for (Sku product : products){%>
                    <div class="w-full shadow-md hover:scale-105 hover:shadow-xl transition-all aspect-square rounded-lg overflow-hidden">
                        
                        <img src="/images/<%=product.getImage()%>" class="w-full h-[60%] object-cover hover:scale-110 transition-all"/>
                        
                        <div class="h-[40%] flex flex-col justify-between p-2">
                            <h1 class="flex justify-between"><%=product.getName()%> <span class="text-lg">$<%=product.getPrice()%></span></h1> 
                            <a href="./addtocart?id=<%=product.getId()%>" class="p-2 bg-slate-700 rounded-md text-white text-sm">Add to Cart</a>
                        </div>
                    </div>
                <%}%>
            </div>
        <%}%>
    </main>

</body>
</html>