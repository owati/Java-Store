<%@page session="true"%>
<%@page import="com.stores.db.models.Cart"%>

<% Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
    }
%>
    <nav class="bg-slate-700 text-white flex justify-between text-lg">
        <div class="container mx-auto  flex justify-between p-4 text-lg">
            <h1 class="flex items-center font-semibold"> <img class="w-8 mr-3" src="assets/uniform.png"
                    loading="lazy" /> Clothes For Men</h1>
            <a class="relative" href="./cart">
                <span
                    class="absolute -top-1 -right-2  bg-white border-2
                 border-slate-700 rounded-full text-slate-700 w-5 font-semibold h-5 flex justify-center items-center text-sm"><%=cart.size()%></span>
                <img src="assets/cart.png" class="w-8" />
            </a>
        </div>
    </nav>