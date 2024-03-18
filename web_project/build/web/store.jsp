
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->


        <title>Electro - HTML Ecommerce Template</title>

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>

        <style>

            .filter {
                background-color: #f7f7f7;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
                text-align: center;
                display: flex;
                justify-content: center;
                max-width: 800px; /* Điều chỉnh độ rộng của phần filter tùy theo ý muốn */
                margin: 0 auto; /* Để căn giữa phần filter */
            }

            .filter form {
                display: flex;
                align-items: center;
                gap: 10px;
            }

            .filter label {
                font-weight: bold;
            }

            .filter select {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: #fff;
            }

            .filter input[type="submit"] {
                background-color: #007BFF;
                color: #fff;
                border: none;
                border-radius: 5px;
                padding: 10px 20px;
                cursor: pointer;
            }

            .filter input[type="submit"]:hover {
                background-color: #0056b3;

            </style>

            <script type="text/javascript">
                function setCheck(obj) {

                    var fries = document.getElementsByName('cidd');
                    if ((obj.id == 'c0') && (fries[0].checked == true)) {
                        for (var i = 1; i < fries.length; i++) {
                            fries[i].checked = false;
                        }
                    } else {
                        for (var i = 1; i < fries.length; i++) {
                            if (fries[i].checked == true) {
                                fries[0].checked = false;
                                break;
                            }
                        }
                    }

                    document.getElementById('f1').submit();
                }

                function setCheck1(obj) {

                    var fries = document.getElementsByName('price');
                    if ((obj.id == 'p0') && (fries[0].checked == true)) {
                        for (var i = 1; i < fries.length; i++) {
                            fries[i].checked = false;
                        }
                    } else {
                        for (var i = 1; i < fries.length; i++) {
                            if (fries[i].checked == true) {
                                fries[0].checked = false;
                                break;
                            }
                        }
                    }

                    document.getElementById('f2').submit();
                }

            </script>

            <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
            <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
              <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
            <![endif]-->


        </head>
        <body>
            <!-- HEADER -->
            <header>
                <!-- TOP HEADER -->
                <div id="top-header">
                    <div class="container">

                        <ul class="header-links pull-right">
                            <c:if test="${sessionScope.acc==null}">
                                <li><a href="login.jsp"><i class="fa fa-user-o"></i> Login</a></li>    
                                </c:if>
                                <c:if test="${sessionScope.acc!=null}">
                                <li><a href="logout"><i class="fa fa-user-o"></i> Logout</a></li>
                                    <c:if test="${!sessionScope.acc.isIsAdmin()}">
                                    <li><a href="orderlistuser"><i class="fa fa-user-o"></i> My Order</a></li>

                                </c:if>
                                <c:if test="${sessionScope.acc.isIsAdmin()}">
                                    <li><a href="admin"><i class="fa fa-user-o"></i>Admin</a></li>
                                    </c:if>
                                <li><a href="profile"><i class="fa fa-user-o"></i> view profile</a></li>
                                <li><a href="home"><i class="fa fa-user-o"></i> Hello ${sessionScope.acc.user}</a></li>
                                </c:if>


                        </ul>
                    </div>
                </div>
                <!-- /TOP HEADER -->

                <!-- MAIN HEADER -->
                <div id="header">
                    <!-- container -->
                    <div class="container">
                        <c:if test="${sessionScope.message != null}">
                            <div id="message" class="notification">
                                ${sessionScope.message}
                            </div>
                            <script>
                                // Đợi 3 giây sau khi trang tải xong
                                setTimeout(function () {
                                    var messageDiv = document.getElementById("message");
                                    if (messageDiv) {
                                        messageDiv.style.display = "none"; // Ẩn thông báo
                                        // Xóa sessionScope.message
                                <%
                                            session.removeAttribute("message");
                                %>
                                    }
                                }, 3000); 
                            </script>
                        </c:if>


                        <!-- row -->
                        <div class="row">
                            <!-- LOGO -->
                            <div class="col-md-3">
                                <div class="header-logo">
                                    <a href="home" class="logo" id="hongapple-text">Minh Táo</a>
                                </div>
                            </div>

                            <!-- /LOGO -->

                            <!-- SEARCH BAR -->
                            <div class="col-md-6">
                                <div class="header-search">
                                    <form action="search">
                                        <input  class="input" placeholder="Search here" name="txt" value="${value}">
                                        <button class="search-btn">Search</button>
                                    </form>
                                </div>
                            </div>
                            <!-- /SEARCH BAR -->

                            <c:if test="${!sessionScope.acc.isIsAdmin()}">
                                <!-- ACCOUNT -->
                                <div class="col-md-3 clearfix">
                                    <div class="header-ctn">


                                        <!-- Cart -->
                                        <div class="dropdown">
                                            <a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                                                <i class="fa fa-shopping-cart"></i>
                                                <span>Your Cart</span>
                                                <c:set value="${sessionScope.size}" var="size"/>
                                                <c:if test="${size==null}">
                                                    <div class="qty">0</div>
                                                </c:if>
                                                <c:if test="${size!=null}">
                                                    <div class="qty">${size}</div>
                                                </c:if>

                                            </a>
                                            <div class="cart-dropdown">
                                                <div class="cart-list">
                                                    <c:set value="${sessionScope.cart}" var="o"/>
                                                    <c:forEach  items="${o.items}" var="i">

                                                        <div class="product-widget">
                                                            <div class="product-img">
                                                                <img src="${i.product.url_img}" alt="">
                                                            </div>
                                                            <div class="product-body">
                                                                <h3 class="product-name"><a href="#">${i.product.name}</a></h3>
                                                                <button class="btn btn-sm decrease-qty"><a href="process?num=-1&id=${i.product.id}">-</a></button>
                                                                <span class="qty">x${i.quantity}</span>
                                                                <button class="btn btn-sm increase-qty"><a href="process?num=1&id=${i.product.id}">+</a></button>
                                                                <h4 class="product-price">${i.product.price}</h4>
                                                            </div>
                                                            <form action="process" method="post">
                                                                <input type="hidden" name="id" value="${i.product.id}"/>
                                                                <button type="submit" class="delete"><i class="fa fa-close"></i></button>    
                                                            </form>
                                                            <c:set var="totalQuantity" value="${totalQuantity + i.quantity}" />
                                                        </div>    

                                                    </c:forEach>

                                                </div>
                                                <div class="cart-summary">
                                                    <c:set value="${sessionScope.total}" var="total"/>
                                                    <small>${totalQuantity} Item(s) selected</small>
                                                    <h5>SUBTOTAL: $${total}</h5>
                                                </div>
                                                <div class="cart-btns">
                                                    <a href="viewcart">View Cart</a>
                                                    <a href="order" >Checkout  <i class="fa fa-arrow-circle-right"></i></a>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- /Cart -->
                                    </c:if>

                                </div>
                            </div>
                            <!-- /ACCOUNT -->
                        </div>
                        <!-- row -->
                    </div>
                    <!-- container -->
                </div>
                <!-- /MAIN HEADER -->
            </header>
            <!-- /HEADER -->

            <!-- NAVIGATION -->
            <nav id="navigation">
                <!-- container -->
                <div class="container">
                    <!-- responsive-nav -->
                    <div id="responsive-nav">
                        <!-- NAV -->
                        <ul class="main-nav nav navbar-nav">

                            <li><a  href="home">Home</a></li>
                            <li><a href="home#sell">Hot Deals</a></li>
                            <li class="active"><a href="store">Store</a></li>
                                <c:forEach items="${requestScope.listC}" var="c">
                                <li class="${tag==c.cid?"active":""}"><a href="search?key=${c.cid}">${c.cname}</a></li>
                                </c:forEach>
                            <li><a href="#footer">Contact</a></li>

                        </ul>
                        <!-- /NAV -->
                    </div>
                    <!-- /responsive-nav -->
                </div>
                <!-- /container -->
            </nav>
            <!-- /NAVIGATION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <!-- ASIDE -->
                        <div id="aside" class="col-md-3">
                            <!-- aside Widget -->


                            <div class="aside">

                                <h3  class="aside-title">Categories</h3>
                                <form action="search">
                                    <c:set value="${requestScope.key}" var="k"/>
                                    <select name="key" onchange="submit()">
                                        <option value="0">All</option>
                                        <c:forEach items="${requestScope.listC}" var="c">
                                            <option ${(c.cid==k)?"selected":""} value="${c.cid}">${c.cname}</option>
                                        </c:forEach>
                                    </select>


                                </form>


                            </div>
                            <!-- /aside Widget -->

                            <!-- aside Widget -->
                            <div class="aside">
                                <h3 class="aside-title">Price</h3>                          
                                <div class="checkbox-filter">
                                    <form action="search">
                                        <c:set value="${requestScope.price}" var="pr"/>
                                        <select name="price" onchange="submit()">
                                            <option ${(pr==0)?"selected":""} value="0">All</option>
                                            <option ${(pr==1)?"selected":""} value="1">under 1000$</option>
                                            <option ${(pr==2)?"selected":""} value="2">1000$-2000$</option>
                                            <option ${(pr==3)?"selected":""} value="3">2000$-3000$</option>
                                            <option ${(pr==4)?"selected":""} value="4">over 3000$</option>

                                        </select>
                                    </form>
                                </div>
                            </div>


                        </div>
                        <!-- /ASIDE -->

                        <!-- STORE -->
                        <div id="store" class="col-md-9">
                            <!-- store top filter -->
                            <div class="store-filter clearfix">
                                <div class="store-sort">
                                    <c:set value="${requestScope.s}" var="s"/>
                                    <form  action="sort">
                                        Sort By:
                                        <select name="sort" class="input-select" onchange="submit()">
                                            <option ${(s==0)?"selected":""} value="0">none</option>
                                            <option ${(s==1)?"selected":""} value="1">Low to High Price</option>
                                            <option ${(s==2)?"selected":""} value="2">High to Low Price</option>
                                            <option ${(s==3)?"selected":""} value="3">Oldest</option>
                                            <option ${(s==4)?"selected":""} value="4">Newest</option>


                                        </select>
                                    </form>



                                </div>
                                <ul class="store-grid">
                                    <li class="active"><i class="fa fa-th"></i></li>
                                    <li><a href="#"><i class="fa fa-th-list"></i></a></li>
                                </ul>
                            </div>
                            <!-- /store top filter -->


                            <!-- store products -->
                            <div class="row">
                                <!-- product -->

                                <c:choose>
                                    <c:when test="${empty listP}">
                                        <div class="col-md-12 text-center">
                                            <p style="font-size: 24px;
                                            font-weight: bold;">Not found</p>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${requestScope.listP}" var="p" >
                                            <div class="col-md-4 col-xs-6">

                                                <div class="product">

                                                    <div class="product-img">
                                                        <img src="${p.url_img}" alt="">
                                                         <div class="product-label">
                                                        <span class="sale">-20%</span>
                                                        
                                                    </div>

                                                    </div>
                                                    <div class="product-body">

                                                        <h3 class="product-name"><a href="detail?pid=${p.id}">${p.name}</a></h3>
                                                        <h4 class="product-price">${p.price} <del class="product-old-price">${p.price*1.2}</del></h4>
                                                        <div class="product-rating">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                        <div class="product-btns">

                                                            <button class="quick-view" onclick="window.location.href = 'detail?pid=${p.id}'"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
                                                        </div>
                                                    </div>
                                                    <div class="add-to-cart">
                                                        <form name="f" action="buy?id=${p.id}" method="post">
                                                            <button type="submit" class="add-to-cart-btn">
                                                                <i class="fa fa-shopping-cart"></i> add to cart
                                                            </button>
                                                        </form>

                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>


                                <!-- /product -->

                            </div>
                            <!-- /store products -->

                            <!-- store bottom filter -->
                            <div class="store-filter clearfix">

                                <c:set var="page" value="${requestScope.page}"/>
                                <ul class="store-pagination">

                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">   

                                        <li><a class="${i==page?"active":""}" href="search?page=${i}&txt=${value}&key=${k}&price=${pr}&sort=${s}">${i}</a> </li>    

                                    </c:forEach>

                                </ul>
                            </div>
                            <!-- /store bottom filter -->
                        </div>
                        <!-- /STORE -->
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /SECTION -->



            <!-- FOOTER -->
            <jsp:include page="footer.jsp"></jsp:include>
            <!-- /FOOTER -->

            <!-- jQuery Plugins -->
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/slick.min.js"></script>
            <script src="js/nouislider.min.js"></script>
            <script src="js/jquery.zoom.min.js"></script>
            <script src="js/main.js"></script>


        </body>

    </html>

