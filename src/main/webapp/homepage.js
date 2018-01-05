/* 
 Author Minh Tran
 */
var param = function (name, value) {
    this.name = name;
    this.value = value;
};
$(document).ready(function () {
    $("#btn_search").click(function () {
        getDataTiki($("#inputURL").val());
        getPriceProduct($("#inputURL").val());
        getPriceHistory($("#inputURL").val());
        saveUserRequest($("#inputURL").val());
        getSuggestUrl($("#inputURL").val());
    });
});

var getPriceProduct = function (url) {
    var filterParam = new Array();
    filterParam[filterParam.length] = new param("url", url);
    $.ajax({
        url: "getPriceProduct",
        type: "POST",
        data: filterParam,
        success: function (response) {
            response = $.parseJSON(response);
            $("#avgPrice").text(response[0].price);
            $("#hPrice").text(response[1].price);
            $("#lPrice").text(response[2].price);
            $("#hDate").text(response[1].dateUpdated);
            $("#lDate").text(response[2].dateUpdated);
        },
        error: function () {
        }
    });
};
var getPriceHistory = function (url) {
    var filterParam = new Array();
    filterParam[filterParam.length] = new param("url", url);
    $.ajax({
        url: "getPriceHistory",
        type: "POST",
        data: filterParam,
        success: function (response) {
            response = $.parseJSON(response);
            var catelories = new Array();
            var productsPrice = new Array();
            $.each(response, function (key) {
                catelories[catelories.length] = response[key].dateUpdated;
                productsPrice[productsPrice.length] = response[key].price;
            });
            Highcharts.chart('chartContainer', {
                chart: {
                    type: 'line'
                },
                title: {
                    text: 'Lịch sử giá sản phẩm'
                },
                subtitle: {
                    text: 'Nguồn: tiki.vn'
                },
                xAxis: {
                    categories: catelories
                },
                yAxis: {
                    title: {
                        text: 'Giá'
                    }
                },
                plotOptions: {
                    line: {
                        dataLabels: {
                            enabled: true
                        },
                        enableMouseTracking: false
                    }
                },
                series: [{
                        data: productsPrice
                    }]
            });
        },
        error: function () {
        }
    });
};
var getDataTiki = function (link) {
    $.ajax({
        url: link,
        type: "POST",
        success: function (response) {
            //lấy thông tin mô tả của sản phẩm
            $(response).find('[class="top-feature-item bullet-wrap"]').each(function () {
                $(".item-discription").html($.trim($(this).html())); // nhúng thông tin mô tả vào website
            });
            // lấy hình ảnh của sản phẩm
            $(response).find('[id="product-magiczoom"]').each(function () {
                $(".item-image").attr("src", $(this).attr("src"));// nhúng hình ảnh vào website
            });
            //lấy tên của sản phẩm
            $(response).find('[id="product-name"]').each(function () {
                $(".item-name").html($.trim($(this).html()));// nhúng tên sản phẩm vào website
            });
        },
        error: function () {
        }
    });
};
var saveUserRequest = function (url) {
    var filterParam = new Array();
    filterParam[filterParam.length] = new param("url", url);
    filterParam[filterParam.length] = new param("sessionID", readCookie("sessionID_Min"));
    $.ajax({
        url: "saveUserRequest",
        type: "POST",
        data: filterParam,
        success: function (response) {
            if (response > 0) {
                createCookie("sessionID_Min", response, 0.5);
            }
        },
        error: function () {
        }
    });
};

var getSuggestUrl = function (url) {
    var filterParam = new Array();
    filterParam[filterParam.length] = new param("url", url);
    $.ajax({
        url: "getSuggestUrl",
        type: "POST",
        data: filterParam,
        success: function (response) {
            response = $.parseJSON(response);
            $.each(response, function (key) {
                var url=response[key].url;
                $.ajax({
                    url: url,
                    type: "POST",
                    success: function (response) {
                        // lấy hình ảnh của sản phẩm
                        var srcImg = "";
                        var namePrc="";
                        $(response).find('[id="product-magiczoom"]').each(function () {
                            srcImg = $(this).attr("src");// nhúng hình ảnh vào website
                        });
                        //lấy tên của sản phẩm
                        $(response).find('[id="product-name"]').each(function () {
                           namePrc= $.trim($(this).html());// nhúng tên sản phẩm vào website
                        });
                        var html="";
                        html+='<a href="'+url+'"><img id="sgImg1"  src="'+srcImg+'"   height="100%" width="100%"></a>';
                        $(".product-image"+(key+1)).html(html);
                        $(".product-image"+(key+1)).next().text(namePrc);
                    },
                    error: function () {
                    }
                });
            });
        },
        error: function () {
        }
    });
};

//<editor-fold defaultstate="collapsed" desc="CREATE COOKIE">
function createCookie(name, value, hour) {
    var expires = "";
    if (hour) {
        var date = new Date();
        date.setTime(date.getTime() + (hour * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="READ COOKIE">
function readCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ')
            c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0)
            return c.substring(nameEQ.length, c.length);
    }
    return null;
}
//</editor-fold>