<%@page import="java.util.List"%>
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

    <jsp:include page="./components/adminnav.jsp" />
    <main class="container mx-auto p-4 mt-5">
        <jsp:include page="./components/adminaction.jsp" />

        <% List<Sku> products = (List<Sku>) request.getAttribute("products");%>

        <%if (products.size() == 0) {%>
            <h1 class="text-xl text-center">There are not products </h1>
        <%} else {%>
            <table class="table-auto mx-auto w-full">
                <thead>
                    <tr class="border-b-2">
                        <th>name</th>
                        <th>category</th>
                        <th>price</th>
                        <th>quantity</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Sku product : products){%>
                        <form method="POST" action="./products">
                            <tr>
                                <td><%=product.getName()%></td>
                                <td><%=product.getCategory().getValue()%></td>
                                <td>$<%=product.getPrice()%></td>
                                <td><input class="!w-32" name="quantity" min="0" type="number" value="<%=product.getStockQuantity()%>"></td>
                                <td><button class="p-2 bg-slate-700 rounded-md text-white w-fit px-3 col-span-full" type="submit">Update</button></td>
                                <input type="hidden" name="id" value="<%=product.getId()%>">
                            </tr>
                        </form>
                    <%}%>
                </tbody>

            </table>
        <%}%>
    </main>
</body>
</html>