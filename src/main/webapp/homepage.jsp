<%-- 
    Document   : homepage
    Created on : Dec 5, 2017, 10:19:59 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div align="center" style="width: 1200px;margin: auto;">
            <h1>Price Tracker</h1>
            <div  style="margin: 50px;">
                <input id="inputURL" type="text" style="width:540px">
                <input type="button" id="btn_search" class="btn-info" value="Search">
            </div>
            <div class="item-info col-md-12">
                <div style="float: left;">
                    <img class="item-image" src="https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/1/_/1.u4939.d20170921.t153352.506708.jpg"  alt="Smiley face" height="160" width="160">
                </div>
                <div class="item-name">
                </div>


                <div class="item-discription">
                    Điện Thoại iPhone 8 Plus 64GB - Hàng Chính Hãng FPT

                    Chính hãng, Nguyên seal, Mới 100%, Chưa Active
                    Miễn phí giao hàng toàn quốc
                    Thiết kế: Nguyên khối
                    Màn hình: LED-backlit IPS LCD, 5.5 inch Full HD
                    Camera Trước/Sau: 7MP/ 2 camera 12MP
                    CPU: Apple A11 Bionic 6 nhân
                    Bộ Nhớ: 64GB
                    RAM: 3GB
                    SIM: 1 Nano SIM
                    Tính năng: Chống nước, chống bụi, 3D touch
                </div>
            </div>
            <div class="col-md-4 price-info" style="margin-top: 40px;">
                <table id="tbl_product" class="product_pane" width="100%"> 
                    <thead>
                        <tr>
                            <th width="40%">Thông Tin</th>
                            <th width="26%">Giá</th>
                            <th class="" width="34%">Thời Điểm</th>
                        </tr>
                    </thead>
                    <tbody id="tbl_price">
                        <tr>
                            <td>Giá trung bình</td>
                            <td id='avgPrice'>27.500.000đ </td>
                        </tr>
                        <tr class="highest_price">
                            <td>Cao Nhất <sup>*</sup></td>
                            <td id='hPrice'>27.500.000đ</td>
                            <td id='hDate'>07-12-2017</td>
                        </tr>
                        <tr class="lowest_price">
                            <td>Thấp Nhất <sup>*</sup></td>
                            <td id='lPrice'>27.500.000đ</td>
                            <td id='lDate'>07-12-2017</td>
                        </tr>

                    </tbody>
                </table>
            </div>
            <div class="col-md-8 price-history-chart" >
                <div class="col-md6" id="chartContainer" style="min-width: 300px; height: 400px; margin: 0 auto">></div>
            </div>
            <div class="red-products col-md-12" style="
                 margin-top: 100px;
                 ">
                <div class="product1 col-md-3">
                    <div class="product-image col-md-12">
                        <img src="https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/1/_/1.u4939.d20170921.t153352.506708.jpg"  alt="Smiley face" height="100%" width="100%">
                    </div>
                    Điện Thoại iPhone X 64GB - Hàng Nhập Khẩu

                </div>

                <div class="product1 col-md-3">
                    <div class="product-image col-md-12">
                        <img src="https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/v/a/vang%20dong_1%20(1).u696.d20160401.t004115.jpg"  alt="Smiley face" height="100%" width="100%">
                    </div>
                    Samsung Galaxy J1 (2016)

                </div>
                <div class="product1 col-md-3">
                    <div class="product-image col-md-12">
                        <img src="https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/h/t/htc10silver1.u425.d20160524.t163116.jpg"  alt="Smiley face" height="100%" width="100%">
                    </div>
                    Điện Thoại HTC 10 - Hàng Chính Hãng


                </div>
                <div class="product1 col-md-3">
                    <div class="product-image col-md-12">
                        <img src="https://vcdn.tikicdn.com/cache/550x550/media/catalog/product/s/o/sony-xzden.u504.d20161003.t161127.398838.jpg"  alt="Smiley face" height="100%" width="100%">
                    </div>
                    Sony Xperia XZ - F8332 - Hàng Chính Hãng

                </div>

            </div>
        </div>
        <!-- IMPORT  -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <script src="https://code.highcharts.com/modules/exporting.js"></script>
        <script src="homepage.js" type="text/javascript"></script>
    </body>
</html>
