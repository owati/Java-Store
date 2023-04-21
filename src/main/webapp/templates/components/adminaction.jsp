<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>

<%!
Map<String, String> actionMap= new HashMap<String, String>() {{
    put("View Products", "./products");
    put("Add Product", "./productsadd");
    put("View Orders", "./orders");
}};
%>
<div>
<h1 class="text-lg text-slate-700 font-semibold">Admin Actions</h1>
<div class="flex flex-wrap gap-5 mt-2">
    <% for (Entry<String, String> entry : actionMap.entrySet()) {%>
        <a href="<%=entry.getValue()%>" class="p-2 bg-slate-700 rounded-md text-white"><%=entry.getKey()%></a>
    <%}%>
</div>
<hr class="border my-4">
</div>