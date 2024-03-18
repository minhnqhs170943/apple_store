<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <title>Order </title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

         

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        



        <!-- Css Styles -->
        <style>
            img {
                width: 200px;
                height: 120px;
            }
        </style>

    </head>

    <body class="sb-nav-fixed">



        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="admin">Admin</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->

            <!-- Navbar-->

        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">


                            <a class="nav-link" href="admin">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                            <a class="nav-link active" href="listproduct">
                                <div class="sb-nav-link-icon"><i class="fas fa-mobile-phone"></i></div>
                                Product
                            </a>
                            <a class="nav-link" href="orderlist">
                                <div class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></div>
                                View Order
                            </a>
                            <a class="nav-link" href="accountmanager">
                                <div class="sb-nav-link-icon"><i class="fas fa-user"></i></div>
                                View Account
                            </a>

                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                Acount
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="profileuser?uid=${1}">Profile</a>
                                    <a class="nav-link" href="changepass.jsp">Change Password</a>
                                </nav>
                            </div>
                            <a class="nav-link" href="home">
                                <div class="sb-nav-link-icon"><i class="fas  fa-home"></i></div>
                                Home
                            </a>


                        </div>
                    </div>

                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container">
                        <div class="table-wrapper">
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <h1 class="mt-4">Products</h1><br>
                                        <form action="listproduct" >
                                            <div class="menu_box_2">
                                                <input required name="txt" class="form_1" type="text" value="${txt}" placeholder="Search...">
                                                <input type="submit" value="Ok">
                                            </div>
                                        </form>

                                        <br>
                                        <a href="addproduct"><button type="button" class="btn btn-success">Add new Product</button>

                                    </div>

                                    <div class="col-sm-6">
                                        <a href="#"   > </a>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Image</th>
                                        <th>Price</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listP}" var="p">
                                        <tr>
                                            <td>${p.getId()}</td>
                                            <td>${p.getName()}</td>
                                            <td>
                                                <img src="${p.getUrl_img()}">
                                            </td>
                                            <td>${p.getPrice()}</td>

                                            <td>
                                                <a style="color: #0077FF" href="detail?pid=${p.getId()}" class="edit" data-toggle="modal"><i class="material-icons"data-toggle="tooltip" title="detail">?</i></a>
                                                <a style="color: #0077FF" href="editproduct?ID=${p.getId()}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                                <a style="color: #0077FF" href="javascript:void(0);" class="delete" data-toggle="modal" onclick="confirmDelete('${p.getId()}');"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="store-filter clearfix">

                                <c:set var="page" value="${requestScope.page}"/>
                                <ul class="store-pagination">

                                    <c:forEach begin="${1}" end="${requestScope.num}" var="i">   

                                        <li><a style="text-decoration: none" class="${i==page?"active":""}" href="listproduct?page=${i}&txt=${txt}">${i}</a> </li>    

                                    </c:forEach>

                                </ul>
                            </div>
                        </div>
                    </div>


                </main>

            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>

        <!-- Edit Modal HTML -->

        <script src="js/manager.js" type="text/javascript"></script>

        <script>
                                                    function confirmDelete(productID) {
                                                        if (confirm("Are you sure you want to delete this product?")) {
                                                            window.location.href = "deleteproduct?ID=" + productID;
                                                        }
                                                    }
        </script>

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>

</html>
