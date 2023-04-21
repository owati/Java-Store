

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
            
        <div class="flex flex-col items-center gap-3">    
            <h1 class="text-xl text-center"><%=request.getAttribute("message")%></h1>
            <a href="./home" class="p-2 bg-slate-700 rounded-md text-white">Go Back</a>
        </div>
    </main>

</body>
</html>