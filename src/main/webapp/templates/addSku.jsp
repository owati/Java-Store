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

    <jsp:include page="./components/adminnav.jsp" />
    <main class="container mx-auto p-4 mt-5">
        <jsp:include page="./components/adminaction.jsp" />

        <form action="./productsadd" method="POST" class="grid lg:grid-cols-2 gap-3 max-w-2xl mx-auto" enctype="multipart/form-data">
            <h1 class="text-2xl font-semibold col-span-full">Add Product</h1>
            <input name="name" placeholder="Name" /> 
            <input name="price" placeholder="Price" />
            <input name="stockQuantity" placeholder="Quantity" /> 

            <select name="category">
                <% for (String cat : (List<String>) request.getAttribute("categories")) {%>
                    <option value="<%=cat%>"><%=cat%></option>
                <%}%>
            </select>

            <div class="flex col-span-full justify-between">
                <input name="image" type="file"/> 
            </div>

            <button class="p-2 bg-slate-700 rounded-md text-white w-full col-span-full" type="submit">Create Product</button>
        </form>
    </main>
</body>
</html>