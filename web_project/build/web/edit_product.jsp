 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Product Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editNewModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="editproduct" method="post">
                            <div class="modal-header">						
                                <h4 style="text-align:  center" class="modal-title">Edit Product</h4>
                                <h3 style="color: red">${requestScope.error}</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${detail.getId()}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${detail.getName()}" name="name" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Image</label>
                                    <input value=" ${detail.getUrl_img()}" name="image" type="file" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Quantity</label>
                                    <input value="${detail.getQuantity()}" name="quantity" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="${detail.getPrice()}" name="price" type="text" class="form-control" required>
                                </div>
                      
                                <div class="form-group">
                                    <label>Category</label>
                                    <select name="category" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listC}" var="c">                                   
                                            <option value="${c.getCid()}"> ${c.getCname()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>description</label>
                                    <input value="${detail.getDescription()}" name="des" type="text" class="form-control" required>
                                </div>
                            </div>
                                 <div class="modal-header">						
                            <h4 style="text-align: center" class="modal-title">Product Detail</h4>


                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <input value="${detailSpe.getScreen()}" placeholder="Screen Size" name="screen" type="text" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <input value="${detailSpe.getCpu()}" placeholder="Cpu" name="cpu" type="text" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <input value="${detailSpe.getRam()}"  placeholder="Ram" name="ram" type="text" class="form-control" required>
                            </div>

                             <div class="form-group">
                                <input value="${detailSpe.getStorage()}" placeholder="Storage capacity" name="storage" type="text" class="form-control" required>
                            </div>

                        </div>
                            <div class="modal-footer">

                                <a class="btn btn-danger" href="listproduct">Cancel</a>
                                <input type="submit" class="btn btn-success" value="Edit">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/manager.js" type="text/javascript"></script>
    </body>
</html>