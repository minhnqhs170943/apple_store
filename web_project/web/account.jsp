<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
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
                            <a class="nav-link" href="listproduct">
                                <div class="sb-nav-link-icon"><i class="fas fa-mobile-phone"></i></div>
                                Product
                            </a>
                            <a class="nav-link" href="orderlist">
                                <div class="sb-nav-link-icon"><i class="fas fa-shopping-cart"></i></div>
                                View Order
                            </a>
                            <a class="nav-link active" href="accountmanager">
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
                                    <a class="nav-link" href="register.html">Change Password</a>
                                    <a class="nav-link" href="password.html">Forgot Password</a>
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
                                        <h1 class="mt-4">Maneger User</h1><br>
                                    </div>
                                    <div class="col-sm-6">
                                        <!--                            <a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>-->
                                        <!--                            <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						-->
                                    </div>
                                </div>
                            </div>
                            <table class="table table-striped table-hover">
                                <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Booking history</th>
                                        <th>View profile</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listU}" var="u">
                                        <tr>
                                            <td>${u.user}</td>
                                            <td><a style="text-decoration: none; color: #0077FF;" href="orderlist?uid=${u.user_id}">click</a></td>
                                            <td><a style="text-decoration: none; color: #0077FF;" href="profileuser?uid=${u.user_id}">view</a></td>
                                            <td>
                                                <a style="text-decoration: none; color: red;" onclick="confirmDelete('${u.user_id}');" href="#">X</a>             
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>


                    </div>

                </main>

            </div>
        </div>
        <script>
                                                    function confirmDelete(uid) {
                                                        if (confirm("Are you sure you want to delete this account?")) {
                                                            window.location.href = "deleteaccount?uid=" + uid;
                                                        }
                                                    }
        </script>
        <script src="js/manager.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>


    </body>
</html>