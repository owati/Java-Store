<%@page import="java.util.List"%>
<%@page import="com.stores.db.models.Orders"%>
<%@page import="com.stores.db.models.OrderItem"%>

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

        <% List<Orders> orders = (List<Orders>) request.getAttribute("orders");%>

        <%if (orders.size() == 0) {%>
            <h1 class="text-xl text-center">There are no orders </h1>
        <%} else {%>
            <table class="table-auto mx-auto w-full">
                <thead>
                    <tr class="border-b-2">
                        <th>Customer</th>
                        <th>Items</th>
                        <th>Cost</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Orders order : orders){%>
                        <form method="POST" action="./orders">
                            <tr>
                                <td><%=order.getName()%></td>
                                <td>
                                    <ul>
                                        <% for (OrderItem item : order.getItems()) {%>
                                            <li><%=item.getProduct()%>(x <%=item.getQuantity()%>) --- $<%=item.getTotal()%></li>
                                        <%}%>
                                    </ul>
                                </td>
                                <td>$<%=order.getTotal()%></td>
                                <td><%=!order.isDelivered() ? "Pending" : "Delivered"%></td>
                                <% if( order.isDelivered()) {%>
                                    <td></td>
                                <%} else {%>
                                    <td><button class="p-2 bg-slate-700 rounded-md text-white w-fit px-3 col-span-full" type="submit">Deliver</button></td>
                                <%}%>
                                <input type="hidden" name="id" value="<%=order.getId()%>">
                            </tr>
                        </form>
                    <%}%>
                </tbody>

            </table>
        <%}%>
    </main>
</body>
</html>