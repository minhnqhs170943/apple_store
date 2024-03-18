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

        <!-- Css Styles -->
       
        <style>
            /* Custom CSS */

            /* Table Styling */
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }

            th,
            td {
                text-align: left;
                padding: 10px;
            }

            th {
                background-color: #f2f2f2;
            }

            /* Button Styling */
            input[type="submit"] {
                padding: 10px 20px;
                background-color: #218838;
                color: white;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                font-size: 12px;
                margin-bottom: 5%;
                font-weight: bold;
            }

            input[type="submit"]:hover {
                background-color: #1e7e34;
            }

            /* Back to Homepage Button Styling */
            .back-to-homepage {
                display: flex;
                justify-content: center;
                margin-bottom: 3%;
            }

            .back-to-homepage a {
                padding: 10px 20px;
                background-color: #004085;
                color: white;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                font-size: 16px;
                font-weight: bold;
                text-decoration: none;
            }

            .back-to-homepage a:hover {
                background-color: #003164;
            }

            /* End of Custom CSS */
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
                             <a class="nav-link" href="listproduct">
                                <div class="sb-nav-link-icon"><i class="fas fa-mobile-phone"></i></div>
                                Product
                            </a>
                            <a class="nav-link active" href="orderlist">
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
                    <div>
                <table>
                    <thead>
                        <tr>
                            <th>ODID</th>
                            <th>UserName</th>
                            <th>Order Date</th>
                            <th>totalMoney</th>
                            <th>Detail</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listO}" var="o">
                        <tr>
                            <td>${o.id}</td>
                            <td>${o.user_name}</td>
                            <td>${o.date}</td>
                            <td>${o.totalMoney}</td>
                            <td>
                                <a href="orderdetail?odid=${o.id}">
                                    <input type="submit" value="Detail">
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
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
